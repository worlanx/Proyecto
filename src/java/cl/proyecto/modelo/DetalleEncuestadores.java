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
public class DetalleEncuestadores {
    private int usuario_id;
    private ArrayList<DetalleEncuestador> detalleEncuestadores;

    public DetalleEncuestadores(int usuario_id, ArrayList<DetalleEncuestador> detalleEncuestadores) {
        this.usuario_id = usuario_id;
        this.detalleEncuestadores = detalleEncuestadores;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public ArrayList<DetalleEncuestador> getDetalleEncuestadores() {
        return detalleEncuestadores;
    }

    public void setDetalleEncuestadores(ArrayList<DetalleEncuestador> detalleEncuestadores) {
        this.detalleEncuestadores = detalleEncuestadores;
    }

}
