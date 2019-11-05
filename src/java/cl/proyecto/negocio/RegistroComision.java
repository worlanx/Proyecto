/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Comision;
import cl.proyecto.modelo.DetalleEncuestador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class RegistroComision {
    
    public int agregar(Comision comision) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into comision(cantidad,total,detalle_id) values(?,?,?)");
        sm.setInt(1, comision.getCantidad());
        sm.setInt(2, comision.getTotal());
        sm.setInt(3, comision.getDetalle().getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int eliminar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update comision set activo = 0 where id = ?");
        sm.setInt(1, id);       
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int modificar(Comision comision) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update comision set cantidad = ?, total = ?, detalle_id = ? where id = ?");
        sm.setInt(1, comision.getCantidad());
        sm.setInt(2, comision.getTotal());
        sm.setInt(3, comision.getDetalle().getId());
        sm.setInt(4, comision.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public Comision obtenerComision(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select cantidad, total, detalle_id from comision where id = ? and activo = 1");
        sm.setInt(1, id);
        Comision comision = null;
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
            
            int cantidad  = rs.getInt("cantidad");
            int total = rs.getInt("total");
            int detalle_id = rs.getInt("detalle_id");
            
            RegistroDetalleEncuestador registroDetalleEncuestador = new RegistroDetalleEncuestador();
            DetalleEncuestador detalleEncuestador = registroDetalleEncuestador.obtenerDetalleEncuestador(detalle_id);       
            
            comision = new Comision(id, cantidad, total, detalleEncuestador);
        }
        sm.close();
        return comision;
    }
    
    public ArrayList<Comision> listarComisiones() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select cantidad, total, detalle_id from comision where activo = 1");
        ArrayList<Comision> comisiones = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
         
            int id = rs.getInt("id");
            int cantidad  = rs.getInt("cantidad");
            int total = rs.getInt("total");
            int detalle_id = rs.getInt("detalle_id");
            
            RegistroDetalleEncuestador registroDetalleEncuestador = new RegistroDetalleEncuestador();
            DetalleEncuestador detalleEncuestador = registroDetalleEncuestador.obtenerDetalleEncuestador(detalle_id); 
            
            Comision comision = new Comision(id, cantidad, total, detalleEncuestador);
            comisiones.add(comision);            
        }
        sm.close();
        return comisiones;        
    }
}
