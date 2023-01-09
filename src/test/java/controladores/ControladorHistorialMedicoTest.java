/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package controladores;

import modelos.HistorialMedico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ControladorHistorialMedicoTest {
    ControladorHistorialMedico controladorHistorialMedico = null;
    
    public ControladorHistorialMedicoTest() {
        
    }
    

    @Before
    public void setUp() {
        controladorHistorialMedico = new ControladorHistorialMedico();
    }

    /**
     * Test of obtenerHistorial method, of class ControladorHistorialMedico.
     */
    @Test
    
    public void given_CIpaciente_when_obtenerHistorialMedico_then_notNull() {        
        HistorialMedico hitorialMedico = controladorHistorialMedico.obtenerHistorial("1202210934");
        
        assertNotNull(hitorialMedico);
    }
    
    @Test
    public void given_CIpaciente_when_obtenerCita_then_ok() {
       
        ControladorPacientes controladorPacientes = new ControladorPacientes();
        
        HistorialMedico historialEsperado = new HistorialMedico(controladorPacientes.obtenerPaciente("1202210934"),"O+", "Diabetes, Asma", "Nateglinida, Fluticasona","Asma alérgico","Apéndice");
        HistorialMedico historialObtenido = controladorHistorialMedico.obtenerHistorial("1202210934");
        
        assertEquals(historialEsperado.toString(), historialObtenido.toString());
    }
    

    @Test
    public void given_historialMedico_when_registrarHistorialMedico_then_ok() {
        
        ControladorPacientes controladorPacientes = new ControladorPacientes();

        HistorialMedico historialEsperado = new HistorialMedico(controladorPacientes.obtenerPaciente("1202210934"),"O+", "Diabetes, Asma", "Nateglinida, Fluticasona","Asma alérgico","Apéndice");
        controladorHistorialMedico.registrarHistorialMedico(historialEsperado);
        
        HistorialMedico historialGuardado = controladorHistorialMedico.obtenerHistorial("1202210934");
        
        assertEquals(historialEsperado.toString(), historialGuardado.toString());
        
        controladorHistorialMedico.eliminarHistorial("1202210934");
    }

    /**
     * Test of eliminarHistorial method, of class ControladorHistorialMedico.
     */

        @Test
    public void given_CIpaciente_when_eliminarHistorialMedico_then_ok() {
        
        ControladorPacientes controladorPacientes = new ControladorPacientes();
        
        HistorialMedico historialEsperado = new HistorialMedico(controladorPacientes.obtenerPaciente("1202210934"),"O+", "Diabetes, Asma", "Nateglinida, Fluticasona","Asma alérgico","Apéndice");
        controladorHistorialMedico.registrarHistorialMedico(historialEsperado);
        
        controladorHistorialMedico.eliminarHistorial("1202210934");
        
        assertNull(controladorHistorialMedico.obtenerHistorial("1202210934"));
    }
}
