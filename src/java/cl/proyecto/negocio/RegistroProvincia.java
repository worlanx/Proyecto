/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Provincia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Laptop
 */
public class RegistroProvincia {
    
    public Provincia obtenerProvincia(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion,region_id from provincia where id = ?");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        Provincia provincia = null;
        while(rs.next())
        {  
            String descripcion = rs.getString("descripcion");
            int regionId = rs.getInt("region_id");            
            provincia = new Provincia(id, descripcion, regionId);
        }
        sm.close();
        return provincia;
    }
    
    public ArrayList<Provincia> listarProvincias() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion,region_id from provincia");
        ResultSet rs = sm.executeQuery();
        ArrayList<Provincia> provincias =new ArrayList<>();
        while(rs.next())
        { 
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            int regionId = rs.getInt("region_id");            
            Provincia provincia = new Provincia(id, descripcion, regionId);
        }
        sm.close();
        return provincias;
    }
}
