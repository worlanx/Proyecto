/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.servlet;

import cl.proyecto.modelo.Mensaje;
import cl.proyecto.modelo.Participante;
import cl.proyecto.negocio.RegistroParticipante;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class ServletVerificarParticipante extends HttpServlet {

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
        //http://localhost:8080/Proyecto/ServletVerificarParticipante?encuesta=14725369-8&run=123456789
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            int encuesta = Integer.parseInt(request.getParameter("encuesta"));
            String run = request.getParameter("run");
            RegistroParticipante registroParticipante = new RegistroParticipante();
            Participante participante = registroParticipante.listar(encuesta, run);
            Gson gson = new Gson();
            if (participante == null) {
                Mensaje m = new Mensaje(1, "Encuesta válida");
                String json = gson.toJson(m);
                out.print(json);
            }
            else
            {
                Mensaje m = new Mensaje(2, "run ya realizó la encuesta");
                String json = gson.toJson(m);
                out.print(json);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletVerificarParticipante.class.getName()).log(Level.SEVERE, null, ex);
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
