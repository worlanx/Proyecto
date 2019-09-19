/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.modelo;

import java.util.Date;

/**
 *
 * @author Laptop
 */
public class Persona {
    private int id;
    private int run;
    private char dv;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoManterno;
    private Date fechaNacimiento;
    private String email;
    private Telefono[] telefono;
    private CuentaUsuario cuenta;
    private Genero genero;
    private Direccion direccion;

    public Persona(int id, int run, char dv, String nombre, String apellidoPaterno, String apellidoManterno, Date fechaNacimiento, String email, Telefono[] telefono, CuentaUsuario cuenta, Genero genero, Direccion direccion) {
        this.id = id;
        this.run = run;
        this.dv = dv;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoManterno = apellidoManterno;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.cuenta = cuenta;
        this.genero = genero;
        this.direccion = direccion;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoManterno() {
        return apellidoManterno;
    }

    public void setApellidoManterno(String apellidoManterno) {
        this.apellidoManterno = apellidoManterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefono[] getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono[] telefono) {
        this.telefono = telefono;
    }

    public CuentaUsuario getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaUsuario cuenta) {
        this.cuenta = cuenta;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

   

   
    
    
}
