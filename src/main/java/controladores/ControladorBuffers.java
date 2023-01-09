/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Glender Miranda
 */
public class ControladorBuffers {

    public static void cerrarBuffers(FileWriter fileWriter, BufferedWriter bw,
            FileReader fileReader, BufferedReader br) {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
            if (bw != null) {
                bw.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            if (br != null) {
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void cerrarBuffers(FileWriter fileWriter, BufferedWriter bw) {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
            if (bw != null) {
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void cerrarBuffers(FileReader fileReader, BufferedReader br) {
        try {
            if (fileReader != null) {
                fileReader.close();
            }
            if (br != null) {
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
