/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.time.LocalDateTime;
import java.time.Month;
import modelos.Login;

/**
 *
 * @author Frankz
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VistaLogin vistaLogin = new VistaLogin();
        System.out.println("=============================================");
        System.out.println("-------SISTEMA GESTOR DE CITAS MEDICAS-------");
        System.out.println("=============================================");
        String tipoUsuario = vistaLogin.seleccionarTipoUsuario();
        if (tipoUsuario == "Admin") {
            VistaAdministrador vistaAdministrador = new VistaAdministrador();
            boolean estaLogeado = vistaLogin.ingresarCredenciales(tipoUsuario);
            if (estaLogeado) {
                vistaAdministrador.opcionesAdministrador();
            } else {
                System.out.println("Credenciales invalidas");
            }
        } else if (tipoUsuario == "Medico"){
            VistaMedico vistaMedico = new VistaMedico();
            boolean estaLogeado = vistaLogin.ingresarCredenciales(tipoUsuario);
            if (estaLogeado) {
                vistaMedico.opcionesMedico();
            }
        }        
        
    }
    
}
