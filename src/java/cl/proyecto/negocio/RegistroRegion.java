/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Region;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Laptop
 */
public class RegistroRegion {
    
    public Region obtenerRegion(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion,abreviatura, capital from region where id = ? and activo = 1");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        Region region = null;
        while(rs.next())
        {            
            String descripcion = rs.getString("descripcion");
            String abreviatura = rs.getString("abreviatura");
            String capital = rs.getString("capital");
            region = new Region(id, descripcion, abreviatura, capital);
        }
        sm.close();
        return region;
    }
    
    public ArrayList<Region> listarRegiones() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion,abreviatura,capital from region where activo = 1");
        ResultSet rs = sm.executeQuery();
        ArrayList<Region> regiones = new ArrayList<>();
        while(rs.next())
        {            
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            String abreviatura = rs.getString("abreviatura");
            String capital = rs.getString("capital");
            Region region = new Region(id, descripcion, abreviatura, capital);
            regiones.add(region);
        }
        sm.close();
        return regiones;
    }
    
}
