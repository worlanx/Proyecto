/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.negocio;

import cl.proyecto.conexion.Conexion;
import cl.proyecto.modelo.Comuna;
import cl.proyecto.modelo.CuentaUsuario;
import cl.proyecto.modelo.Direccion;
import cl.proyecto.modelo.Genero;
import cl.proyecto.modelo.Persona;
import cl.proyecto.modelo.Provincia;
import cl.proyecto.modelo.Region;
import cl.proyecto.modelo.Rol;
import cl.proyecto.modelo.Telefono;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Worlan
 */
public class RegistroPersona {

    public int agregar(Persona persona) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("insert into persona(run, dv, nombre, apellido_paterno, apellido_materno,fecha_nacimiento, email, genero_id, direccion_id) values(?,?,?,?,?,?,?,?,?)");
        sm.setInt(1, persona.getRun());
        sm.setString(2, String.valueOf(persona.getDv()));
        sm.setString(3, persona.getNombre());
        sm.setString(4, persona.getApellidoPaterno());
        sm.setString(5, persona.getApellidoMaterno());
        sm.setDate(6, (new java.sql.Date(persona.getFechaNacimiento().getTime())));
        sm.setString(7, persona.getEmail());
        sm.setInt(8, persona.getGenero().getId());
        sm.setInt(9, persona.getDireccion().getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int desactivarPorRun(int run) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update persona set activo =  0 where run = ?");
        sm.setInt(1, run);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int desactivarPorId(int id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update persona set activo = 0 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int activarPorRun(int run) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update persona set activo = 1 where run = ?");
        sm.setInt(1, run);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int activarPorId(int id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update persona set activo = 1 where id = ?");
        sm.setInt(1, id);
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int modificarPorRun(Persona persona) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update persona set nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, email = ? ,genero_id = ?, direccion_id = ? where run = ? ");
        sm.setString(1, persona.getNombre());
        sm.setString(2, persona.getApellidoPaterno());
        sm.setString(3, persona.getApellidoMaterno());
        sm.setDate(4, (new java.sql.Date(persona.getFechaNacimiento().getTime())));
        sm.setString(5, persona.getEmail());
        sm.setInt(6, persona.getGenero().getId());
        sm.setInt(7, persona.getDireccion().getId());
        sm.setInt(8, persona.getRun());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int modificarPorId(Persona persona) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("update persona set nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, email = ? ,genero_id = ?, direccion_id = ? where id = ? ");
        sm.setString(1, persona.getNombre());
        sm.setString(2, persona.getApellidoPaterno());
        sm.setString(3, persona.getApellidoMaterno());
        sm.setDate(4, (new java.sql.Date(persona.getFechaNacimiento().getTime())));
        sm.setString(5, persona.getEmail());
        sm.setInt(6, persona.getGenero().getId());
        sm.setInt(7, persona.getDireccion().getId());
        sm.setInt(8, persona.getId());
        int res = sm.executeUpdate();
        sm.close();
        return res;
    }

    public int obtenerIdPorRun(int run) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("select id from persona where run = ? and activo = 1");
        sm.setInt(1, run);
        ResultSet rs = sm.executeQuery();
        int id = -1;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public Persona obtenerPersonaPorRun(int run) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT id, run, dv, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, genero_id, direccion_id, email, genero, direccion, departamento,comuna_id, comuna, provincia_id, provincia, region_id, region, cuenta_id ,usuario, contraseña, rol_id, rol FROM detalle_usuarios where run  = ? and activo = 1");
        sm.setInt(1, run);
        ResultSet rs = sm.executeQuery();
        Persona persona = null;
        while (rs.next()) {

            //persona
            int id = rs.getInt("id");
            char dv = rs.getString("dv").charAt(0);
            String nombre = rs.getString("nombre");
            String apellidoPaterno = rs.getString("apellido_paterno");
            String apellidoMaterno = rs.getString("apellido_materno");
            Date fechaNacimiento = rs.getDate("fecha_nacimiento");
            String email = rs.getString("email");
            //genero
            int generoId = rs.getInt("genero_id");
            String descripcionGenero = rs.getString("genero");
            Genero genero = new Genero(generoId, descripcionGenero);

            //region
            int regionId = rs.getInt("region_id");
            String descripcionRegion = rs.getString("region");
            Region region = new Region(regionId, descripcionRegion, "", "");
            //provincia
            int provinciaId = rs.getInt("provincia_id");
            String descripcionProvincia = rs.getString("provincia");
            Provincia provincia = new Provincia(provinciaId, descripcionProvincia, regionId);
            //comuna
            int comunaId = rs.getInt("comuna_id");
            String descripcionComuna = rs.getString("comuna");
            Comuna comuna = new Comuna(comunaId, descripcionComuna, provinciaId);
            //direccion
            int direccionId = rs.getInt("direccion_id");
            String descripcionDireccion = rs.getString("direccion");
            String detalle = rs.getString("departamento");
            Direccion direccion = new Direccion(direccionId, descripcionDireccion, detalle, comunaId);
            //rol
            int rolId = rs.getInt("rol_id");
            String descripcionRol = rs.getString("rol");
            Rol rol = new Rol(rolId, descripcionRol);
            //usuario
            int cuentaId = rs.getInt("cuenta_id");
            String usuario = rs.getString("usuario");
            String contrasenia = rs.getString("contraseña");

            CuentaUsuario cuenta = new CuentaUsuario(cuentaId, usuario, contrasenia, rol, id);
            //Telefono
            RegistroTelefono regTelefono = new RegistroTelefono();
            ArrayList<Telefono> telefono = regTelefono.listarTelefonoPorId(id);
            persona = new Persona(id, run, dv, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, email, telefono, cuenta, genero, direccion);
        }
        return persona;
    }

    public Persona obtenerPersonaPorId(int id) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT id, run, dv, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, genero_id, direccion_id, email, genero, direccion, departamento, comuna_id, comuna, provincia_id, provincia, region_id, region, cuenta_id ,usuario, contraseña, rol_id, rol FROM detalle_usuarios where id  = ? and activo = 1");
        sm.setInt(1, id);
        ResultSet rs = sm.executeQuery();
        Persona persona = null;
        while (rs.next()) {

            //persona
            int run = rs.getInt("run");
            char dv = rs.getString("dv").charAt(0);
            String nombre = rs.getString("nombre");
            String apellidoPaterno = rs.getString("apellido_paterno");
            String apellidoMaterno = rs.getString("apellido_materno");
            Date fechaNacimiento = rs.getDate("fecha_nacimiento");
            String email = rs.getString("email");
            //genero
            int generoId = rs.getInt("genero_id");
            String descripcionGenero = rs.getString("genero");
            Genero genero = new Genero(generoId, descripcionGenero);
            //region
            int regionId = rs.getInt("region_id");
            String descripcionRegion = rs.getString("region");
            Region region = new Region(regionId, descripcionRegion, "", "");
            //provincia
            int provinciaId = rs.getInt("provincia_id");
            String descripcionProvincia = rs.getString("provincia");
            Provincia provincia = new Provincia(provinciaId, descripcionProvincia, regionId);
            //comuna
            int comunaId = rs.getInt("comuna_id");
            String descripcionComuna = rs.getString("comuna");
            Comuna comuna = new Comuna(comunaId, descripcionComuna, provinciaId);
            //direccion
            int direccionId = rs.getInt("direccion_id");
            String descripcionDireccion = rs.getString("direccion");
            String detalle = rs.getString("departamento");
            Direccion direccion = new Direccion(direccionId, descripcionDireccion, detalle, comunaId);
            //rol
            int rolId = rs.getInt("rol_id");
            String descripcionRol = rs.getString("rol");
            Rol rol = new Rol(rolId, descripcionRol);
            //usuario
            int cuentaId = rs.getInt("cuenta_id");
            String usuario = rs.getString("usuario");
            String contrasenia = rs.getString("contraseña");
            CuentaUsuario cuenta = new CuentaUsuario(cuentaId, usuario, contrasenia, rol, id);
            //Telefono
            RegistroTelefono regTelefono = new RegistroTelefono();
            ArrayList<Telefono> telefono = regTelefono.listarTelefonoPorId(id);
            persona = new Persona(id, run, dv, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, email, telefono, cuenta, genero, direccion);
        }
        return persona;
    }

