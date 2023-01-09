/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Administrador;
import org.hamcrest.core.Is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frankz
 */
public class ControladorAdministradoresTest {
    
    public ControladorAdministradoresTest() {
    }

    /**
     * Test of obtenerAdministrador method, of class ControladorAdministradores.
     */
    @Test
    public void given_cedula_when_obtenerAdministrador_then_notNull() {
        Administrador admin = new Administrador("1234567890", "admin", 30, "admin@admin.com", "admin");
        ControladorAdministradores controladorAdmins = new ControladorAdministradores();
        Administrador adminObtenido = controladorAdmins.obtenerAdministrador("1234567890");
        
        assertNotNull(adminObtenido);        
    }
    
    @Test
    public void given_cedula_when_obtenerAdministrador_then_ok() {
        Administrador admin = new Administrador("1234567890", "admin", 30, "admin@admin.com", "admin");
        ControladorAdministradores controladorAdmins = new ControladorAdministradores();
        Administrador adminObtenido = controladorAdmins.obtenerAdministrador("1234567890");
        
        assertEquals(admin.toString(), adminObtenido.toString());        
    }
    
    
}
