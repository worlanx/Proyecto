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
public class Pregunta {
    private int id;
    private String titulo;
    private TipoPregunta tipo;
    private ArrayList<Alternativa> alternativas;
    private int encuesta_id;

    public Pregunta(int id, String titulo, TipoPregunta tipo, ArrayList<Alternativa> alternativas, int encuesta_id) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.alternativas = alternativas;
        this.encuesta_id = encuesta_id;
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

    public TipoPregunta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPregunta tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(ArrayList<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public int getEncuesta_id() {
        return encuesta_id;
    }

    public void setEncuesta_id(int encuesta_id) {
        this.encuesta_id = encuesta_id;
    }
    
    
}
