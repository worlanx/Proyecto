/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.OrdenPago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Worlan
 */
public class RegistroOrdenPago {

    
    public int agregar(OrdenPago ordenPago) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into orden_pago(id,fecha,total) values(?,?,?)");
        sm.setLong(1, ordenPago.getId());
        sm.setDate(2, new java.sql.Date(ordenPago.getFecha().getTime()));
        sm.setInt(3, ordenPago.getTotal());
        int resultado = sm.executeUpdate();
        sm.close();
        return resultado;
    }
    
    public ArrayList<OrdenPago> listarOrden(int encuestador_id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select o.id, o.fecha, o.total from orden_pago as o left join detalle_pago as d on o.id = d.orden_pago_id left join realizada as r on d.realizada_id = r.id where persona_run = ? group by o.id");
        sm.setInt(1, encuestador_id);
        ResultSet rs = sm.executeQuery();
        ArrayList<OrdenPago> ordenPagos = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            Date fecha = rs.getDate("fecha");
            int total = rs.getInt("total");
            OrdenPago ordenPago = new OrdenPago(id, fecha, total);
            ordenPagos.add(ordenPago);
        }
        sm.close();
        return ordenPagos;
    }
    
    public OrdenPago listarOrdenOtro(long id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT id, fecha, total FROM orden_pago where id = ?");        
        sm.setLong(1, id);
        ResultSet rs = sm.executeQuery();
        OrdenPago ordenPago = null;
        while (rs.next()) {            
            Date fecha = rs.getDate("fecha");
            int total = rs.getInt("total");
            ordenPago = new OrdenPago(id, fecha, total);            
        }
        sm.close();
        return ordenPago;
    }
}
