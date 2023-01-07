/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladorPacientes;
import java.util.ArrayList;
import java.util.Scanner;
import modelos.Paciente;

/**
 *
 * @author Frankz
 */
public class VistaPaciente {
    private Scanner sc;
    private ControladorPacientes controladorPacientes;
    public VistaPaciente() {
        sc = new Scanner(System.in);
        controladorPacientes = new ControladorPacientes();
    }
    public void regristrarPaciente() {        
        System.out.println("INGRESE CI:");
        String cedula = sc.nextLine();
        System.out.println("INGRESE NOMBRE COMPLETO:");
        String nombre = sc.nextLine();
        System.out.println("INGRESE SU EDAD:");
        int edad = Integer.parseInt(sc.nextLine());
        System.out.println("INGRESE SU CORREO ELECTRONICO:");
        String email = sc.nextLine();
        
        controladorPacientes.registrarPaciente(new Paciente(cedula, nombre, edad, email));
    }
}
