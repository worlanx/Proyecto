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
public class CuentaUsuario {
    private int id;
    private String usuario;
    private String contrasenia;
    private int rol;
    private int persona;

    public CuentaUsuario(int id, String usuario, String contrasenia, int rol, int persona) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getPersona() {
        return persona;
    }

    public void setPersona(int persona) {
        this.persona = persona;
    }

    
    
}
