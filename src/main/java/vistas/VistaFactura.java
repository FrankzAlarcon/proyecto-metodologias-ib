/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import java.util.Scanner;
import modelos.Factura;
import controladores.ControladorCitasCompletadas;
import controladores.ControladorFactura;
import modelos.CitaCompletada;
/**
 *
 * @author Glender Miranda
 */
public class VistaFactura {
    private Scanner sc;
    Factura factura;
    ControladorFactura controladorFactura;
    ControladorCitasCompletadas controladorCitasCompletadas;
    CitaCompletada citaCompletada;

    public VistaFactura() {
        sc = new Scanner(System.in);
        controladorFactura = new ControladorFactura();
        controladorCitasCompletadas = new ControladorCitasCompletadas();
    }
    
    
    
    public boolean generarFactura(){
        //pedir la plata
        System.out.println("==============");
        System.out.println("    Pagos");
        System.out.println("==============");
        System.out.println("Ingrese id de cita completada a pagar");
        String idCita = sc.nextLine();
        citaCompletada = controladorCitasCompletadas.obtenerCita(idCita);
        
        System.out.println("Ingrese efectivo:");
        double efectivo = Double.parseDouble(sc.nextLine());
        double saldoDeVuelto = saldoVuelto(efectivo, citaCompletada.getPrecio());
        
        if(saldoDeVuelto < 0){
            System.out.println("¡El efectivo ingresado es insuficiente!");
            return false;
        }
        
        System.out.println("Saldo de vuelto: "+saldoDeVuelto);
        
        //llenar datos de factura
        factura = new Factura(Math.round(Math.random() * 10000000) + "", citaCompletada.getFecha(), 
                citaCompletada.getEspecialidad(), citaCompletada.getDescripcion(),
                citaCompletada.getMedico(), citaCompletada.getPaciente(), 
                citaCompletada.getPrecio(), efectivo, saldoDeVuelto);
        
        //generar factura
        System.out.println("Generando factura...");
        controladorFactura.generarFactura(factura);
        return true;
    }
    
    public double saldoVuelto(double efectivo, double precio){
        double saldoDevuelto = efectivo - precio;
        return saldoDevuelto;
    }
    
    
    
    
}
