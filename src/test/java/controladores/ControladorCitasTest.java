/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelos.Cita;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Frankz
 */
public class ControladorCitasTest {

    ControladorCitas controladorCitas = null;

    public ControladorCitasTest() {
    }

    /**
     * Test of obtenerCitas method, of class ControladorCitas.
     */
    @Before
    public void setUp() {
        controladorCitas = new ControladorCitas();
    }

    private ArrayList<Cita> obtenerCitas() {
        FileReader fileReader = null;
        BufferedReader br = null;

        ArrayList<Cita> citas = new ArrayList<Cita>();

        ControladorMedicos gestorMedicos = new ControladorMedicos();
        ControladorPacientes gestorPacientes = new ControladorPacientes();
        try {
            fileReader = new FileReader("db/citas.txt");
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            Cita cita;
            while (linea != null) {
                String[] datosCita = linea.split(",");
                cita = new Cita(datosCita[0], LocalDateTime.parse(datosCita[1]), datosCita[2], datosCita[3], gestorMedicos.obtenerMedico(datosCita[4]), gestorPacientes.obtenerPaciente(datosCita[5]));
                citas.add(cita);
                linea = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ControladorBuffers.cerrarBuffers(fileReader, br);
        }
        return citas;
    }

    @Test
    public void given_none_when_obtenerCitas_then_ok() {
        ArrayList<Cita> citasObtenidas = controladorCitas.obtenerCitas();
        ArrayList<Cita> citasTxt = this.obtenerCitas();
        for (int i = 0; i < citasObtenidas.size(); i++) {
            assertEquals(citasTxt.get(i).toString(), citasObtenidas.get(i).toString());
        }
    }

    /**
     * Test of obtenerCita method, of class ControladorCitas.
     */
    @Test
    public void given_id_when_obtenerCita_then_notNull() {
        Cita cita = controladorCitas.obtenerCita("9092841");
        assertNotNull(cita);
    }

    @Test
    public void given_id_when_obtenerCita_then_ok() {
        //9092841,2023-01-22T09:30,Medicina General,Chequeo de rutina,2200129381,1202210934
        ControladorMedicos controladorMedicos = new ControladorMedicos();
        ControladorPacientes controladorPacientes = new ControladorPacientes();
        Cita citaEsperada = new Cita("9092841", LocalDateTime.parse("2023-01-22T09:30"), "Medicina General", "Chequeo de rutina", controladorMedicos.obtenerMedico("2200129381"), controladorPacientes.obtenerPaciente("1202210934"));
        
        Cita citaEncontrada = controladorCitas.obtenerCita("9092841");
        assertEquals(citaEsperada.toString(), citaEncontrada.toString());                
    }
    
    @Test
    public void given_cedula_when_obtenerCitasMedico_then_ok() {
        ArrayList<Cita> citas = controladorCitas.obtenerCitasDeMedico("2200129381");
        
        citas.forEach(cita -> {
            assertEquals("2200129381", cita.getMedico().getCedula());
        });
    }
    
    @Test
    public void given_cedulaMedico_y_cedulaPaciente_when_obtenerCitasMedicoPaciente_then_ok() {
        ArrayList<Cita> citas = controladorCitas.obtenerCitasMedicoPaciente("2200129381", "1202210934");
        
        citas.forEach(cita -> {
            assertEquals("2200129381", cita.getMedico().getCedula());
            assertEquals("1202210934", cita.getPaciente().getCedula());            
        });
    }
    
    /**
     * Test of registrarCita method, of class ControladorCitas.
     */
    @Test
    public void given_cita_when_registrarCita_then_ok() {
        //1234567,2023-01-30T10:30,Medicina General,Prueba,2200129381,1202210934
        ControladorMedicos controladorMedicos = new ControladorMedicos();
        ControladorPacientes controladorPacientes = new ControladorPacientes();
        Cita citaEsperada = new Cita("1234567", LocalDateTime.parse("2023-01-30T10:30"), "Medicina General", "Prueba", controladorMedicos.obtenerMedico("2200129381"), controladorPacientes.obtenerPaciente("1202210934"));
        controladorCitas.registrarCita(citaEsperada);
        
        Cita citaGuardada = controladorCitas.obtenerCita("1234567");
        assertEquals(citaEsperada.toString(), citaGuardada.toString());
        
        controladorCitas.eliminarCita(citaGuardada);        
    }

    /**
     * Test of eliminarCita method, of class ControladorCitas.
     */
    @Test
    public void given_cita_when_eliminarCita_then_ok() {
        //1234567,2023-01-30T10:30,Medicina General,Prueba,2200129381,1202210934
        ControladorMedicos controladorMedicos = new ControladorMedicos();
        ControladorPacientes controladorPacientes = new ControladorPacientes();
        Cita citaEsperada = new Cita("1234567", LocalDateTime.parse("2023-01-30T10:30"), "Medicina General", "Prueba", controladorMedicos.obtenerMedico("2200129381"), controladorPacientes.obtenerPaciente("1202210934"));
        controladorCitas.registrarCita(citaEsperada);                        
        
        controladorCitas.eliminarCita(citaEsperada);
        
        assertNull(controladorCitas.obtenerCita("1234567"));
    }

}
