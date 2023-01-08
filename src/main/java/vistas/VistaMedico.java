/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladorCitas;
import controladores.ControladorCitasCompletadas;
import controladores.ControladorMedicos;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import modelos.Cita;
import modelos.CitaCompletada;
import modelos.Medico;
import modelos.Paciente;

/**
 *
 * @author Frankz
 */
public class VistaMedico {
    private Scanner sc;
    Medico medico;
    private ControladorMedicos controladorMedicos;
    private ControladorCitas controladorCitas;
    private ControladorCitasCompletadas controladorCitasCompletadas;
    public VistaMedico() {        
        sc = new Scanner(System.in);
        controladorMedicos = new ControladorMedicos();
        controladorCitas = new ControladorCitas();
        controladorCitasCompletadas = new ControladorCitasCompletadas();
    }
    
    public VistaMedico(String cedulaMedico) {
        this.medico = controladorMedicos.obtenerMedico(cedulaMedico);
        sc = new Scanner(System.in);
        controladorMedicos = new ControladorMedicos();
        controladorCitas = new ControladorCitas();
    }
    
    public void registrarMedico() {        
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
    
    public void administracionCitas() {
        System.out.println("---- GESTION CITAS ----");
        System.out.println("1.- VISUALIZAR CITAS PENDIENTES");
        System.out.println("2.- VISUALIZAR CITAS REALIZADAS");
        System.out.println("3.- VISUALIZAR CITAS CON PACIENTE");
        System.out.println("4.- SALIR");
        
        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                ArrayList<Cita> citas = controladorCitas.obtenerCitasDeMedico(medico.getCedula());
                citas.forEach(cita -> {
                    System.out.println(cita.toString());
                });
                break;                                
            case 2:
                
            case 3:
                System.out.println("");
        }
    }   
}
