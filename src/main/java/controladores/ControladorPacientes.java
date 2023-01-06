/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import modelos.Paciente;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Frankz
 */
public class ControladorPacientes {

    private File archivo;

    public ControladorPacientes() {
        this.archivo = new File("db/pacientes.txt");
        if (!this.archivo.exists()) {
            try {
                this.archivo.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(0);
            }
        }
    }

    // retorna paciente o null, si es null no se encuentra el paciente
    public Paciente obtenerPaciente(String cedula) {
        FileReader fileReader = null;
        BufferedReader br = null;
        Paciente paciente = null;
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            while (linea != null) {
                String cedulaEncontrada = linea.split(",")[0];
                if (cedulaEncontrada.equals(cedula)) {
                    String[] datosPaciente = linea.split(",");
                    paciente = new Paciente(datosPaciente[0], datosPaciente[1], Integer.parseInt(datosPaciente[2]), datosPaciente[3]);
                    return paciente;
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
        return paciente;
    }

    public void registrarPaciente(Paciente paciente) {
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        String linea = paciente.toString();
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

    public void eliminarPaciente(Paciente paciente) {
        FileReader fileReader = null;
        BufferedReader br = null;

        FileWriter fileWriter = null;
        BufferedWriter bw = null;

        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);

            String linea = br.readLine();
            Paciente pacienteEncontrado;
            String[] datosPaciente;
            while (linea != null) {
                datosPaciente = linea.split(",");
                if (!paciente.getCedula().equals(datosPaciente[0])) {
                    pacienteEncontrado = new Paciente(datosPaciente[0], datosPaciente[1], Integer.parseInt(datosPaciente[2]), datosPaciente[3]);
                    pacientes.add(pacienteEncontrado);
                }
                linea = br.readLine();
            }
            
            fileWriter = new FileWriter(this.archivo);
            bw = new BufferedWriter(fileWriter);
            
            for (Paciente p: pacientes) {                
                bw.write(p.toString());
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
