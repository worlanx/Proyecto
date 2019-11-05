/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Alternativa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class RegistroAlternativa {
    
    public int agregar(Alternativa alternativa) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into alternativa(descripcion,pregunta_id) values(?,?)");
        sm.setString(1, alternativa.getDescripcion());
        sm.setInt(2, alternativa.getPregunta_id());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int eliminar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update alternativa set activo = 0 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public int modificar(Alternativa alternativa) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update alternativa set descripcion = ?, pregunta_id = ? where id = ?");
        sm.setString(1, alternativa.getDescripcion());
        sm.setInt(2, alternativa.getPregunta_id());
        sm.setInt(3, alternativa.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }
    
    public Alternativa obtenerAlternativa(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select descripcion, pregunta_id from alternativa where id = ? and activo = 1");
        sm.setInt(1, id);
        Alternativa alternativa = null;
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {            
            String descripcion = rs.getString("descripcion");
            int pregunta_id = rs.getInt("pregunta_id");
            alternativa = new Alternativa(id, descripcion, pregunta_id);
        }
        sm.close();
        return alternativa;
    }
    
    public ArrayList<Alternativa> listarAlternativasPorPregunta(int pregunta_id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id,descripcion from alternativa where pregunta_id = ? and activo = 1");
        sm.setInt(1, pregunta_id);
        ArrayList<Alternativa> alternativas = new ArrayList<>();
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {          
            
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            Alternativa alternativa = new Alternativa(id, descripcion, pregunta_id);
            alternativas.add(alternativa);
        }
        sm.close();
        return alternativas;
    }
}
