/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladorMedicos;
import java.util.Scanner;
import modelos.Medico;
import modelos.Paciente;

/**
 *
 * @author Frankz
 */
public class VistaMedico {
    private Scanner sc;
    private ControladorMedicos controladorMedicos;
    public VistaMedico() {
        sc = new Scanner(System.in);
        controladorMedicos = new ControladorMedicos();
    }
    public void regristrarMedico() {        
        System.out.println("INGRESE CI:");
        String cedula = sc.nextLine();
        System.out.println("INGRESE NOMBRE COMPLETO:");
        String nombre = sc.nextLine();
        System.out.println("INGRESE SU EDAD:");
        int edad = Integer.parseInt(sc.nextLine());
        System.out.println("INGRESE SU ESPECIALIDAD:");
        String especialidad = sc.nextLine();
        System.out.println("INGRESE SU CORREO ELECTRONICO:");
        String email = sc.nextLine();
        System.out.println("INGRESE SU CONTRASEÑA:");
        String password = sc.nextLine();
        
        controladorMedicos.registrarMedico(new Medico(cedula, nombre, edad, especialidad, email, password));        
    }
    
    public void opcionesMedico() {
        System.out.println("-------BIENVENIDO AL SISTEMA-------");
        System.out.println("1. GESTION CITAS");        
        System.out.println("2. GESTION PACIENTES");
        System.out.println("3. SALIR");
    }
}
