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
import modelos.CitaCompletada;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Frankz
 */
public class ControladorCitasCompletadasTest {
    ControladorCitasCompletadas controladorCitasCompletadas = null;
    public ControladorCitasCompletadasTest() {
    }
    
    @Before
    public void setUp() {
        controladorCitasCompletadas = new ControladorCitasCompletadas();
    }
    
    public ArrayList<CitaCompletada> obtenerCitasCompletadas() {
        FileReader fileReader = null;
        BufferedReader br = null;

        ArrayList<CitaCompletada> citas = new ArrayList<CitaCompletada>();

        ControladorMedicos gestorMedicos = new ControladorMedicos();
        ControladorPacientes gestorPacientes = new ControladorPacientes();
        try {
            fileReader = new FileReader("db/citas_completadas.txt");
            br = new BufferedReader(fileReader);
            String linea = br.readLine();
            CitaCompletada cita;
            while (linea != null) {                
                String[] datosCita = linea.split(",");
                cita = new CitaCompletada(
                        datosCita[0],
                        LocalDateTime.parse(datosCita[1]),
                        datosCita[2], datosCita[3],
                        Boolean.parseBoolean(datosCita[4]),
                        Double.parseDouble(datosCita[5]),
                        gestorMedicos.obtenerMedico(datosCita[6]),
                        gestorPacientes.obtenerPaciente(datosCita[7]));
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

    /**
     * Test of obtenerCitasCompletadas method, of class ControladorCitasCompletadas.
     */
    @Test
    public void given_none_when_obtenerCitasCompletadas_then_ok() {
        ArrayList<CitaCompletada> citasEsperadas = this.obtenerCitasCompletadas();
        ArrayList<CitaCompletada> citasObtenidas = controladorCitasCompletadas.obtenerCitasCompletadas();
        
        for (int i = 0; i < citasEsperadas.size(); i++) {
            assertEquals(citasEsperadas.get(i).toString(), citasObtenidas.get(i).toString());            
        }
    }

    /**
     * Test of obtenerCita method, of class ControladorCitasCompletadas.
     */
    @Test
    public void given_idCita_when_obtenerCita_then_notNull() {        
        CitaCompletada citaCompletada = controladorCitasCompletadas.obtenerCita("3287070");
        
        assertNotNull(citaCompletada);
    }
    
    @Test
    public void given_idCita_when_obtenerCita_then_ok() {
        //3287070,2023-02-02T16:00,Ginecologia,Chequeo General,true,40.0,2200129381,1202210934
        ControladorMedicos controladorMedicos = new ControladorMedicos();
        ControladorPacientes controladorPacientes = new ControladorPacientes();
        
        CitaCompletada citaEsperada = new CitaCompletada("3287070", LocalDateTime.parse("2023-02-02T16:00"), "Ginecologia", "Chequeo General", true, 40.0, controladorMedicos.obtenerMedico("2200129381"), controladorPacientes.obtenerPaciente("1202210934"));
        CitaCompletada citaObtenida = controladorCitasCompletadas.obtenerCita("3287070");
        
        assertEquals(citaEsperada.toString(), citaObtenida.toString());
    }
    
    @Test
    public void given_cedulaMedico_when_obtenerCitasPorMedico_then_ok() {
        ArrayList<CitaCompletada> citas = controladorCitasCompletadas.obtenerCitasPorMedico("2200129381");
        
        citas.forEach(cita -> {
            assertEquals("2200129381", cita.getMedico().getCedula());
        });
    }
    
    @Test
    public void given_cedulaMedico_y_cedulaPaciente_when_obtenerCitasMedicoPaciente_then_ok() {
        ArrayList<CitaCompletada> citas = controladorCitasCompletadas.obtenerCitasMedicoPaciente("2200129381", "1202210934");
        
        citas.forEach(cita -> {
            assertEquals("2200129381", cita.getMedico().getCedula());
            assertEquals("1202210934", cita.getPaciente().getCedula());
        });
    }

    /**
     * Test of registrarCita method, of class ControladorCitasCompletadas.
     */
    @Test
    public void given_citaCompletada_when_registrarCita_then_ok() {
        //8392530,2023-01-22T16:00,Medicina General,Chequeo General,true,30.0,2200129381,1202210934
        ControladorMedicos controladorMedicos = new ControladorMedicos();
        ControladorPacientes controladorPacientes = new ControladorPacientes();

        CitaCompletada citaEsperada = new CitaCompletada("8392530", LocalDateTime.parse("2023-01-22T16:00"), "Medicina General", "Chequeo General", true, 30, controladorMedicos.obtenerMedico("2200129381"), controladorPacientes.obtenerPaciente("1202210934"));
        controladorCitasCompletadas.registrarCita(citaEsperada);
        
        CitaCompletada citaGuardada = controladorCitasCompletadas.obtenerCita("8392530");
        
        assertEquals(citaEsperada.toString(), citaGuardada.toString());
        
        controladorCitasCompletadas.eliminarCita(citaGuardada);
    }    
}
