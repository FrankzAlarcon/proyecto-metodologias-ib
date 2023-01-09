/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package controladores;

import java.time.LocalDateTime;
import modelos.Factura;
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
        Factura facturaEsperada = new Factura("2323", LocalDateTime.parse("2023-01-30T10:30"), "Odontologia", "Dolor de muelas", gestorMedicos.obtenerMedico("1250395702"), gestorPacientes.obtenerPaciente("1203434731"), 28, 30, 2);
        ControladorFactura controladorFactura = new ControladorFactura();
        controladorFactura.generarFactura(facturaEsperada);
        Factura facturaGuardada = controladorFactura.obtenerFactura("2323");
        assertEquals(facturaEsperada, facturaGuardada);
    }
    
    @Test
    public void given_factura_when_generarFactura_then_notNull() {
        ControladorMedicos gestorMedicos = new ControladorMedicos();
        ControladorPacientes gestorPacientes = new ControladorPacientes();
        Factura facturaEsperada = new Factura("2121", LocalDateTime.parse("2023-01-30T10:30"), "Odontologia", "Dolor de muelas", gestorMedicos.obtenerMedico("1250395702"), gestorPacientes.obtenerPaciente("1203434731"),21, 25, 3);
        ControladorFactura controladorFactura = new ControladorFactura();
        controladorFactura.generarFactura(facturaEsperada);
        
        assertNull(facturaEsperada);
    }
    
    
    
    
    
}
