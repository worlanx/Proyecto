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
public class DetalleRespuestas {
    private int usuario_id;
    private int encuesta_id;
    private String run_participante;
    private ArrayList<Respuesta> detalleRespuesta;

    public DetalleRespuestas(int usuario_id, int encuesta_id, ArrayList<Respuesta> detalleRespuesta) {
        this.usuario_id = usuario_id;
        this.encuesta_id = encuesta_id;
        this.detalleRespuesta = detalleRespuesta;
    }

    public DetalleRespuestas(int usuario_id, int encuesta_id, String run_participante, ArrayList<Respuesta> detalleRespuesta) {
        this.usuario_id = usuario_id;
        this.encuesta_id = encuesta_id;
        this.run_participante = run_participante;
        this.detalleRespuesta = detalleRespuesta;
    }
    

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getEncuesta_id() {
        return encuesta_id;
    }

    public void setEncuesta_id(int encuesta_id) {
        this.encuesta_id = encuesta_id;
    }

    public ArrayList<Respuesta> getDetalleRespuesta() {
        return detalleRespuesta;
    }

    public void setDetalleRespuesta(ArrayList<Respuesta> detalleRespuesta) {
        this.detalleRespuesta = detalleRespuesta;
    }

    public String getRun_participante() {
        return run_participante;
    }

    public void setRun_participante(String run_participante) {
        this.run_participante = run_participante;
    }
}
