/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;
import controladores.ControladorHistorialMedico;
import controladores.ControladorPacientes;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import modelos.HistorialMedico;
import modelos.Paciente;

public class VistaHistorialMedico {
    
    private Scanner sc;
    private ControladorHistorialMedico controladorHistorial;
    private ControladorPacientes controladorPacientes;
    private boolean actualizar=false;

    public VistaHistorialMedico() {
        sc = new Scanner(System.in);
        controladorHistorial = new ControladorHistorialMedico();
        controladorPacientes = new ControladorPacientes();
    }
    
    public void registrarHistorialMedico(){
        
        System.out.println("INGRESE CI DEL PACIENTE:");
        String cedulaPaciente = sc.nextLine();
        Paciente paciente = controladorPacientes.obtenerPaciente(cedulaPaciente);
        if (paciente == null) {
            System.out.println("El paciente ingresado no existe");
            return;
        }
        System.out.println(paciente.toString());
        
        
        System.out.println("INGRESE TIPO DE SANGRE:");
        String tipoSangre = sc.nextLine();
        System.out.println("INGRESE ENFERMEDADES CRONICAS:");
        String enfermedadesCronicas = sc.nextLine();
        System.out.println("INGRESE MEDICAMENTOS:");
        String medicamentos = sc.nextLine();
        System.out.println("INGRESE ALERGIAS:");
        String alergias = sc.nextLine();
        System.out.println("INGRESE CIRUGIAS:");
        String cirugias = sc.nextLine();
        
        //HistorialMedico historial = controladorHistorial.obtenerHistorial(cedulaPaciente);
        
        controladorHistorial.registrarHistorialMedico( new HistorialMedico(paciente,tipoSangre,enfermedadesCronicas, medicamentos, alergias, cirugias));
    }
    
    public void visualizarHistorialMedico(){
        System.out.println("-- VISUALIZAR HISTORIAL MEDICO --");
        System.out.println("INGRESE CI DEL PACIENTE:");
        String cedulaPaciente = sc.nextLine();
        HistorialMedico historial = controladorHistorial.obtenerHistorial(cedulaPaciente);
        
        if (historial == null) {
            System.out.println("El historial del paciente ingresado no existe");
            return;
        }
        System.out.println(historial.completetoString());
    }
            
    
    public void actualizarHistorialMedico(){
        System.out.println("-- ACTUALIZAR HISTORIAL MEDICO --");
        System.out.println("INGRESE CI DEL PACIENTE:");
        String cedulaPaciente = sc.nextLine();
        HistorialMedico historial = controladorHistorial.obtenerHistorial(cedulaPaciente);
       
        if (historial == null) {
            System.out.println("El historial del paciente ingresado no existe");
            return;
        }
        System.out.println(historial.completetoString());
        controladorHistorial.eliminarHistorial(cedulaPaciente);
        System.out.println("---- INGRESE LOS NUEVOS DATOS DEL HISTORIAL MEDICO ----");
        this.registrarHistorialMedico();
        System.out.println("La cita se ha modificado exitosamente");
    }   
            
    
    
    
    
    
}
