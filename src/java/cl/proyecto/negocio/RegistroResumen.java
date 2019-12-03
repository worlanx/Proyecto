/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Resumen;
import cl.proyecto.modelo.ResumenEncuesta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Worlan
 */
public class RegistroResumen {

    public ArrayList<Resumen> listarResumen() throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select p.id, p.run, p.dv, p.nombre, p.apellido_paterno as paterno, p.apellido_materno as materno, count(r.encuesta_id) as total from persona as p inner join realizada as r on p.id = r.persona_run group by p.id  ");
        ArrayList<Resumen> resumenes = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {

            int id = rs.getInt("id");
            int run = rs.getInt("run");
            char dv = rs.getString("dv").charAt(0);
            String nombre = rs.getString("nombre");
            String paterno = rs.getString("paterno");
            String materno = rs.getString("materno");
            int total = rs.getInt("total");
            Resumen resumen = new Resumen(id, run, dv, nombre, paterno, materno, total);
            resumenes.add(resumen);
        }
        sm.close();
        return resumenes;
    }

    public ArrayList<ResumenEncuesta> listarResumenEncuesta(int id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select count(r.encuesta_id) as total, e.titulo , e.valor from persona as p inner join realizada as r on p.id = r.persona_run inner join encuesta as e on r.encuesta_id = e.id where p.id = ? group by p.id, encuesta_id");
        sm.setInt(1, id);
        ArrayList<ResumenEncuesta> resumenes = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {

            int cantidad = rs.getInt("total");
            String titulo = rs.getString("titulo");
            int valor = rs.getInt("valor");
            int total = 0;
            if (cantidad < 101) {
                total = cantidad * valor;
            } else if (cantidad > 100 && cantidad < 201) {
                total = (int) (cantidad * (valor * 1.1f));
            } else {
                total = (int) (cantidad * (valor * 1.2f));
            }
            ResumenEncuesta resumenEncuesta = new ResumenEncuesta(cantidad, titulo, valor, total);
            resumenes.add(resumenEncuesta);
        }
        sm.close();
        return resumenes;
    }

    public ArrayList<ResumenEncuesta> listarResumenEncuesta(int id, Date desde, Date hasta) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select count(r.encuesta_id) as total, e.titulo , e.valor, r.fecha, r.id as id_re from persona as p inner join realizada as r on p.id = r.persona_run inner join encuesta as e on r.encuesta_id = e.id left join detalle_pago as dp on r.id = dp.realizada_id where p.id = ? and dp.realizada_id is null  and CAST(r.fecha AS DATE) between ? and ? group by p.id, encuesta_id");
        sm.setInt(1, id);
        sm.setDate(2, new java.sql.Date(desde.getTime()));
        sm.setDate(3, new java.sql.Date(hasta.getTime()));
        ArrayList<ResumenEncuesta> resumenes = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {

            int cantidad = rs.getInt("total");
            String titulo = rs.getString("titulo");
            int realizado_id = rs.getInt("id_re");
            int valor = rs.getInt("valor");
            int total = 0;
            if (cantidad < 101) {
                total = cantidad * valor;
            } else if (cantidad > 100 && cantidad < 201) {
                total = (int) (cantidad * (valor * 1.1f));
            } else {
                total = (int) (cantidad * (valor * 1.2f));
            }
            ResumenEncuesta resumenEncuesta = new ResumenEncuesta(cantidad, titulo, valor, total, realizado_id);
            resumenes.add(resumenEncuesta);
        }
        sm.close();
        return resumenes;
    }

    public ArrayList<ResumenEncuesta> listarResumenPorOrden(long orden_id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select count(r.encuesta_id) as total, e.titulo , e.valor from persona as p inner join realizada as r on p.id = r.persona_run inner join encuesta as e on r.encuesta_id = e.id right join detalle_pago as de on de.realizada_id = r.id right join orden_pago as op on op.id = de.orden_pago_id where op.id = ? group by p.id, encuesta_id");
        sm.setLong(1, orden_id);
        ArrayList<ResumenEncuesta> resumenes = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {

            int cantidad = rs.getInt("total");
            String titulo = rs.getString("titulo");
            int valor = rs.getInt("valor");
            int total = 0;
            if (cantidad < 101) {
                total = cantidad * valor;
            } else if (cantidad > 100 && cantidad < 201) {
                total = (int) (cantidad * (valor * 1.1f));
            } else {
                total = (int) (cantidad * (valor * 1.2f));
            }
            ResumenEncuesta resumenEncuesta = new ResumenEncuesta(cantidad, titulo, valor, total);
            resumenes.add(resumenEncuesta);
        }
        sm.close();
        return resumenes;
    }

    public ArrayList<Integer> listarEspecial(int id, Date desde, Date hasta) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT r.id, r.encuesta_id FROM realizada as r left join detalle_pago as dp on r.id = dp.realizada_id where  dp.realizada_id is null and r.persona_run = ? and CAST(r.fecha AS DATE) between ? and ?;");
        sm.setInt(1, id);
        sm.setDate(2, new java.sql.Date(desde.getTime()));
        sm.setDate(3, new java.sql.Date(hasta.getTime()));
        ArrayList<Integer> numeros = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {

            int num = rs.getInt("id");
            numeros.add(num);
        }
        sm.close();
        return numeros;
    }
}
