/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import modelos.Factura;

/**
 *
 * @author Glender Miranda
 */
public class ControladorFactura {

    private File archivo;

    public ControladorFactura() {
        //el idFactura se genera con el tiempo
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedString = currentDateTime.format(customFormat);
        this.archivo = new File("db/factura" + formattedString + ".txt");
        if (!this.archivo.exists()) {
            try {
                this.archivo.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(0);
            }
        } 
    }
    
    
    public void generarFactura(Factura factura) {
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        String linea = factura.toString();
        try {            
            fileWriter = new FileWriter(this.archivo, true);
            bw = new BufferedWriter(fileWriter);
            
            bw.write(linea);
            bw.newLine();
            bw.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ControladorBuffers.cerrarBuffers(fileWriter, bw);
        }   
    }
    
    
}
