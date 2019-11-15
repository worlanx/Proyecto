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
public class DetalleEncuestador {
    private int id;
    private Persona persona;
    private Encuesta encuesta;   
    private int persona_id;
    private int total;

    public DetalleEncuestador(int id, Encuesta encuesta, int persona_id, int total) {
        this.id = id;        
        this.encuesta = encuesta;
        this.persona_id = persona_id;
        this.total = total;
    }

    public DetalleEncuestador(int id, Persona persona, Encuesta encuesta) {
        this.id = id;
        this.persona = persona;
        this.encuesta = encuesta;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public int getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(int persona_id) {
        this.persona_id = persona_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    
    
}
