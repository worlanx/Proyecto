/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.modelo;

import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class Encuesta {
    private int id;
    private String titulo;
    private int valor;
    private EstadoEncuesta estado;
    private ArrayList<Pregunta> preguntas;

    public Encuesta(int id, String titulo, int valor, EstadoEncuesta estado, ArrayList<Pregunta> preguntas) {
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
        this.estado = estado;
        this.preguntas = preguntas;
    }

    public Encuesta(int id, String titulo, int valor, EstadoEncuesta estado) {
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public EstadoEncuesta getEstado() {
        return estado;
    }

    public void setEstado(EstadoEncuesta estado) {
        this.estado = estado;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    
   
   
}
