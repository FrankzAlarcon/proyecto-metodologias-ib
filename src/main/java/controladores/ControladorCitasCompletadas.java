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
import modelos.CitaCompletada;

/**
 *
 * @author Frankz
 */
public class ControladorCitasCompletadas {

    private File archivo;

    public ControladorCitasCompletadas() {
        this.archivo = new File("db/citas_completadas.txt");
        if (!this.archivo.exists()) {
            try {
                this.archivo.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(0);
            }
        }
    }

    public ArrayList<CitaCompletada> obtenerCitasCompletadas() {
        FileReader fileReader = null;
        BufferedReader br = null;

        ArrayList<CitaCompletada> citas = new ArrayList<CitaCompletada>();

        ControladorMedicos gestorMedicos = new ControladorMedicos();
        ControladorPacientes gestorPacientes = new ControladorPacientes();
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            CitaCompletada cita;
            while (linea != null) {                
                String[] datosCita = linea.split(",");
                cita = new CitaCompletada(
                        datosCita[0],
                        LocalDateTime.parse(datosCita[1]),
                        datosCita[2], datosCita[3],
                        Boolean.parseBoolean(datosCita[4]),
                        Double.parseDouble(datosCita[5]),
                        gestorMedicos.obtenerMedico(datosCita[6]),
                        gestorPacientes.obtenerPaciente(datosCita[7]));
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
    
    public ArrayList<CitaCompletada> obtenerCitasPorMedico(String cedulaMedico) {
        ArrayList<CitaCompletada> citas = obtenerCitasCompletadas();
        ArrayList<CitaCompletada> citasFiltradas = new ArrayList<>();
        
        citas.forEach(cita -> {
            if (cita.getMedico().getCedula().equals(cedulaMedico)){
                citasFiltradas.add(cita);
            }            
        });
        
        return citasFiltradas;
    }
    
    public ArrayList<CitaCompletada> obtenerCitasMedicoPaciente(String cedulaMedico, String cedulaPaciente) {
        ArrayList<CitaCompletada> citas = obtenerCitasPorMedico(cedulaMedico);
        ArrayList<CitaCompletada> citasFiltradas = new ArrayList<>();
        citas.forEach(cita -> {
            if (cita.getPaciente().getCedula().equals(cedulaPaciente)) {
                citasFiltradas.add(cita);
            }
        });
        return citasFiltradas;
    }

    // retorna paciente o null, si es null no se encuentra el paciente
    public CitaCompletada obtenerCita(String idCita) {
        FileReader fileReader = null;
        BufferedReader br = null;
        CitaCompletada cita = null;

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
                cita = new CitaCompletada(
                        datosCita[0],
                        LocalDateTime.parse(datosCita[1]),
                        datosCita[2], datosCita[3],
                        Boolean.parseBoolean(datosCita[4]),
                        Double.parseDouble(datosCita[5]),
                        gestorMedicos.obtenerMedico(datosCita[6]),
                        gestorPacientes.obtenerPaciente(datosCita[7]));                    
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

    public void registrarCita(CitaCompletada cita) {
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

    public void eliminarCita(CitaCompletada cita) {
        FileReader fileReader = null;
        BufferedReader br = null;

        FileWriter fileWriter = null;
        BufferedWriter bw = null;

        ArrayList<CitaCompletada> citas = new ArrayList<CitaCompletada>();
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);

            String linea = br.readLine();
            CitaCompletada citaEncontrada;
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

            for (CitaCompletada c : citas) {
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
