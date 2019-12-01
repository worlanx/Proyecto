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
public class Resumen {
    private int id;
    private int run;
    private char dv;
    private String nombre;
    private String paterno;
    private String materno;
    private int total;

    public Resumen(int id, int run, char dv, String nombre, String paterno, String materno, int total) {
        this.id = id;
        this.run = run;
        this.dv = dv;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public char getDv() {
        return dv;
    }

    public void setDv(char dv) {
        this.dv = dv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

   
    
    
}
