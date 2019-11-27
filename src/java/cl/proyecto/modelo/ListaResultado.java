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
public class ListaResultado {
    private ArrayList<Resultado> lista;

    public ListaResultado() {
        this.lista = new ArrayList<>();
    }

    public ArrayList<Resultado> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Resultado> lista) {
        this.lista = lista;
    }
    
    public Resultado buscar(int alternativa_id)
    {
        for (Resultado resultado : lista) {
            if (resultado.getAlternativa_id() == alternativa_id) {
                return resultado;
            }
        }
        return null;
    }
    
    public void modificarCantidad(int alternativa_id, int cantidad)
    {
         for (Resultado resultado : lista) {
            if (resultado.getAlternativa_id() == alternativa_id) {
                resultado.setCantidad(cantidad);
            }
        }        
    }
    
}
