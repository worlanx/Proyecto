/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.modelo;

/**
 *
 * @author Laptop
 */
public class Comuna {
    private int id;
    private String descripcion;
    private int privencia;

    public Comuna(int id, String descripcion, int privencia) {
        this.id = id;
        this.descripcion = descripcion;
        this.privencia = privencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrivencia() {
        return privencia;
    }

    public void setPrivencia(int privencia) {
        this.privencia = privencia;
    }
    
    @Override
    public String toString() {
        return String.format("%s-%s*%s", getId(),getDescripcion().replace("'", "´"),getPrivencia());
    }
    
}
