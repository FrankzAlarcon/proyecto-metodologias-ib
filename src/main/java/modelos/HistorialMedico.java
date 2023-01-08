/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import controladores.ControladorHistorialMedico;

public class HistorialMedico {
    private Paciente paciente;
    private String tipoSangre;
    private String enfermedadesCronicas;
    private String medicamentos;
    private String alergias;
    private String cirugias;
    
    private ControladorHistorialMedico historialPersistencia;

    public HistorialMedico(Paciente paciente, String tipoSangre, String enfermedadesCronicas, String medicamentos, String alergias, String cirugias) {
        this.paciente = paciente;
        this.tipoSangre = tipoSangre;
        this.enfermedadesCronicas = enfermedadesCronicas;
        this.medicamentos = medicamentos;
        this.alergias = alergias;
        this.cirugias = cirugias;
        this.historialPersistencia = new ControladorHistorialMedico();
    }



    public void registrar(){
        this.historialPersistencia.registrarHistorialMedico(this);
    }
    
    public void actualizar(){
        
    }

    @Override
    public String toString() {
        return paciente + ";" + tipoSangre + ";" + enfermedadesCronicas + ";" + medicamentos + ";" + alergias + ";" + cirugias ;
    }
    
    public String completetoString() {
        return "Paciente=" + paciente + "; tipoSangre=" + tipoSangre + "; enfermedadesCronicas=" + enfermedadesCronicas + "; medicamentos=" + medicamentos + "; alergias=" + alergias + "; cirugias=" + cirugias;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getCirugias() {
        return cirugias;
    }
    
    
    
}
