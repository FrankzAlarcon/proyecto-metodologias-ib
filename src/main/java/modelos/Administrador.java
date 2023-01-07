/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

//import gestores.GestorCitasMedicas;
//import gestores.GestorMedicos;
//import gestores.GestorPacientes;

/**
 *
 * @author Frankz
 */
public class Administrador {
    
    private String cedula;
    private String nombre;
    private int edad;    
    private String correoElectronico;
    private String contrasenia;

    //private GestorMedicos gestorMedicos;
    //private GestorCitasMedicas gestorCitas;    

    public Administrador(String cedula, String nombre, int edad, String correoElectronico, String contrasenia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        //this.gestorCitas = new GestorCitasMedicas();
        //this.gestorMedicos = new GestorMedicos();
    }
   

    /*public GestorMedicos accederGestorMedicos(){
        return this.gestorMedicos;
    }
    
    public GestorCitasMedicas accederGestorCitasMedicas(){
        return this.gestorCitas;
    }*/

    public String getCedula() {
        return cedula;
    }
    
    public String getContrasenia() {
        return contrasenia;
    }

    @Override
    public String toString() {
        return cedula + "," + nombre + "," + edad + "," + correoElectronico + "," + contrasenia;
    }
}
