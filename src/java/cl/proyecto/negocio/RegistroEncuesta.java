/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Encuesta;
import cl.proyecto.modelo.EstadoEncuesta;
import cl.proyecto.modelo.Pregunta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class RegistroEncuesta {
    
    public int agregar(Encuesta encuesta) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into encuesta(titulo,valor,estado_id) values(?,?,?)");
        sm.setString(1, encuesta.getTitulo());
        sm.setInt(2, encuesta.getValor());
        sm.setInt(3, encuesta.getEstado().getId());  
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int eliminar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update encuesta set activo = 0 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int modificar(Encuesta encuesta) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update encuesta set titulo = ?, valor = ?, estado = ? where id = ?");
        sm.setString(1, encuesta.getTitulo());
        sm.setInt(2, encuesta.getValor());
        sm.setInt(3, encuesta.getEstado().getId());
        sm.setInt(4, encuesta.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public Encuesta obtenerEncuesta(int id) throws SQLException{
        PreparedStatement sm = Conexion.getConnection().prepareCall("select titulo, valor, estado_id from encuesta where id = ? and activo = 1");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        Encuesta encuesta = null;
        while (rs.next()) {            
            String titulo = rs.getString("titulo");
            int valor = rs.getInt("valor");
            int estado_id = rs.getInt("estado_id");
            RegistroEstadoEncuesta registroEstadoEncuesta = new RegistroEstadoEncuesta();
            EstadoEncuesta estadoEncuesta = registroEstadoEncuesta.obtenerEstadoEncuesta(estado_id);
            
            RegistroPregunta registroPregunta = new RegistroPregunta();
            ArrayList<Pregunta> preguntas = registroPregunta.listarPreguntasPorEncuesta(id);            
            
            encuesta = new Encuesta(id, titulo, valor, estadoEncuesta, preguntas);
        }
        sm.close();
        return  encuesta;
    }  
    
    public int obtenerEncuestaId() throws SQLException{
        PreparedStatement sm = Conexion.getConnection().prepareCall("select max(id) as id from encuesta where activo = 1");       
        ResultSet rs = sm.executeQuery();
        int id = -1;
        while (rs.next()) {            
            id = rs.getInt("id");
        }
        sm.close();
        return  id;
    }
        
    public ArrayList<Encuesta> listarEncuestas() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select titulo, valor, estado_id from encuesta where activo = 1");
        ResultSet rs = sm.executeQuery();
        ArrayList<Encuesta> encuestas = new ArrayList<>();
        while (rs.next()) {            
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            int valor = rs.getInt("valor");
            int estado_id = rs.getInt("estado_id");
            RegistroEstadoEncuesta registroEstadoEncuesta = new RegistroEstadoEncuesta();
            EstadoEncuesta estadoEncuesta = registroEstadoEncuesta.obtenerEstadoEncuesta(estado_id);
            
            RegistroPregunta registroPregunta = new RegistroPregunta();
            ArrayList<Pregunta> preguntas = registroPregunta.listarPreguntasPorEncuesta(id);          
            
            
            Encuesta encuesta = new Encuesta(id, titulo, valor, estadoEncuesta, preguntas);
            encuestas.add(encuesta);
        }
        sm.close();
        return  encuestas;
    }
    
    
}
