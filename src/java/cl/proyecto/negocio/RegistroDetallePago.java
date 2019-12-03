/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.DetallePago;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Worlan
 */
public class RegistroDetallePago {
    
    public int agregar(DetallePago detallePago) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into detalle_pago(monto, orden_pago_id, realizada_id) values(?,?,?) ");
        sm.setInt(1, detallePago.getMonto());
        sm.setLong(2, detallePago.getOrden_pago_id());
        sm.setInt(3, detallePago.getRealizada_id());
        int resultado = sm.executeUpdate();
        sm.close();
        return resultado;
    }
    
    
//    public ArrayList<DetallePago> listarPago(Date desde, Date hasta) throws SQLException {
//        PreparedStatement sm = Conexion.getConnection().prepareCall("select * from ordenes where cast(fecha as date) between ? and ?");
//        sm.setDate(1, new java.sql.Date(desde.getTime()));
//        sm.setDate(2, new java.sql.Date(hasta.getTime()));
//        ResultSet rs = sm.executeQuery();
//        ArrayList<OrdenPago> ordenPagos = new ArrayList<>();
//        while (rs.next()) {
//            long id = rs.getLong("id");
//            Date fecha = rs.getDate("fecha");
//            int total = rs.getInt("total");
//            OrdenPago ordenPago = new OrdenPago(id, fecha, total);
//            ordenPagos.add(ordenPago);
//        }
//        sm.close();
//        return ordenPagos;
//    }
    
//    public ArrayList<DetallePago> listarPago(Date desde, Date hasta) throws SQLException {
//        PreparedStatement sm = Conexion.getConnection().prepareCall("select * from ordenes where cast(fecha as date) between ? and ?");
//        sm.setDate(1, new java.sql.Date(desde.getTime()));
//        sm.setDate(2, new java.sql.Date(hasta.getTime()));
//        ResultSet rs = sm.executeQuery();
//        ArrayList<OrdenPago> ordenPagos = new ArrayList<>();
//        while (rs.next()) {
//            long id = rs.getLong("id");
//            Date fecha = rs.getDate("fecha");
//            int total = rs.getInt("total");
//            OrdenPago ordenPago = new OrdenPago(id, fecha, total);
//            ordenPagos.add(ordenPago);
//        }
//        sm.close();
//        return ordenPagos;
//    }
}
