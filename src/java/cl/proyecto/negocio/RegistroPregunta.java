/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Alternativa;
import cl.proyecto.modelo.Pregunta;
import cl.proyecto.modelo.TipoPregunta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class RegistroPregunta {
    
    public int agregar(Pregunta pregunta) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into pregunta(titulo, tipo_id, encuesta_id) values(?,?,?)");
        sm.setString(1, pregunta.getTitulo());
        sm.setInt(2, pregunta.getTipo().getId());
        sm.setInt(3, pregunta.getEncuesta_id());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int eliminar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("delete from pregunta where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int modificar(Pregunta pregunta) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update pregunta set titulo = ?, tipo_id = ?. encuesta_id = ? where id = ?");
        sm.setString(1, pregunta.getTitulo());
        sm.setInt(2, pregunta.getTipo().getId());
        sm.setInt(3, pregunta.getEncuesta_id());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public Pregunta obtenerPreguntas(int id)throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select titulo, tipo_id, encuesta_id from pregunta where id = ?");
        sm.setInt(1, id);
        Pregunta pregunta = null;
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
            
            String titulo = rs.getString("titulo");
            int tipo_id = rs.getInt("tipo_id");
            
            RegistroTipoPregunta registroTipoPregunta = new RegistroTipoPregunta();
            TipoPregunta tipoPregunta = registroTipoPregunta.obtenerTipoPregunta(tipo_id);
            
            RegistroAlternativa registroAlternativa = new RegistroAlternativa();
            ArrayList<Alternativa> alternativas = registroAlternativa.listarAlternativasPorPregunta(id);
            
            int encuesta_id = rs.getInt("encuesta_id");
            pregunta = new Pregunta(id, titulo, tipoPregunta, alternativas, encuesta_id);
        }
        sm.close();
        return pregunta;
    }
    
    public ArrayList<Pregunta> listarPreguntasPorEncuesta(int encuesta_id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id, titulo, tipo_id from pregunta where encuesta_id = ?");
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            int tipo_id = rs.getInt("tipo_id");
            
            RegistroTipoPregunta registroTipoPregunta = new RegistroTipoPregunta();
            TipoPregunta tipoPregunta = registroTipoPregunta.obtenerTipoPregunta(tipo_id);
            
            RegistroAlternativa registroAlternativa = new RegistroAlternativa();
            ArrayList<Alternativa> alternativas = registroAlternativa.listarAlternativasPorPregunta(id);            
            
            Pregunta pregunta = new Pregunta(id, titulo, tipoPregunta, alternativas, encuesta_id);
            preguntas.add(pregunta);
        }        
        sm.close();
        return preguntas;
    }
    
}
