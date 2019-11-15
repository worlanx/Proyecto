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
public class Respuesta {
    private String id;
    private int pregunta_id;
    private int alternativa_id;    

    public Respuesta(String id, int pregunta_id, int alternativa_id) {
        this.id = id;
        this.pregunta_id = pregunta_id;
        this.alternativa_id = alternativa_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    

    
}
