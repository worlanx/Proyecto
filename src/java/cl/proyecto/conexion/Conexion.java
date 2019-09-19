/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Worlan
 */
public class Conexion {
    
    private static Connection conn = null;
    
    private Conexion()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhosr:3306/proyecto","root","");            
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static Connection getConnection(){
        if(conn == null)
        {
            new Conexion();
        }
        return conn;
    }
    
}
