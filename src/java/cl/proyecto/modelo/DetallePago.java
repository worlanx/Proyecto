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
public class DetallePago {
    private int id;
    private int monto;
    private long orden_pago_id;
    private int realizada_id;

    public DetallePago(int id, int monto, long orden_pago_id, int realizada_id) {
        this.id = id;
        this.monto = monto;
        this.orden_pago_id = orden_pago_id;
        this.realizada_id = realizada_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public long getOrden_pago_id() {
        return orden_pago_id;
    }

    public void setOrden_pago_id(long orden_pago_id) {
        this.orden_pago_id = orden_pago_id;
    }

    public int getRealizada_id() {
        return realizada_id;
    }

    public void setRealizada_id(int realizada_id) {
        this.realizada_id = realizada_id;
    }

    
}
