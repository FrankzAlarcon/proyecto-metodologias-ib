/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.util.Scanner;
import modelos.Login;

/**
 *
 * @author Frankz
 */
public class VistaLogin {
    private Scanner sc;
    private Login login;
    public VistaLogin() {
        sc = new Scanner(System.in);
        login = null;        
    }
    
    public String seleccionarTipoUsuario() {
        System.out.println("SELECCIONA TU TIPO DE USUARIO");
        System.out.println("1.- Admin");
        System.out.println("2.- Medico");
        int opc = Integer.parseInt(sc.nextLine());
        
        switch(opc) {
            case 1:
                return "Admin";
            case 2:
                return "Medico";
            default:
                return "Medico";
        }
    }
    
    public boolean ingresarCredenciales(String role) {
        System.out.println("INGRESA TU NUMERO DE CEDULA");
        String cedula = sc.nextLine();
        
        System.out.println("INGRESA TU CONTRASEÑA");
        String password = sc.nextLine();
        
        this.login = new Login(cedula, password);
        return login.validarCredenciales(role);
    }

    public Login getLogin() {
        return login;
    }
    
    
}
