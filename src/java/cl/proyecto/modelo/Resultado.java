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
public class Resultado {
    private int pregunta_id;
    private int alternativa_id;
    private int encuesta_id;
    private int cantidad;
    private String titulo;
    private String descripcion;

    public Resultado(int pregunta_id, int alternativa_id, int encuesta_id, int cantidad, String titulo, String descripcion) {
        this.pregunta_id = pregunta_id;
        this.alternativa_id = alternativa_id;
        this.encuesta_id = encuesta_id;
        this.cantidad = cantidad;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getPregunta_id() {
        return pregunta_id;
    }

    public void setPregunta_id(int pregunta_id) {
        this.pregunta_id = pregunta_id;
    }

    public int getAlternativa_id() {
        return alternativa_id;
    }

    public void setAlternativa_id(int alternativa_id) {
        this.alternativa_id = alternativa_id;
    }

    public int getEncuesta_id() {
        return encuesta_id;
    }

    public void setEncuesta_id(int encuesta_id) {
        this.encuesta_id = encuesta_id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
}
