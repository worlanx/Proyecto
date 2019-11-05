/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Telefono;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Laptop
 */
public class RegistroTelefono {
    
    public int agregar(Telefono telefono) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into detalle_telefono(numero,telefono_id,persona_id) values(?,?,?)");
        sm.setString(1, telefono.getNumero());
        sm.setInt(2, telefono.getTipo());
        sm.setInt(3, telefono.getPersona_id());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int desactivar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update detalle_telefono set activo = 0 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int activar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update detalle_telefono set activo = 1 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int modificar(Telefono telefono) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update detalle_telefono set numero = ?, telefono_id = ?, persona_id = ? where id = ? ");
        sm.setString(1, telefono.getNumero());
        sm.setInt(2, telefono.getTipo());
        sm.setInt(3, telefono.getPersona_id());
        sm.setInt(4, telefono.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
 
    public Telefono obtenerTelefono(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,numero,prefijo,telefono_id,persona_id from detalle_telefono where id = ? and activo = 1");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        Telefono telefono = null;
        while(rs.next())
        {
            String numero = rs.getString("numero");
            String prefijo = rs.getString("prefijo");
            int telefonoId = rs.getInt("telefono_id");
            int personaId = rs.getInt("persona_id");
            telefono = new  Telefono(id, numero, prefijo, telefonoId, personaId);
        }
        sm.close();
        return telefono;
    }
    
    public ArrayList<Telefono> listarTelefonoPorId(int persona_id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,numero,prefijo,telefono_id,persona_id from detalle_telefono where persona_id = ? and activo = 1");
        sm.setInt(1, persona_id);
        ResultSet rs = sm.executeQuery();
        ArrayList<Telefono> telefonos = new ArrayList<>();
        while(rs.next())
        {
            int id = rs.getInt("id");
            String numero = rs.getString("numero");
            String prefijo = rs.getString("prefijo");
            int telefonoId = rs.getInt("telefono_id");            
            Telefono telefono = new  Telefono(id, numero, prefijo, telefonoId, persona_id);
            telefonos.add(telefono);
        }
        sm.close();
        return telefonos;
    }
    
    
    public ArrayList<Telefono> listarTelefono() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,numero,prefijo,telefono_id,persona_id from detalle_telefono where activo = 1");
        ResultSet rs = sm.executeQuery();
        ArrayList<Telefono> telefonos = new ArrayList<>();
        while(rs.next())
        {
            int id = rs.getInt("id");
            String numero = rs.getString("numero");
            String prefijo = rs.getString("prefijo");
            int telefonoId = rs.getInt("telefono_id");
            int personaId = rs.getInt("persona_id");
            Telefono telefono = new  Telefono(id, numero, prefijo, telefonoId, personaId);
            telefonos.add(telefono);
        }
        sm.close();
        return telefonos;
    }
}
