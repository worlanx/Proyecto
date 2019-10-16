/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.CuentaUsuario;
import cl.proyecto.modelo.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Laptop
 */
public class RegistroCuentaUsuario {
    
    public int agregar(CuentaUsuario cuentaUsuario) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into cuenta_usuario(usuario,contraseña,rol_id,persona_id) values(?,?,?,?)");
        sm.setString(1, cuentaUsuario.getUsuario());
        sm.setString(2, cuentaUsuario.getContrasenia());
        sm.setInt(3, cuentaUsuario.getRol().getId());
        sm.setInt(4, cuentaUsuario.getPersona());
        int res = sm.executeUpdate();
        sm.close();
        return res;        
    }
    
    public int eliminar(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("delete from cuenta_usuario where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;   
    }
    
    public int modificar(CuentaUsuario cuentaUsuario) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update cuenta_usuario set usuario = ?, contraseña = ?, rol_id = ?, persona_id = ? where id =?");
        sm.setString(1, cuentaUsuario.getUsuario());
        sm.setString(2, cuentaUsuario.getContrasenia());
        sm.setInt(3, cuentaUsuario.getRol().getId());
        sm.setInt(4, cuentaUsuario.getPersona());
        sm.setInt(5, cuentaUsuario.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;   
    }
    
    public CuentaUsuario obtenerCuentaUsuario(int id) throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id, usuario, contraseña, rol_id, persona_id from cuenta_usuario where id = ?");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        CuentaUsuario cuentaUsuario = null;
        while(rs.next())
        {  
            String usuario = rs.getString("usuario");
            String contrasenia = rs.getString("contraseña");
            int rolId = rs.getInt("rol_id");
            int personaId = rs.getInt("persona_id");
            
            RegistroRol registroRol = new RegistroRol();
            Rol rol = registroRol.obtenerRol(rolId);
            
            cuentaUsuario = new CuentaUsuario(id, usuario, contrasenia, rol, personaId);
        }
        sm.close();
        return cuentaUsuario;
    }
    
    public ArrayList<CuentaUsuario> listarCuentaUsuario() throws SQLException
    {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id, usuario, contraseña, rol_id, persona_id from cuenta_usuario");
        ResultSet rs = sm.executeQuery();
        ArrayList<CuentaUsuario> cuentaUsuarios = new ArrayList<>();
        while(rs.next())
        {  
            int id = rs.getInt("id");
            String usuario = rs.getString("usuario");
            String contrasenia = rs.getString("contraseña");
            int rolId = rs.getInt("rol_id");
            int personaId = rs.getInt("persona_id");
            
             RegistroRol registroRol = new RegistroRol();
            Rol rol = registroRol.obtenerRol(rolId);
            
            CuentaUsuario cuentaUsuario = new CuentaUsuario(id, usuario, contrasenia, rol, personaId);
            cuentaUsuarios.add(cuentaUsuario);
        }
        sm.close();
        return cuentaUsuarios;
    }
    
}
