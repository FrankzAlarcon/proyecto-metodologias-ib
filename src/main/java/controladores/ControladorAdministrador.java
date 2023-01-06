/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import modelos.Administrador;

/**
 *
 * @author Lida
 */
public class ControladorAdministrador {
    private File archivo;
    
    public ControladorAdministrador() {
        this.archivo = new File("db/admins.txt");
        if (!this.archivo.exists()) {
            try {
                this.archivo.createNewFile();
            } catch(IOException ioe) {
                ioe.printStackTrace();
                System.exit(0);
            }
        }             
    }
    
    public Administrador obtenerAdministrador(String cedula) {        
        FileReader fileReader =  null;
        BufferedReader br = null;
        Administrador administrador = null;
        try {
            fileReader =new FileReader(this.archivo);
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            while (linea != null) {
                String cedulaEncontrada = linea.split(",")[0];
                if (cedulaEncontrada.equals(cedula)) {
                    String[] datosPaciente = linea.split(",");
                    administrador = new Administrador(datosPaciente[0], datosPaciente[1], Integer.parseInt(datosPaciente[2]), datosPaciente[3], datosPaciente[4]);
                    return administrador;
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
        return administrador;
    }
    

}
