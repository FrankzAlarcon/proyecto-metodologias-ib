/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import controladores.ControladorFactura;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author Glender Miranda
 */
public class Factura {

    private String idFactura;
    private LocalDateTime fecha;
    private String especialidad;
    private String descripcion;
    private ControladorFactura controladorFactura;
    private Medico medico;
    private Paciente paciente;
    private double precio;
    private double efectivo;
    private double saldoDevuelto;

    public Factura(String idFactura, LocalDateTime fecha, String especialidad, String descripcion, Medico medico, Paciente paciente, double precio, double efectivo, double saldoDevuelto) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.especialidad = especialidad;
        this.descripcion = descripcion;
        this.controladorFactura = new ControladorFactura();
        this.medico = medico;
        this.paciente = paciente;
        this.precio = precio;
        this.efectivo = efectivo;
        this.saldoDevuelto = saldoDevuelto;
    }

    
    public void generar() {
        this.controladorFactura.generarFactura(this);
    }

    @Override
    public String toString() {
        return idFactura + "," + fecha.toString() + "," + especialidad + "," 
                + descripcion + ","+ medico.getCedula()+ "," 
                + paciente.getCedula()+","+precio+","+efectivo+","+saldoDevuelto;
    }

    
    
    public String CompleteToString() {
        return "=============\n"
                + " FACTURA \n"
                + "============\n"
                + "id Factura: "+idFactura+"\n"
                + "Fecha: "+fecha.toString()+"\n"
                + "Especialidad: "+especialidad+"\n"
                + "Descripcion: "+descripcion+"\n"
                + "Medico: "+medico.getNombre()+"\n"
                + "Paciente: "+paciente.getNombre()+"\n"
                + "Precio: "+precio+"\n"
                + "Efectivo: "+efectivo+"\n"
                + "Saldo a devolver: "+saldoDevuelto+"\n";
    }

}
