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
public class Region {
    private int id;
    private String descripcion;
    private String abreviatura;
    private String capital;

    public Region(int id, String descripcion, String abreviatura, String capital) {
        this.id = id;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.capital = capital;
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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
    
    
}
