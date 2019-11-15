/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.DetalleEncuesta;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Worlan
 */
public class RegistroDetalleEncuesta {
    public int agregar(DetalleEncuesta detalleEncuesta) throws SQLException
    {
         PreparedStatement sm = Conexion.getConnection().prepareCall("insert into detalle_encuesta(encuesta_id, respuesta_id, persona_id) values(?,?,?)");
         sm.setInt(1, detalleEncuesta.getEncuesta_id());
         sm.setString(2, detalleEncuesta.getRespuesta().getId());
         sm.setInt(3, detalleEncuesta.getPersona_id());
         int res = sm.executeUpdate();
        sm.close();
        return res;
    }
}
