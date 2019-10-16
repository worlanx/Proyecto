/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.modelo;

/**
 *
 * @author Worlan
 */
public class Comision {
    private int id;
    private int cantidad;
    private int total;
    private DetalleEncuestador detalle;
   
    public Comision(int id, int cantidad, int total, DetalleEncuestador detalle) {
        this.id = id;
        this.cantidad = cantidad;
        this.total = total;
        this.detalle = detalle;        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DetalleEncuestador getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleEncuestador detalle) {
        this.detalle = detalle;
    }
    
}
