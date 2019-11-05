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
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into respuesta(pregunta_id,alternativa_id) values(?,?)");
        sm.setInt(1, respuesta.getPregunta().getId());
        sm.setInt(2, respuesta.getAlternativa().getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int eliminar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update respuesta set activo = 0 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int modificar(Respuesta respuesta) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update respuesta set pregunta_id = ?, alternativa_id = ? where id = ?");
        sm.setInt(1, respuesta.getPregunta().getId());
        sm.setInt(2, respuesta.getAlternativa().getId());
        sm.setInt(3, respuesta.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public Respuesta obtenerRespuesta(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select pregunta_id, alternativa_id from respuesta where id = ? and activo = 1");
        sm.setInt(1, id);
        Respuesta respuesta = null;
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
            
            int pregunta_id = rs.getInt("pregunta_id");
            RegistroPregunta registroPregunta = new RegistroPregunta();
            Pregunta pregunta = registroPregunta.obtenerPreguntas(pregunta_id);
            
            int alternativa_id = rs.getInt("alternativa_id");
            RegistroAlternativa registroAlternativa = new RegistroAlternativa();
            Alternativa alternativa = registroAlternativa.obtenerAlternativa(alternativa_id);
            
            respuesta = new Respuesta(id, pregunta, alternativa);
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
            
            int id = rs.getInt("id");
            
            RegistroPregunta registroPregunta = new RegistroPregunta();
            Pregunta pregunta = registroPregunta.obtenerPreguntas(pregunta_id);
            
            int alternativa_id = rs.getInt("alternativa_id");
            RegistroAlternativa registroAlternativa = new RegistroAlternativa();
            Alternativa alternativa = registroAlternativa.obtenerAlternativa(alternativa_id);
            
            Respuesta respuesta = new Respuesta(id, pregunta, alternativa);
            respuestas.add(respuesta);
        }
        sm.close();
        return respuestas;
    }
}
