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
public class Direccion {
    private int id;
    private String descripcion;
    private int comuna;

    public Direccion(int id, String descripcion, int comuna) {
        this.id = id;
        this.descripcion = descripcion;
        this.comuna = comuna;
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

    public int getComuna() {
        return comuna;
    }

    public void setComuna(int comuna) {
        this.comuna = comuna;
    }
    
}
