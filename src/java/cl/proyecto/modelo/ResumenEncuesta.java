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
public class ResumenEncuesta {
    private int cantidad;
    private String titulo;
    private int valor;
    private int totalPago;

    public ResumenEncuesta(int cantidad, String titulo, int valor, int totalPago) {
        this.cantidad = cantidad;
        this.titulo = titulo;
        this.valor = valor;
        this.totalPago = totalPago;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(int totalPago) {
        this.totalPago = totalPago;
    }
}
