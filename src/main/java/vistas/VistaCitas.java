/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladorCitas;
import controladores.ControladorMedicos;
import controladores.ControladorPacientes;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import modelos.Cita;
import modelos.Medico;
import modelos.Paciente;

/**
 *
 * @author Frankz
 */
public class VistaCitas {
    private Scanner sc;
    private ControladorCitas controladorCitas;
    private ControladorPacientes controladorPacientes;
    private ControladorMedicos controladorMedicos;
    
    public VistaCitas() {
        sc = new Scanner(System.in);
        controladorCitas = new ControladorCitas();
        controladorPacientes = new ControladorPacientes();
        controladorMedicos = new ControladorMedicos();
    }
    
    public void listarCitas() {
        ArrayList<Cita> citas = controladorCitas.obtenerCitas();
        citas.forEach(cita -> {
            System.out.println(cita.completeToString());
        });
    }
    
    public void registrarCita() {        
        //con el ci se encuentra y se obtiene el objeto medico desde el csv
        System.out.println("INGRESE CI DEL PACIENTE:");
        String cedulaPaciente = sc.nextLine();
        Paciente paciente = controladorPacientes.obtenerPaciente(cedulaPaciente);
        if (paciente == null) {
            System.out.println("El paciente ingresado no existe");
            return;
        }
        System.out.println(paciente.toString());
        System.out.println("SELECCIONE UNA ESPECIALIDAD:");
        System.out.println("1.- Odontologia");
        System.out.println("2.- Endocrinologia");
        System.out.println("3.- Psiquiatria");
        System.out.println("4.- Medicina General");
        System.out.println("5.- Ginecologia");
        String especialidad = this.obtenerEspecialidad(Integer.parseInt(sc.nextLine()));
        System.out.println("---- MEDICOS ESPECIALIZADOS EN " + especialidad.toUpperCase() + " ----");
        ArrayList<Medico> medicos = controladorMedicos.obtenerMedicosPorEspecialidad(especialidad);
        medicos.forEach(medico -> {
            System.out.println(medico.toString());
        });
        System.out.println("INGRESE CI DEL MEDICO:");
        String cedulaMedico = sc.nextLine();        
        System.out.println("INGRESE FECHA DE CONSULTA [aaaa/mm/dd/hh:min]:");
        String fecha = sc.nextLine();
        System.out.println("INGRESE DESCRIPCION:");
        String descripcion = sc.nextLine();
        controladorCitas.registrarCita(new Cita(this.obtenerFecha(fecha), especialidad, descripcion, controladorMedicos.obtenerMedico(cedulaMedico), paciente));                   
    }
    public void registrarCita(String idCita) {        
        //con el ci se encuentra y se obtiene el objeto medico desde el csv
        System.out.println("INGRESE CI DEL PACIENTE:");
        String cedulaPaciente = sc.nextLine();
        Paciente paciente = controladorPacientes.obtenerPaciente(cedulaPaciente);
        if (paciente == null) {
            System.out.println("El paciente ingresado no existe");
            return;
        }
        System.out.println(paciente.toString());
        System.out.println("SELECCIONE UNA ESPECIALIDAD:");
        System.out.println("1.- Odontologia");
        System.out.println("2.- Endocrinologia");
        System.out.println("3.- Psiquiatria");
        System.out.println("4.- Medicina General");
        System.out.println("5.- Ginecologia");
        String especialidad = this.obtenerEspecialidad(Integer.parseInt(sc.nextLine()));
        System.out.println("---- MEDICOS ESPECIALIZADOS EN " + especialidad.toUpperCase() + " ----");
        ArrayList<Medico> medicos = controladorMedicos.obtenerMedicosPorEspecialidad(especialidad);
        medicos.forEach(medico -> {
            System.out.println(medico.toString());
        });
        System.out.println("INGRESE CI DEL MEDICO:");
        String cedulaMedico = sc.nextLine();        
        System.out.println("INGRESE FECHA DE CONSULTA [aaaa/mm/dd/hh:min]:");
        String fecha = sc.nextLine();
        System.out.println("INGRESE DESCRIPCION:");
        String descripcion = sc.nextLine();
        Cita cita = controladorCitas.obtenerCita(idCita);
        controladorCitas.eliminarCita(cita);
        controladorCitas.registrarCita(new Cita(idCita, this.obtenerFecha(fecha), especialidad, descripcion, controladorMedicos.obtenerMedico(cedulaMedico), paciente));                   
    }
    
    private LocalDateTime obtenerFecha(String fecha) {
        int anio = Integer.parseInt(fecha.substring(0,4));
        int mes = Integer.parseInt(fecha.substring(5, 7));
        int dia = Integer.parseInt(fecha.substring(8, 10));
        int hora = Integer.parseInt(fecha.substring(11, 13));
        int minutos = Integer.parseInt(fecha.substring(14));        
        
        return LocalDateTime.of(anio, mes, dia, hora, minutos);
    }
    
    private String obtenerEspecialidad(int opcion) {
        switch (opcion) {
            case 1:
                return "Odontologia";
            case 2: 
                return "Endocrinologia";
            case 3:
                return "Psiquiatria";
            case 4:
                return "Medicina General";
            case 5:
                return "Ginecologia";
            default:
                return "Medicina General";                
        }
    }
    
    public void modificarCita() {
        System.out.println("INGRESE EL ID DE LA CITA A MODIFICAR");
        String id = sc.nextLine();        
        Cita cita = controladorCitas.obtenerCita(id);
        if (cita == null) {
            System.out.println("La cita con id: " + id + " no existe.");
            return;
        }
        System.out.println(cita.toString());
        System.out.println("---- INGRESE LOS NUEVOS DATOS DE LA CITA ----");
        this.registrarCita(cita.getId());
        System.out.println("La cita se ha modificado exitosamente");
    }
    
    public void eliminarCita() {
        System.out.println("INGRESE EL ID DE LA CITA A ELIMINAR");
        String id = sc.nextLine();
        Cita cita = controladorCitas.obtenerCita(id);
        if (cita == null) {
            System.out.println("No existe cita con el id: " + cita.getId());
            return;
        }
        controladorCitas.eliminarCita(cita);
        System.out.println("La cita: " + cita.toString() + " Se ha eliminado correctamente");
    }
}
