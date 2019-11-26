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
public class Participante {
    private int id;
    private int encuesta_id;
    private String run_participante;

    public Participante(int id, int encuesta_id, String run_participante) {
        this.id = id;
        this.encuesta_id = encuesta_id;
        this.run_participante = run_participante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEncuesta_id() {
        return encuesta_id;
    }

    public void setEncuesta_id(int encuesta_id) {
        this.encuesta_id = encuesta_id;
    }

    public String getRun_participante() {
        return run_participante;
    }

    public void setRun_participante(String run_participante) {
        this.run_participante = run_participante;
    }    
}
