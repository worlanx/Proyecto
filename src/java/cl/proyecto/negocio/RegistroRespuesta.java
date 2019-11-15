/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Alternativa;
import cl.proyecto.modelo.Pregunta;
import cl.proyecto.modelo.Respuesta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class RegistroRespuesta {
    
    public int agregar(Respuesta respuesta) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into respuesta(id, pregunta_id,alternativa_id) values(?,?,?)");
        sm.setString(1, respuesta.getId());
        sm.setInt(2, respuesta.getPregunta_id());
        sm.setInt(3, respuesta.getAlternativa_id());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int desactivar(String id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update respuesta set activo = 0 where id = ?");
        sm.setString(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int activar(String id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update respuesta set activo = 1 where id = ?");
        sm.setString(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int modificar(Respuesta respuesta) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update respuesta set pregunta_id = ?, alternativa_id = ? where id = ?");
        sm.setInt(1, respuesta.getPregunta_id());
        sm.setInt(2, respuesta.getAlternativa_id());
        sm.setString(3, respuesta.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public Respuesta obtenerRespuesta(String id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select pregunta_id, alternativa_id from respuesta where id = ? and activo = 1");
        sm.setString(1, id);
        Respuesta respuesta = null;
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
            
            int pregunta_id = rs.getInt("pregunta_id");
            int alternativa_id = rs.getInt("alternativa_id");       
            
            respuesta = new Respuesta(id, pregunta_id, alternativa_id);
        }
        sm.close();
        return respuesta;
    }
    
    public ArrayList<Respuesta> listarRespuestasPorPregunta(int pregunta_id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id, alternativa_id from respuesta where pregunta_id = ? and activo = 1");
        sm.setInt(1, pregunta_id);
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
            
            String id = rs.getString("id");
                      
            
            int alternativa_id = rs.getInt("alternativa_id");
            
            Respuesta respuesta = new Respuesta(id, pregunta_id, alternativa_id);
            respuestas.add(respuesta);
        }
        sm.close();
        return respuestas;
    }
}
