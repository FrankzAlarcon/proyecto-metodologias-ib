/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladorCitas;
import java.util.Scanner;

/**
 *
 * @author Frankz
 */
public class VistaAdministrador {
    private Scanner sc;
    public VistaAdministrador() {
        sc = new Scanner(System.in);
    }
    
    public void opcionesAdministrador() {
        System.out.println("-------BIENVENIDO AL SISTEMA-------");
        System.out.println("1. GESTION CITAS");
        System.out.println("2. GESTION MEDICOS");
        System.out.println("3. GESTION PACIENTES");
        System.out.println("4. SALIR");
        int opcion = Integer.parseInt(sc.nextLine());
        
        switch(opcion) {
            case 1:
                administracionCitas();
                break;
            case 2:
                administracionMedicos();
                break;
            case 3:
                administracionPacientes();
                break;
            default:
                System.out.println("Saliendo del sistema...");
                
        }
    }
    
    private void administracionCitas() {
        VistaCitas vistaCitas = new VistaCitas();
        System.out.println("-------GESTION DE CITAS-------");
        System.out.println("1. AGENDAR CITA");        
        System.out.println("2. MOSTRAR CITAS");
        System.out.println("3. MODIFICAR CITA");
        System.out.println("4. ELIMINAR CITA");
        System.out.println("5. SALIR");
        
        int opcion = Integer.parseInt(sc.nextLine());
        
        switch(opcion) {
            case 1:
                vistaCitas.registrarCita();
                break;
            case 2:
                vistaCitas.listarCitas();
                break;
            case 3:
                vistaCitas.modificarCita();
                break;
            case 4:
                vistaCitas.eliminarCita();
                break;
            default:
                System.out.println("Saliendo del sistema...");                                                          
        }
        
    }
    
    private void administracionMedicos(){
        VistaMedico vistaMedico = new VistaMedico();
        System.out.println("-------GESTION DE MEDICOS-------");
        System.out.println("1. REGISTRAR MEDICO");
        System.out.println("2. SALIR");
        
        int opcion = Integer.parseInt(sc.nextLine());
        switch(opcion) {
            case 1:
                vistaMedico.regristrarMedico();
                break;
            default:
                System.out.println("Saliendo del sistema...");                
        }
    }
    
    private void administracionPacientes() {
        VistaPaciente vistaPaciente = new VistaPaciente();
        System.out.println("-------GESTION DE PACIENTES-------");
        System.out.println("1. REGISTRAR PACIENTE");
        System.out.println("2. SALIR");
        int opcion = Integer.parseInt(sc.nextLine());
        
        switch(opcion) {
            case 1:
                vistaPaciente.regristrarPaciente();
                break;
            default:
                System.out.println("Saliendo del sistema...");
        }
    }
    
    
}
