/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Genero;
import cl.proyecto.modelo.TipoTelefono;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Laptop
 */
public class RegistroGenero {
    
    public Genero obtenerGenero(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from genero where id = ?");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        Genero genero = null;
        while(rs.next())
        {  
            String descripcion = rs.getString("descripcion");
            genero = new Genero(id, descripcion);
        }
        sm.close();
        return genero;
    }
    
    public ArrayList<Genero> listarGenero() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from genero where");
        ResultSet rs = sm.executeQuery();
        ArrayList<Genero> generos = new ArrayList<>();
        while(rs.next())
        {  
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            Genero genero = new Genero(id, descripcion);
            generos.add(genero);
        }
        sm.close();
        return generos;
    }
}
