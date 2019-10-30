/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.servlet;

import cl.proyecto.modelo.Alternativa;
import cl.proyecto.modelo.Encuesta;
import cl.proyecto.modelo.EstadoEncuesta;
import cl.proyecto.modelo.Pregunta;
import cl.proyecto.modelo.TipoPregunta;
import cl.proyecto.negocio.RegistroAlternativa;
import cl.proyecto.negocio.RegistroEncuesta;
import cl.proyecto.negocio.RegistroPregunta;
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
public class CrearEncuestaServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CrearEncuestaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearEncuestaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            //processRequest(request, response);
            request.setCharacterEncoding("UTF-8");
            String titulo = request.getParameter("titulo");
            int valor = Integer.parseInt(request.getParameter("valor"));

            Encuesta encuesta = new Encuesta(1, titulo, valor, new EstadoEncuesta(1, ""));
            RegistroEncuesta registroEncuesta = new RegistroEncuesta();
            registroEncuesta.agregar(encuesta);
            int id = registroEncuesta.obtenerEncuestaId();

            String[] pre = request.getParameterValues("pregunta");
            String[] numeroPregunta = request.getParameterValues("numeroPregunta");           

            for (int i = 0; i < pre.length; i++) {
                String tipo = request.getParameter(String.format("multiple%s", numeroPregunta[i]));
                TipoPregunta tipoPregunta = new TipoPregunta(1);
                if (tipo != null) {
                    tipoPregunta.setId(2);
                }
                Pregunta pregunta = new Pregunta(pre[i], tipoPregunta,id);
                RegistroPregunta registroPregunta = new RegistroPregunta();
                registroPregunta.agregar(pregunta);
                int idPregunta = registroPregunta.obtenerPreguntaId();

                String[] alternativas = request.getParameterValues(String.format("alt%s", numeroPregunta[i]));
                for (int j = 0; j < alternativas.length; j++) {
                    String alt = alternativas[j];
                    Alternativa alternativa = new Alternativa(0, alt, idPregunta);
                    RegistroAlternativa registroAlternativa = new RegistroAlternativa();
                    registroAlternativa.agregar(alternativa);
                }               
            }
             request.getSession().setAttribute("encuestaExito", "Cuenta Registrada Exitosamente");
             response.sendRedirect("crearencuesta.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(CrearEncuestaServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("encuestaError", "No se pudo registar la encuesta, verifique la información");
            response.sendRedirect("crearencuesta.jsp");
        }
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
