/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Worlan
 */
public class OrdenPago {
    private long id;
    private Date fecha;
    private int total;
    private ArrayList<DetallePago> detalle;

    public OrdenPago(long id, Date fecha, int total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        detalle = new ArrayList<>();
    }

    public OrdenPago(long id, Date fecha, int total, ArrayList<DetallePago> detalle) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.detalle = detalle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<DetallePago> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<DetallePago> detalle) {
        this.detalle = detalle;
    }
    
    
}
