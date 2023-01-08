/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

//import gestores.GestorMedicos;
//import gestores.GestorPacientes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelos.Cita;

/**
 *
 * @author Frankz
 */
public class ControladorCitas {

    private File archivo;

    public ControladorCitas() {
        this.archivo = new File("db/citas.txt");
        if (!this.archivo.exists()) {
            try {
                this.archivo.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(0);
            }
        }
    }

    public ArrayList<Cita> obtenerCitas() {
        FileReader fileReader = null;
        BufferedReader br = null;

        ArrayList<Cita> citas = new ArrayList<Cita>();

        ControladorMedicos gestorMedicos = new ControladorMedicos();
        ControladorPacientes gestorPacientes = new ControladorPacientes();
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            Cita cita;
            while (linea != null) {
                String idCitaEncontrado = linea.split(",")[0];
                String[] datosCita = linea.split(",");
                cita = new Cita(datosCita[0], LocalDateTime.parse(datosCita[1]), datosCita[2], datosCita[3], gestorMedicos.obtenerMedico(datosCita[4]), gestorPacientes.obtenerPaciente(datosCita[5]));
                citas.add(cita);
                linea = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ControladorBuffers.cerrarBuffers(fileReader, br);
        }
        return citas;
    }
    
    public ArrayList<Cita> obtenerCitasDeMedico(String cedulaMedico) {
        ArrayList<Cita> citas = obtenerCitas();
        ArrayList<Cita> citasFiltradas = new ArrayList<Cita>();
        citas.forEach(cita -> {
            if (cita.getMedico().getCedula().equals(cedulaMedico)) {
                citasFiltradas.add(cita);
            }
        });
        
        return citasFiltradas;
    }
    
    public ArrayList<Cita> obtenerCitasMedicoPaciente(String cedulaMedico, String cedulaPaciente) {
        ArrayList<Cita> citas = obtenerCitasDeMedico(cedulaMedico);
        ArrayList<Cita> citasFiltradas = new ArrayList<>();
        citas.forEach(cita -> {
            if (cita.getPaciente().getCedula().equals(cedulaPaciente)) {
                citasFiltradas.add(cita);
            }
        });
        return citasFiltradas;
    }

    // retorna paciente o null, si es null no se encuentra el paciente
    public Cita obtenerCita(String idCita) {
        FileReader fileReader = null;
        BufferedReader br = null;
        Cita cita = null;

        ControladorMedicos gestorMedicos = new ControladorMedicos();
        ControladorPacientes gestorPacientes = new ControladorPacientes();
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            while (linea != null) {
                String idCitaEncontrado = linea.split(",")[0];
                if (idCitaEncontrado.equals(idCita)) {
                    String[] datosCita = linea.split(",");
                    cita = new Cita(datosCita[0], LocalDateTime.parse(datosCita[1]), datosCita[2], datosCita[3], gestorMedicos.obtenerMedico(datosCita[4]), gestorPacientes.obtenerPaciente(datosCita[5]));
                    return cita;
                }
                linea = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ControladorBuffers.cerrarBuffers(fileReader, br);
        }
        return cita;
    }

    public void registrarCita(Cita cita) {
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        String linea = cita.toString();
        try {
            fileWriter = new FileWriter(this.archivo, true);
            bw = new BufferedWriter(fileWriter);

            bw.write(linea);
            bw.newLine();
            bw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ControladorBuffers.cerrarBuffers(fileWriter, bw);
        }
    }

    public void eliminarCita(Cita cita) {
        FileReader fileReader = null;
        BufferedReader br = null;

        FileWriter fileWriter = null;
        BufferedWriter bw = null;

        ArrayList<Cita> citas = new ArrayList<Cita>();
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);

            String linea = br.readLine();
            Cita citaEncontrada;
            String[] datosCita;
            while (linea != null) {
                datosCita = linea.split(",");
                if (!cita.getId().equals(datosCita[0])) {
                    citaEncontrada = this.obtenerCita(datosCita[0]);
                    citas.add(citaEncontrada);
                }
                linea = br.readLine();
            }

            fileWriter = new FileWriter(this.archivo);
            bw = new BufferedWriter(fileWriter);

            for (Cita c : citas) {
                bw.write(c.toString());
                bw.newLine();
            }
            bw.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ControladorBuffers.cerrarBuffers(fileWriter, bw, fileReader, br);
        }
    }
}