    public Persona obtenerPersonaPorUsuario(String usuario, String contrasenia) throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT id, run, dv, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, genero_id, direccion_id, email, genero, direccion, departamento, comuna_id, comuna, provincia_id, provincia, region_id, region, cuenta_id ,usuario, contraseña, rol_id, rol , activo FROM detalle_usuarios where usuario = ? and contraseña = md5(?)");
        sm.setString(1, usuario);
        sm.setString(2, contrasenia);
        ResultSet rs = sm.executeQuery();
        Persona persona = null;
        while (rs.next()) {

            //persona
            int id = rs.getInt("id");
            int run = rs.getInt("run");
            char dv = rs.getString("dv").charAt(0);
            String nombre = rs.getString("nombre");
            String apellidoPaterno = rs.getString("apellido_paterno");
            String apellidoMaterno = rs.getString("apellido_materno");
            Date fechaNacimiento = rs.getDate("fecha_nacimiento");
            String email = rs.getString("email");
            //genero
            int generoId = rs.getInt("genero_id");
            String descripcionGenero = rs.getString("genero");
            Genero genero = new Genero(generoId, descripcionGenero);
            //region
            int regionId = rs.getInt("region_id");
            String descripcionRegion = rs.getString("region");
            Region region = new Region(regionId, descripcionRegion, "", "");
            //provincia
            int provinciaId = rs.getInt("provincia_id");
            String descripcionProvincia = rs.getString("provincia");
            Provincia provincia = new Provincia(provinciaId, descripcionProvincia, regionId);
            //comuna
            int comunaId = rs.getInt("comuna_id");
            String descripcionComuna = rs.getString("comuna");
            Comuna comuna = new Comuna(comunaId, descripcionComuna, provinciaId);
            //direccion
            int direccionId = rs.getInt("direccion_id");
            String descripcionDireccion = rs.getString("direccion");
            String detalle = rs.getString("departamento");
            Direccion direccion = new Direccion(direccionId, descripcionDireccion, detalle, comunaId);
            //rol
            int rolId = rs.getInt("rol_id");
            String descripcionRol = rs.getString("rol");
            Rol rol = new Rol(rolId, descripcionRol);
            //usuario
            int cuentaId = rs.getInt("cuenta_id");
            CuentaUsuario cuenta = new CuentaUsuario(cuentaId, usuario, contrasenia, rol, id);
            //Telefono
            RegistroTelefono regTelefono = new RegistroTelefono();
            ArrayList<Telefono> telefono = regTelefono.listarTelefonoPorId(id);
            persona = new Persona(id, run, dv, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, email, telefono, cuenta, genero, direccion);
            boolean activo = rs.getBoolean("activo");
            persona.setActivo(activo);
        }
        return persona;
    }

    public ArrayList<Persona> listarPersona() throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT id, run, dv, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, genero_id, direccion_id, email, genero, direccion, departamento, comuna_id, comuna, provincia_id, provincia, region_id, region, cuenta_id ,usuario, contraseña, rol_id, rol, activo FROM detalle_usuarios");
        ResultSet rs = sm.executeQuery();
        ArrayList<Persona> personas = new ArrayList<>();
        while (rs.next()) {

            //persona
            int id = rs.getInt("id");
            int run = rs.getInt("run");
            char dv = rs.getString("dv").charAt(0);
            String nombre = rs.getString("nombre");
            String apellidoPaterno = rs.getString("apellido_paterno");
            String apellidoMaterno = rs.getString("apellido_materno");
            Date fechaNacimiento = rs.getDate("fecha_nacimiento");
            String email = rs.getString("email");
            //genero
            int generoId = rs.getInt("genero_id");
            String descripcionGenero = rs.getString("genero");
            Genero genero = new Genero(generoId, descripcionGenero);
            //region
            int regionId = rs.getInt("region_id");
            String descripcionRegion = rs.getString("region");
            Region region = new Region(regionId, descripcionRegion, "", "");
            //provincia
            int provinciaId = rs.getInt("provincia_id");
            String descripcionProvincia = rs.getString("provincia");
            Provincia provincia = new Provincia(provinciaId, descripcionProvincia, regionId);
            //comuna
            int comunaId = rs.getInt("comuna_id");
            String descripcionComuna = rs.getString("comuna");
            Comuna comuna = new Comuna(comunaId, descripcionComuna, provinciaId);
            //direccion
            int direccionId = rs.getInt("direccion_id");
            String descripcionDireccion = rs.getString("direccion");
            String detalle = rs.getString("departamento");
            Direccion direccion = new Direccion(direccionId, descripcionDireccion, detalle, comunaId);
            //rol
            int rolId = rs.getInt("rol_id");
            String descripcionRol = rs.getString("rol");
            Rol rol = new Rol(rolId, descripcionRol);
            //usuario
            int cuentaId = rs.getInt("cuenta_id");
            String usuario = rs.getString("usuario");
            String contrasenia = rs.getString("contraseña");
            CuentaUsuario cuenta = new CuentaUsuario(cuentaId, usuario, contrasenia, rol, id);
            //Telefono
            RegistroTelefono regTelefono = new RegistroTelefono();
            ArrayList<Telefono> telefono = regTelefono.listarTelefonoPorId(id);
            Persona persona = new Persona(id, run, dv, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, email, telefono, cuenta, genero, direccion);
            boolean activo = rs.getBoolean("activo");
            persona.setActivo(activo);
            personas.add(persona);
        }
        return personas;
    }
    
    
    public ArrayList<Persona> listarEncuestadores() throws SQLException {
        PreparedStatement sm = Conexion.getConnection().prepareCall("SELECT id, run, dv, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, genero_id, direccion_id, email, genero, direccion, departamento, comuna_id, comuna, provincia_id, provincia, region_id, region, cuenta_id ,usuario, contraseña, rol_id, rol, activo FROM detalle_usuarios where rol_id = 4");
        ResultSet rs = sm.executeQuery();
        ArrayList<Persona> personas = new ArrayList<>();
        while (rs.next()) {

            //persona
            int id = rs.getInt("id");
            int run = rs.getInt("run");
            char dv = rs.getString("dv").charAt(0);
            String nombre = rs.getString("nombre");
            String apellidoPaterno = rs.getString("apellido_paterno");
            String apellidoMaterno = rs.getString("apellido_materno");
            Date fechaNacimiento = rs.getDate("fecha_nacimiento");
            String email = rs.getString("email");
            //genero
            int generoId = rs.getInt("genero_id");
            String descripcionGenero = rs.getString("genero");
            Genero genero = new Genero(generoId, descripcionGenero);
            //region
            int regionId = rs.getInt("region_id");
            String descripcionRegion = rs.getString("region");
            Region region = new Region(regionId, descripcionRegion, "", "");
            //provincia
            int provinciaId = rs.getInt("provincia_id");
            String descripcionProvincia = rs.getString("provincia");
            Provincia provincia = new Provincia(provinciaId, descripcionProvincia, regionId);
            //comuna
            int comunaId = rs.getInt("comuna_id");
            String descripcionComuna = rs.getString("comuna");
            Comuna comuna = new Comuna(comunaId, descripcionComuna, provinciaId);
            //direccion
            int direccionId = rs.getInt("direccion_id");
            String descripcionDireccion = rs.getString("direccion");
            String detalle = rs.getString("departamento");
            Direccion direccion = new Direccion(direccionId, descripcionDireccion, detalle, comunaId);
            //rol
            int rolId = rs.getInt("rol_id");
            String descripcionRol = rs.getString("rol");
            Rol rol = new Rol(rolId, descripcionRol);
            //usuario
            int cuentaId = rs.getInt("cuenta_id");
            String usuario = rs.getString("usuario");
            String contrasenia = rs.getString("contraseña");
            CuentaUsuario cuenta = new CuentaUsuario(cuentaId, usuario, contrasenia, rol, id);
            //Telefono
            RegistroTelefono regTelefono = new RegistroTelefono();
            ArrayList<Telefono> telefono = regTelefono.listarTelefonoPorId(id);
            Persona persona = new Persona(id, run, dv, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, email, telefono, cuenta, genero, direccion);
            boolean activo = rs.getBoolean("activo");
            persona.setActivo(activo);
            personas.add(persona);
        }
        return personas;
    }
}
