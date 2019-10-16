/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.DetalleEncuestador;
import cl.proyecto.modelo.Encuesta;
import cl.proyecto.modelo.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class RegistroDetalleEncuestador {
    
    public int agregar(DetalleEncuestador detalleEncuestador) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into detalle_encuestador(persona_id,encuesta_id) values(?,?)");
        sm.setInt(1, detalleEncuestador.getPersona().getId());
        sm.setInt(2, detalleEncuestador.getEncuesta().getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int eliminar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("delete from detalle_encuestador where id = ?");
        sm.setInt(1, id);        
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int modificar(DetalleEncuestador detalleEncuestador) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update detalle_encuestador set persona_id = ?, encuesta_id = ? where id = ? ");
        sm.setInt(1, detalleEncuestador.getPersona().getId());
        sm.setInt(2, detalleEncuestador.getEncuesta().getId());
        sm.setInt(3, detalleEncuestador.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public DetalleEncuestador obtenerDetalleEncuestador(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select persona_id, encuesta_id from detalle_encuestador where id = ?");
        sm.setInt(1, id);
        DetalleEncuestador detalleEncuestador = null;
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
            
            int persona_id = rs.getInt("persona_id");
            RegistroPersona registroPersona = new RegistroPersona();
            Persona persona = registroPersona.obtenerPersonaPorId(persona_id);
            
            int encuesta_id = rs.getInt("encuesta_id");
            RegistroEncuesta registroEncuesta = new RegistroEncuesta();
            Encuesta encuesta = registroEncuesta.obtenerEncuesta(encuesta_id);
            
            detalleEncuestador = new DetalleEncuestador(id, persona, encuesta);
        }
        sm.close();
        return detalleEncuestador;
    }
    
    public ArrayList<DetalleEncuestador> listarEncuestas() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select persona_id, encuesta_id from detalle_encuestador");
        ArrayList<DetalleEncuestador> detalleEncuestadores = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
            
            int id = rs.getInt("id");
            
            int persona_id = rs.getInt("persona_id");
            RegistroPersona registroPersona = new RegistroPersona();
            Persona persona = registroPersona.obtenerPersonaPorId(persona_id);
            
            int encuesta_id = rs.getInt("encuesta_id");
            RegistroEncuesta registroEncuesta = new RegistroEncuesta();
            Encuesta encuesta = registroEncuesta.obtenerEncuesta(encuesta_id);
            
            DetalleEncuestador detalleEncuestador = new DetalleEncuestador(id, persona, encuesta);
            detalleEncuestadores.add(detalleEncuestador);
        }
        sm.close();
        return detalleEncuestadores;
    }
    
    public ArrayList<DetalleEncuestador> listarEncuestasPorEncuestador(int persona_id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id, encuesta_id from detalle_encuestador where persona_id = ?");
        sm.setInt(1, persona_id);
        ArrayList<DetalleEncuestador> detalleEncuestadores = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
            
            int id = rs.getInt("id");           
            
            RegistroPersona registroPersona = new RegistroPersona();
            Persona persona = registroPersona.obtenerPersonaPorId(persona_id);
            
            int encuesta_id = rs.getInt("encuesta_id");
            RegistroEncuesta registroEncuesta = new RegistroEncuesta();
            Encuesta encuesta = registroEncuesta.obtenerEncuesta(encuesta_id);
            
            DetalleEncuestador detalleEncuestador = new DetalleEncuestador(id, persona, encuesta);
            detalleEncuestadores.add(detalleEncuestador);
        }
        sm.close();
        return detalleEncuestadores;
    }
}
