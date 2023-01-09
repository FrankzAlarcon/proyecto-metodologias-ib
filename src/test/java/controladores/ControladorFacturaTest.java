/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package controladores;

import java.time.LocalDateTime;
import modelos.Factura;
import modelos.Medico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Glender Miranda
 */
public class ControladorFacturaTest {
    
    public ControladorFacturaTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void given_factura_when_generarFactura_then_ok() {
        ControladorMedicos gestorMedicos = new ControladorMedicos();
        ControladorPacientes gestorPacientes = new ControladorPacientes();
        Medico medico = gestorMedicos.obtenerMedico("2200129381");        
        Factura facturaEsperada = new Factura("2323", LocalDateTime.parse("2023-01-30T10:30"), "Odontologia", "Dolor de muelas", medico, gestorPacientes.obtenerPaciente("1202210934"), 28, 30, 2);
        ControladorFactura controladorFactura = new ControladorFactura();
        controladorFactura.generarFactura(facturaEsperada);
        Factura facturaGuardada = controladorFactura.obtenerFactura("2323");
        assertEquals(facturaEsperada.toString(), facturaGuardada.toString());
    }                   
}
