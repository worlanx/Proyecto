/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Resultado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Worlan
 */
public class RegistroResultado {

    public ArrayList<Resultado> listarResultados(int encuesta_id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT r.pregunta_id, count(r.alternativa_id) as cantidad, r.alternativa_id,de.encuesta_id, p.titulo, a.descripcion FROM respuesta as r inner join detalle_encuesta as de on r.id = de.respuesta_id inner join pregunta as p on r.pregunta_id = p.id inner join alternativa as a on r.alternativa_id = a.id where de.encuesta_id = ? group by r.alternativa_id");
        sm.setInt(1, encuesta_id);
        ResultSet rs = sm.executeQuery();
        ArrayList<Resultado> resultados = new ArrayList();
        while (rs.next()) {
            
            int pregunta_id = rs.getInt("pregunta_id");
            int alternativa_id = rs.getInt("alternativa_id");
            int cantidad = rs.getInt("cantidad");
            String titulo = rs.getString("titulo");
            String descripcion = rs.getString("descripcion");
            Resultado resultado = new Resultado(pregunta_id, alternativa_id, encuesta_id, cantidad, titulo, descripcion);
            resultados.add(resultado);
        }
        return resultados;
    }

}
