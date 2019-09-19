/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.TipoTelefono;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Laptop
 */
public class RegistroTipoTelefono {
    
    public TipoTelefono obtenerTipoTelefono(int id) throws SQLException
    {        
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from tipo_telefono where id = ?");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        TipoTelefono tipoTelefono = null;
        while(rs.next())
        {  
            String descripcion = rs.getString("descripcion");
            tipoTelefono = new TipoTelefono(id, descripcion);
        }
        sm.close();
        return tipoTelefono;
    }
    
    public ArrayList<TipoTelefono> listarTipoTelefono() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from tipo_telefono");
        ResultSet rs = sm.executeQuery();
        ArrayList<TipoTelefono> tipoTelefonos = new ArrayList<>();
        while(rs.next())
        {  
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            TipoTelefono tipoTelefono = new TipoTelefono(id, descripcion);
            tipoTelefonos.add(tipoTelefono);
        }
        sm.close();
        return tipoTelefonos;
    }
}
