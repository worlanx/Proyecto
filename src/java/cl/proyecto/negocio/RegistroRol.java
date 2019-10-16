/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Laptop
 */
public class RegistroRol {
    
    public Rol obtenerRol(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from rol where id = ?");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        Rol rol = null;
        while(rs.next())
        {  
            String descripcion = rs.getString("descripcion");
            rol = new Rol(id, descripcion);
        }
        sm.close();
        return rol;
    }
    
    public ArrayList<Rol> listarRoles() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from rol");
        ResultSet rs = sm.executeQuery();
        ArrayList<Rol> roles = new ArrayList<>();
        while(rs.next())
        {  
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            Rol rol = new Rol(id, descripcion);
            roles.add(rol);
        }
        sm.close();
        return roles;
    }
}
