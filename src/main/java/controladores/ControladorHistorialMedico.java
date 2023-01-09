/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelos.HistorialMedico;

public class ControladorHistorialMedico {
    
    private File archivo;

    public ControladorHistorialMedico() {
        
        this.archivo = new File("db/historialMedico.txt");
        if (!this.archivo.exists()) {
            try {
                this.archivo.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(0);
            }
        }
    }


    public HistorialMedico obtenerHistorial(String cedula) {
        FileReader fileReader = null;
        BufferedReader br = null;
        HistorialMedico historial = null;
        
        ControladorPacientes gestorPacientes = new ControladorPacientes();
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            while (linea != null) {
                String cedulaEncontrada = linea.split(",")[0];
                if (cedulaEncontrada.equals(cedula)) {
                    String[] datosCita = linea.split(";");
                    historial = new HistorialMedico(
                            gestorPacientes.obtenerPaciente(cedulaEncontrada),
                            datosCita[1], 
                            datosCita[2], 
                            datosCita[3],  
                            datosCita[4], 
                            datosCita[5]);
                    return historial;
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
        return historial;
    }

    public void registrarHistorialMedico(HistorialMedico historial) {
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        String linea = historial.toString();
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
    
    public void eliminarHistorial(String cedula) {
        FileReader fileReader = null;
        BufferedReader br = null;
        HistorialMedico historial = null;
        
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        
        ControladorPacientes gestorPacientes = new ControladorPacientes();
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            
            String cedulaEncontrada = linea.split(",")[0];
            if (cedulaEncontrada.equals(cedula)) {
                fileWriter = new FileWriter(this.archivo);
                bw = new BufferedWriter(fileWriter);
                bw.write("");
                bw.newLine();
                bw.close();
            }
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ControladorBuffers.cerrarBuffers(fileReader, br);
        }
    }

}
