/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import controladores.ControladorAdministradores;
import controladores.ControladorMedicos;

/**
 *
 * @author Frankz
 */
public class Login {

    private String cedula; // El usuario sera la cedula
    private String contrasenia;
    private String tipoUsuario;

    private ControladorAdministradores controladorAdministradores;
    private ControladorMedicos controladorMedicos;
    
    
    public Login(String usuario, String contrasenia) {
        this.cedula = usuario;
        this.contrasenia = contrasenia;
        this.controladorAdministradores = new ControladorAdministradores();
        this.controladorMedicos = new ControladorMedicos();
    }

    public boolean validarCredenciales(String role) {
        if (cedula.equals("") || contrasenia.equals("")) {
            System.out.println("Credenciales incorrectas");
            return false;
        }
        if (role == "Admin") {
            Administrador admin = controladorAdministradores.obtenerAdministrador(cedula);            
            if (admin == null) {
                System.out.println("Acceso Denegado");
                return false;
            }
            boolean esUsuarioCorrecto = admin.getCedula().equals(cedula);
            boolean esContraseniaCorrecta = admin.getContrasenia().equals(contrasenia);
            if ( esUsuarioCorrecto && esContraseniaCorrecta) {
                System.out.println("Acceso Exitoso");
                return true;
            } else {
                System.out.println("Acceso Denegado");
                return false;
            }   
        }
        if (role == "Medico") {
            Medico medico = controladorMedicos.obtenerMedico(cedula);
            if (medico == null) {
                System.out.println("Acceso Denegado");
                return false;
            }
            boolean esUsuarioCorrecto = medico.getCedula().equals(cedula);
            boolean esContraseniaCorrecta = medico.getContrasenia().equals(contrasenia);
            if ( esUsuarioCorrecto && esContraseniaCorrecta) {
                System.out.println("Acceso Exitoso");
                return true;
            } else {
                System.out.println("Acceso Denegado");
                return false;
            }
        }
        return false;
    }

    public String getCedula() {
        return cedula;
    }       
    
}
