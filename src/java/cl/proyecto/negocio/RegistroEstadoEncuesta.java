/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.EstadoEncuesta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class RegistroEstadoEncuesta {
    public EstadoEncuesta obtenerEstadoEncuesta(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from estado_encuesta where id = ?");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        EstadoEncuesta estadoEncuesta = null;
        while(rs.next())
        {  
            String descripcion = rs.getString("descripcion");            
            estadoEncuesta = new EstadoEncuesta(id, descripcion);
        }
        sm.close();
        return estadoEncuesta;
    }
    
    public ArrayList<EstadoEncuesta> listarEstadoEncuesta() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from tipo_pregunta");
        ResultSet rs = sm.executeQuery();
        ArrayList<EstadoEncuesta> estadoEncuestas = new ArrayList<>();
        while(rs.next())
        {  
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            EstadoEncuesta estadoEncuesta = new EstadoEncuesta(id, descripcion);
            estadoEncuestas.add(estadoEncuesta);
        }
        sm.close();
        return estadoEncuestas;
    }
    
}
