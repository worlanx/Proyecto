/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.servlet;

import cl.proyecto.modelo.DetalleEncuestador;
import cl.proyecto.modelo.Mensaje;
import cl.proyecto.modelo.Persona;
import cl.proyecto.negocio.RegistroDetalleEncuestador;
import cl.proyecto.negocio.RegistroPersona;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Worlan
 */
public class TesterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//http://localhost:8080/Proyecto/TesterServlet?run=14725369-8&pass=123456789
            try {
                request.setCharacterEncoding("UTF-8");
                String run = request.getParameter("run").replace("-", "");
                String pass = request.getParameter("pass");
                RegistroPersona regPersona = new RegistroPersona();
                Persona persona = regPersona.obtenerPersonaPorUsuario(run, pass);
                Gson gson = new Gson();
                if (persona != null) {
                    if (persona.isActivo()) {

                        if (persona.getCuenta().getRol().getId() == 4) {
                            RegistroDetalleEncuestador registroDetalleEncuestador = new RegistroDetalleEncuestador();
                            ArrayList<DetalleEncuestador> detalleEncuestadores = registroDetalleEncuestador.listarEncuestas();
                            String json = gson.toJson(detalleEncuestadores);
                            out.print(json);
                        } else {
                            Mensaje m = new Mensaje(1, "App es solo para encuestadores");
                            String json = gson.toJson(m);
                            out.print(json);
                        }
                    } else {
                        Mensaje m = new Mensaje(2, "Cuenta inactiva");
                        String json = gson.toJson(m);
                        out.print(json);
                    }
                } else {
                    Mensaje m = new Mensaje(3, "Usuario o contraseña invalidos");
                    String json = gson.toJson(m);
                    out.print(json);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TesterServlet.class.getName()).log(Level.SEVERE, null, ex);
                Mensaje m = new Mensaje(4, "No se pudo conectar con el servidor");
                Gson gson = new Gson();
                String json = gson.toJson(m);
                out.print(json);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
