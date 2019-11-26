/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Participante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Worlan
 */
public class RegistroParticipante {

    public int agregar(Participante participante) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into participante(encuesta_id, run_participante) values(?,?)");
        sm.setInt(1, participante.getEncuesta_id());
        sm.setString(2, participante.getRun_participante());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int agregarEncuesta(int encuesta_id, int run_participante) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into realizada(encuesta_id, persona_run,fecha) values(?,?,now())");
        sm.setInt(1, encuesta_id);
        sm.setInt(2, run_participante);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public Participante listar(int encuesta_id, String run_participante) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id from participante where encuesta_id = ? and run_participante = ? and activo = 1");
        sm.setInt(1, encuesta_id);
        sm.setString(2, run_participante);
        ResultSet rs = sm.executeQuery();
        Participante participante = null;
        while (rs.next()) {
            int id = rs.getInt("id");
            participante = new Participante(id, encuesta_id, run_participante);
        }
        sm.close();
        return participante;
    }
}
