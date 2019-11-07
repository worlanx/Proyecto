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
    private ArrayList<DetalleEncuestador> detalleEncuestadores;

    public DetalleEncuestadores(ArrayList<DetalleEncuestador> detalleEncuestadores) {
        this.detalleEncuestadores = detalleEncuestadores;
    }

    public ArrayList<DetalleEncuestador> getDetalleEncuestadores() {
        return detalleEncuestadores;
    }

    public void setDetalleEncuestadores(ArrayList<DetalleEncuestador> detalleEncuestadores) {
        this.detalleEncuestadores = detalleEncuestadores;
    }
    
    
}
