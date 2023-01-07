/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

//import gestores.GestorCitasMedicas;
import java.util.ArrayList;
import java.util.Scanner;
import controladores.ControladorMedicos;

/**
 *
 * @author Frankz
 */
public class Medico {

    private String cedula;
    private String nombre;
    private int edad;
    private String especialidad;
    private String correoElectronico;
    private String contrasenia;    

    private ControladorMedicos medicoPersistencia;

    public Medico(String cedula, String nombre, int edad, String especialidad, String correoElectronico, String contrasenia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.especialidad = especialidad;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.medicoPersistencia = new ControladorMedicos();
    }

    public void registrar() {
        this.medicoPersistencia.registrarMedico(this);
    }

    public void eliminar() {
        this.medicoPersistencia.eliminarMedico(this);
    }

    public void modificar(Medico medicoModificado) {        
        this.eliminar();
        medicoModificado.registrar();
    }

    public String getCedula() {
        return cedula;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    @Override
    public String toString() {
        return cedula + "," + nombre + "," + edad + "," + especialidad + ","+ correoElectronico + "," + contrasenia;
    }

}
