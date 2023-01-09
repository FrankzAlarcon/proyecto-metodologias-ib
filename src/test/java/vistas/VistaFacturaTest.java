/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package vistas;

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
public class VistaFacturaTest {
    
    public VistaFacturaTest() {
    }
    
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void given_twoDoubles_when_saldoVuelto_then_ok() {
        VistaFactura vistaFactura = new VistaFactura();
        double esperado = 3;
        double actual = vistaFactura.saldoVuelto(35, 32);
        assertEquals(esperado, actual,0.01);
    }

    
}
