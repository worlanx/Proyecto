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

    public int agregar(DetalleEncuestador detalleEncuestador) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into detalle_encuestador(persona_id,encuesta_id) values(?,?)");
        sm.setInt(1, detalleEncuestador.getPersona().getId());
        sm.setInt(2, detalleEncuestador.getEncuesta().getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int agregarSimple(int persona_id, int encuesta_id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into detalle_encuestador(persona_id,encuesta_id) values(?,?)");
        sm.setInt(1, persona_id);
        sm.setInt(2, encuesta_id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int desactivar(int id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update detalle_encuestador set activo = 0 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int activar(int id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update detalle_encuestador set activo = 1 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int modificar(DetalleEncuestador detalleEncuestador) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update detalle_encuestador set persona_id = ?, encuesta_id = ? where id = ? ");
        sm.setInt(1, detalleEncuestador.getPersona().getId());
        sm.setInt(2, detalleEncuestador.getEncuesta().getId());
        sm.setInt(3, detalleEncuestador.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public DetalleEncuestador obtenerDetalleEncuestador(int id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select persona_id, encuesta_id from detalle_encuestador where id = ? and activo = 1");
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

    public ArrayList<DetalleEncuestador> listarEncuestas(int persona_id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select de.id, de.persona_id, de.encuesta_id, e.estado_id from detalle_encuestador as de inner join encuesta as e on de.encuesta_id = e.id where de.persona_id = ? and e.estado_id = 2 and de.activo = 1");
        sm.setInt(1, persona_id);
        ArrayList<DetalleEncuestador> detalleEncuestadores = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {

            int id = rs.getInt("id");

            int encuesta_id = rs.getInt("encuesta_id");
            RegistroEncuesta registroEncuesta = new RegistroEncuesta();
            Encuesta encuesta = registroEncuesta.obtenerEncuesta(encuesta_id);
            
            int total = cantidadEncuestas(encuesta_id,persona_id);

            DetalleEncuestador detalleEncuestador = new DetalleEncuestador(id, encuesta, persona_id,total);
            detalleEncuestadores.add(detalleEncuestador);
        }
        sm.close();
        return detalleEncuestadores;
    }
    
    public ArrayList<DetalleEncuestador> listarTodasEncuestas(int persona_id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select de.id, de.persona_id, de.encuesta_id, e.estado_id from detalle_encuestador as de inner join encuesta as e on de.encuesta_id = e.id where de.persona_id = ? and e.estado_id = 2 and de.activo = 1");
        sm.setInt(1, persona_id);
        ArrayList<DetalleEncuestador> detalleEncuestadores = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {

            int id = rs.getInt("id");

            int encuesta_id = rs.getInt("encuesta_id");
            RegistroEncuesta registroEncuesta = new RegistroEncuesta();
            Encuesta encuesta = registroEncuesta.obtenerEncuesta(encuesta_id);
            
            int total = cantidadEncuestas(encuesta_id,persona_id);

            DetalleEncuestador detalleEncuestador = new DetalleEncuestador(id, encuesta, persona_id,total);
            detalleEncuestadores.add(detalleEncuestador);
        }
        sm.close();
        return detalleEncuestadores;
    }

    public int cantidadEncuestas(int encuesta_id, int persona_id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT count(*) as total FROM proyecto.realizada where encuesta_id = ? and persona_run = ?;");
        sm.setInt(1, encuesta_id);
        sm.setInt(2, persona_id);
        int total = 0;
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            total = rs.getInt("total");
        }
        return total;
    }
    
    public int cantidadEncuestasTotales(int encuesta_id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT count(*) as total FROM proyecto.realizada where encuesta_id = ? ;");
        sm.setInt(1, encuesta_id);       
        int total = 0;
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            total = rs.getInt("total");
        }
        return total;
    }

    public int totalPagar(DetalleEncuestador detalleEncuestador) throws SQLException
    {
        int cantidad = cantidadEncuestas(detalleEncuestador.getEncuesta().getId(), detalleEncuestador.getPersona_id());
        int valor = detalleEncuestador.getEncuesta().getValor();
        int total = 0;
        if (cantidad < 101) {
            total = cantidad * valor;
        }
        else if(cantidad  > 100 && cantidad < 201)
        {
            total = (int)(cantidad * (valor * 1.1f ));
        }
        else
        {
             total = (int)(cantidad * (valor * 1.2f ));
        }
        return total;
    }
    
    
    
    public ArrayList<DetalleEncuestador> listarEncuestasPorEncuestador(int persona_id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id, encuesta_id from detalle_encuestador where persona_id = ? and activo = 1");
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
