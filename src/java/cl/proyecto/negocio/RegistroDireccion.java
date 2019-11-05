/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Direccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Laptop
 */
public class RegistroDireccion {
    
    public int agregar(Direccion direccion) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into detalle_direccion(descripcion,detalle,comuna_id) values(?,?,?)");
        sm.setString(1, direccion.getDescripcion());
        sm.setString(2, direccion.getDetalle());
        sm.setInt(3, direccion.getComuna());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int eliminar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update detalle_direccion set activo = 0 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int modificar(Direccion direccion) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update detalle_direccion set descripcion = ?, detalle = ?,comuna_id = ? where id = ?");
        sm.setString(1, direccion.getDescripcion());
        sm.setString(2, direccion.getDetalle());
        sm.setInt(3, direccion.getComuna());
        sm.setInt(4, direccion.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public Direccion obtenerDireccion(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id ,descripcion, detalle, comuna_id from detalle_direccion where id = ? and activo = 1");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        Direccion direccion = null;
        while(rs.next())
        {  
            String descripcion = rs.getString("descripcion");
            String detalle = rs.getString("detalle");
            int comunaId = rs.getInt("comuna_id");            
            direccion = new Direccion(id, descripcion, detalle, comunaId);
        }
        sm.close();
        return direccion;
    }
    
    public Direccion obtenerDireccionporDetalle(String descripcion, String detalle, int comuna_id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id from detalle_direccion where descripcion = ? and detalle = ? and comuna_id = ? and activo = 1");
        sm.setString(1, descripcion);
        sm.setString(2, detalle);
        sm.setInt(3, comuna_id);
        ResultSet rs = sm.executeQuery();
        Direccion direccion = null;
        while(rs.next())
        {              
            int id = rs.getInt("id");
            direccion = new Direccion(id, descripcion, detalle, comuna_id);
        }
        sm.close();
        return direccion;
    }
    
    public ArrayList<Direccion> listarDirecciones() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion, detalle, comuna_id from detalle_direccion where activo = 1");
        ResultSet rs = sm.executeQuery();
        ArrayList<Direccion> direcciones = new ArrayList<>();
        while(rs.next())
        {  
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            String detalle = rs.getString("detalle");
            int comunaId = rs.getInt("comuna_id");            
            Direccion direccion = new Direccion(id, descripcion, detalle,comunaId);
            direcciones.add(direccion);
        }
        sm.close();
        return direcciones;
    }
}
