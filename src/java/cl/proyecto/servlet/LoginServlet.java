/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.servlet;

import cl.proyecto.modelo.Encuesta;
import cl.proyecto.modelo.Persona;
import cl.proyecto.negocio.RegistroComuna;
import cl.proyecto.negocio.RegistroEncuesta;
import cl.proyecto.negocio.RegistroGenero;
import cl.proyecto.negocio.RegistroPersona;
import cl.proyecto.negocio.RegistroProvincia;
import cl.proyecto.negocio.RegistroRegion;
import cl.proyecto.negocio.RegistroRol;
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
 * @author Laptop
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        request.getSession().setAttribute("persona", null);
        request.getSession().setAttribute("rol_id", -1);
        response.sendRedirect("login.jsp");
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
            request.setCharacterEncoding("UTF-8");
            String run = request.getParameter("run").replace("-", "");
            String pass = request.getParameter("pass");
            RegistroPersona regPersona = new RegistroPersona();
            Persona persona = regPersona.obtenerPersonaPorUsuario(run, pass);
            if (persona != null) {

                if (persona.isActivo()) {

                    if (persona.getCuenta().getRol().getId() != 4) {
                        RegistroRol registroRol = new RegistroRol();
                        request.getSession().setAttribute("roles", registroRol.listarRoles());

                        RegistroGenero registroGenero = new RegistroGenero();
                        request.getSession().setAttribute("generos", registroGenero.listarGenero());

                        RegistroRegion registroRegion = new RegistroRegion();
                        request.getSession().setAttribute("regiones", registroRegion.listarRegiones());

                        RegistroProvincia registroProvincia = new RegistroProvincia();
                        request.getSession().setAttribute("provincias", registroProvincia.listarProvincias());

                        RegistroComuna registroComuna = new RegistroComuna();
                        request.getSession().setAttribute("comunas", registroComuna.listarComuna());

                        request.getSession().setAttribute("persona", persona);
                        request.getSession().setAttribute("rol_id", persona.getCuenta().getRol());
                        switch (persona.getCuenta().getRol().getId()) {
                            case 1:
                                ArrayList<Persona> personas = regPersona.listarPersona();
                                request.getSession().setAttribute("personas", personas);
                                request.getSession().setAttribute("cantidad", personas.size());
                                response.sendRedirect("administrador.jsp");
                                ;
                                break;
                            case 2:
                                ArrayList<Persona> encuestadores = regPersona.listarEncuestadores();
                                request.getSession().setAttribute("encuestadores", encuestadores);
                                request.getSession().setAttribute("cantidad", encuestadores.size());
                                response.sendRedirect("jefepersonal.jsp");
                                ;
                                break;
                            case 3:
                                RegistroEncuesta registroEncuesta = new RegistroEncuesta();
                                ArrayList<Encuesta> encuestas = registroEncuesta.listarEncuestas();
                                request.getSession().setAttribute("encuestas", encuestas);
                                request.getSession().setAttribute("cantidad", encuestas.size());
                                response.sendRedirect("jefedeestudio.jsp");
                                ;
                                break;                           
                        }
                    } else {
                        request.getSession().setAttribute("loginError", "Encuestador, debe ingresar por la app");
                        response.sendRedirect("login.jsp");
                    }

                } else {
                    request.getSession().setAttribute("loginError", "Cuenta inactiva, contacte al administrador");
                    response.sendRedirect("login.jsp");
                }

            } else {
                request.getSession().setAttribute("loginError", "Usuario o contraseña son incorrecto");
                response.sendRedirect("login.jsp");
            }

            //processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("loginError", "No se pudo contectar con el servidor, vuelva a internarlo");
            response.sendRedirect("login.jsp");
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
