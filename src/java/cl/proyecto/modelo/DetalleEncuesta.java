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
    private Encuesta encuesta;
    private Respuesta respuesta;
    

    public DetalleEncuesta(int id, Encuesta encuesta, Respuesta respuesta) {
        this.id = id;
        this.encuesta = encuesta;
        this.respuesta = respuesta;        
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

   
    
}
