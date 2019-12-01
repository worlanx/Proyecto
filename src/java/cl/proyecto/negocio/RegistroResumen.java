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
}
