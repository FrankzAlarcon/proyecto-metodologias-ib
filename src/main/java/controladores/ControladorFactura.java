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
import java.time.format.DateTimeFormatter;
import modelos.Factura;

/**
 *
 * @author Glender Miranda
 */
public class ControladorFactura {

    private File archivo;

    public ControladorFactura() {
        this.archivo = new File("db/facturas.txt");
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
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ControladorBuffers.cerrarBuffers(fileWriter, bw);
        }
    }

    public Factura obtenerFactura(String idFactura) {
        FileReader fileReader = null;
        BufferedReader br = null;
        Factura factura = null;
        try {
            fileReader = new FileReader(this.archivo);
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            while (linea != null) {
                String idEncontrada = linea.split(",")[0];
                if (idEncontrada.equals(idFactura)) {
                    String[] datosFactura = linea.split(",");
                    ControladorMedicos gestorMedicos = new ControladorMedicos();
                    ControladorPacientes gestorPacientes = new ControladorPacientes();
                    factura = new Factura(datosFactura[0], LocalDateTime.parse(datosFactura[1]), datosFactura[2], datosFactura[3], gestorMedicos.obtenerMedico(datosFactura[4]), gestorPacientes.obtenerPaciente(datosFactura[5]),Double.parseDouble(datosFactura[6]),Double.parseDouble(datosFactura[7]),Double.parseDouble(datosFactura[8]));
                    return factura;
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
        return factura;
    }

}
