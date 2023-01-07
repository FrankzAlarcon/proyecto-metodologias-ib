/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import modelos.Medico;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Frankz
 */
public class ControladorMedicosTest {
    ControladorMedicos controladorMedicos = null;
    public ControladorMedicosTest() {
    }
    
    @Before
    public void setUp(){
        controladorMedicos = new ControladorMedicos();
    }

    /**
     * Test of obtenerMedico method, of class ControladorMedicos.
     */
    @Test
    public void given_cedula_when_obtenerMedico_then_notNull() {
        Medico medico = controladorMedicos.obtenerMedico("2200129381");
        assertNotNull(medico);  
    }
    
    @Test
    public void given_cedula_when_obtenerMedico_then_ok() {
        //220013981,Frankz Alarcon,20,Medicina General,frankz@correo.com,2200129381                
        Medico medicoEsperado = new Medico("2200129381", "Frankz Alarcon", 20, "Medicina General","frankz@correo.com", "2200129381");        
        Medico medicoObtenido = controladorMedicos.obtenerMedico("2200129381");
       
        assertEquals(medicoEsperado.toString(), medicoObtenido.toString());
    }

    /**
     * Test of obtenerMedicosPorEspecialidad method, of class ControladorMedicos.
     */
    @Test
    public void given_especialidad_when_obtenerMedicosPorEspecialidad_then_ok() {
        String especialidad = "Medicina General";
        ArrayList<Medico> medicos = controladorMedicos.obtenerMedicosPorEspecialidad(especialidad);
        
        medicos.forEach(medico -> {            
            assertEquals(medico.getEspecialidad(), especialidad);
        });
        
    }

    /**
     * Test of registrarMedico method, of class ControladorMedicos.
     */
    @Test
    public void given_Medico_when_registrarMedico_then_ok() {
        Medico medicoEsperado = new Medico("1029384756", "Lola Mora", 35, "Odontologia","lola@correo.com", "12345678");
        controladorMedicos.registrarMedico(medicoEsperado);       
        Medico medicoRegistrado = controladorMedicos.obtenerMedico("1029384756");
        
        assertEquals(medicoEsperado.toString(), medicoRegistrado.toString());
        
        controladorMedicos.eliminarMedico(medicoRegistrado);
    }
    
}
