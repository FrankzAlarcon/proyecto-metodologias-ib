/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Paciente;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Frankz
 */
public class ControladorPacientesTest {
    ControladorPacientes controladorPacientes = null;
    public ControladorPacientesTest() {
    }
    
    @Before
    public void setUp() {
        controladorPacientes = new ControladorPacientes();
    }

    /**
     * Test of obtenerPaciente method, of class ControladorPacientes.
     */
    @Test
    public void given_cedula_when_obtenerPaciente_then_notNull() {
        Paciente paciente = controladorPacientes.obtenerPaciente("1202210934");
        assertNotNull(paciente);        
    }
    
    @Test
    public void given_cedula_when_obtenerPaciente_then_ok() {
        //1202210934,Fanny Cando,57,fanny@correo.com
        Paciente pacienteEsperado = new Paciente("1202210934", "Fanny Cando", 57, "fanny@correo.com");
        Paciente pacienteObtenido = controladorPacientes.obtenerPaciente("1202210934");
        
        assertEquals(pacienteEsperado.toString(), pacienteObtenido.toString());
    }

    /**
     * Test of registrarPaciente method, of class ControladorPacientes.
     */
    @Test
    public void testRegistrarPaciente() {
        //0987654321,Pablo Mora,35,pablo@correo.com
        Paciente pacienteEsperado = new Paciente("0987654321", "Pablo Mora", 35, "pablo@correo.com");
        controladorPacientes.registrarPaciente(pacienteEsperado);       
        Paciente pacienteRegistrado = controladorPacientes.obtenerPaciente(pacienteEsperado.getCedula());
        
        assertEquals(pacienteEsperado.toString(), pacienteRegistrado.toString());
        controladorPacientes.eliminarPaciente(pacienteRegistrado);
    }    
}
