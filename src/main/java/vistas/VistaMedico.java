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
        sc = new Scanner(System.in);
        controladorMedicos = new ControladorMedicos();
        controladorCitas = new ControladorCitas();
        controladorCitasCompletadas = new ControladorCitasCompletadas();
        this.medico = controladorMedicos.obtenerMedico(cedulaMedico);
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
        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                administracionCitas();
                break;
            case 2:
                break;
            default:
                break;
        }
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
                visualizarCitasPendientes(medico.getCedula());
                break;                                
            case 2:
                visualizarCitasCompletadas(medico.getCedula());
            case 3:
                System.out.println("Ingrese el numero de cedula del paciente");
                String cedulaPaciente = sc.nextLine();
                ArrayList<Cita> citasMedicoPaciente = controladorCitas.obtenerCitasMedicoPaciente(medico.getCedula(), cedulaPaciente);
                ArrayList<CitaCompletada> citasCompletadasMedicoPaciente = controladorCitasCompletadas.obtenerCitasMedicoPaciente(medico.getCedula(), cedulaPaciente);
                System.out.println("--- CITAS PENDIENTES ---");
                citasMedicoPaciente.forEach(cita -> {
                    System.out.println(cita.toString());
                });
                System.out.println("--- CITAS COMPLETADAS ---");
                citasCompletadasMedicoPaciente.forEach(cita -> {
                    System.out.println(cita.toString());
                });                
        }
    }
    
    public void visualizarCitasPendientes(String cedulaMedico) {
        ArrayList<Cita> citas = controladorCitas.obtenerCitasDeMedico(cedulaMedico);
        citas.forEach(cita -> {
            System.out.println(cita.completeToString());
        });
    }
    
    public void visualizarCitasCompletadas(String cedulaMedico) {
        ArrayList<CitaCompletada> citasCompletadas = controladorCitasCompletadas.obtenerCitasPorMedico(cedulaMedico);
        citasCompletadas.forEach(cita -> {
            System.out.println(cita.completeToString());
        });
    }
}
