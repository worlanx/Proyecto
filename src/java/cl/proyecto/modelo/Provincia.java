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
public class Provincia {
    private int id;
    private String descripcion;
    private int region;

    public Provincia(int id, String descripcion, int region) {
        this.id = id;
        this.descripcion = descripcion;
        this.region = region;
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

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return String.format("%s-%s*%s", getId(),getDescripcion(),getRegion());
    }
    
    
}
