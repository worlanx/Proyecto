/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Comuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Laptop
 */
public class RegistroComuna {
    
    public Comuna obtenerComuna(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion,provincia_id from comuna where id = ?");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        Comuna comuna = null;
        while(rs.next())
        {  
            String descripcion = rs.getString("descripcion");
            int provinciaId = rs.getInt("provincia_id");            
            comuna = new Comuna(id, descripcion, provinciaId);
        }
        sm.close();
        return comuna;
    }
    
    public int[] obtenerIdsPorComuna(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select com,prov,reg from detalle_comuna where com = ?");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        int[] ids = new int[3];
        while(rs.next())
        {  
            ids[0] = id;
            ids[1] = rs.getInt("prov");      
            ids[2] = rs.getInt("reg");            
        }
        sm.close();
        return ids;
    }
    
    public ArrayList<Comuna> listarComuna() throws SQLException{
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion,provincia_id from comuna");
        ResultSet rs = sm.executeQuery();
        ArrayList<Comuna> comunas = new ArrayList<>();
        while(rs.next())
        {  
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            int provinciaId = rs.getInt("provincia_id");            
            Comuna comuna = new Comuna(id, descripcion, provinciaId);
            comunas.add(comuna);
        }
        sm.close();
        return comunas;
    }
}
