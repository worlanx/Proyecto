/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.TipoPregunta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class RegistroTipoPregunta {
    
    public TipoPregunta obtenerTipoPregunta(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from tipo_pregunta where id = ? and activo = 1");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        TipoPregunta tipoPregunta = null;
        while(rs.next())
        {  
            String descripcion = rs.getString("descripcion");            
            tipoPregunta = new TipoPregunta(id, descripcion);
        }
        sm.close();
        return tipoPregunta;
    }
    
    public ArrayList<TipoPregunta> listarTipoPregunta() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from tipo_pregunta where activo = 1");
        ResultSet rs = sm.executeQuery();
        ArrayList<TipoPregunta> tipoPreguntas = new ArrayList<>();
        while(rs.next())
        {  
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            TipoPregunta tipoPregunta = new TipoPregunta(id, descripcion);
            tipoPreguntas.add(tipoPregunta);
        }
        sm.close();
        return tipoPreguntas;
    }
}
