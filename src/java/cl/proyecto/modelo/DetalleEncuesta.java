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
public class DetalleEncuesta {
    private int id;
    private int persona_id;
    private int encuesta_id;
    private Respuesta respuesta;

    public DetalleEncuesta(int id, int persona_id, int encuesta_id, Respuesta respuesta) {
        this.id = id;
        this.persona_id = persona_id;
        this.encuesta_id = encuesta_id;
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(int persona_id) {
        this.persona_id = persona_id;
    }

    public int getEncuesta_id() {
        return encuesta_id;
    }

    public void setEncuesta_id(int encuesta_id) {
        this.encuesta_id = encuesta_id;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    
}
