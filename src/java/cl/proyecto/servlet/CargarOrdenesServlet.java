/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.servlet;

import cl.proyecto.modelo.GeneradarId;
import cl.proyecto.modelo.OrdenPago;
import cl.proyecto.modelo.Persona;
import cl.proyecto.modelo.ResumenEncuesta;
import cl.proyecto.negocio.RegistroResumen;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class CargarOrdenesServlet extends HttpServlet {

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
            out.println("<title>Servlet CargarOrdenesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CargarOrdenesServlet at " + request.getContextPath() + "</h1>");
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
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date desde = formatter.parse(request.getParameter("fecha_desde"));
            Date hasta = formatter.parse(request.getParameter("fecha_hasta"));
            long id = Long.parseLong(GeneradarId.generar());        
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date actual = new Date();
            RegistroResumen registroResumen = new RegistroResumen();
            Persona per = (Persona)request.getSession().getAttribute("enc");
            ArrayList<ResumenEncuesta> resumenEncuestas = registroResumen.listarResumenEncuesta(per.getId(), desde, hasta);
            int total = 0;
            for (ResumenEncuesta resumenEncuesta : resumenEncuestas) {
                total += resumenEncuesta.getTotalPago();
            }
            OrdenPago ordenPago = new OrdenPago(id, actual, total);
            request.getSession().setAttribute("ori", ordenPago);
            request.getSession().setAttribute("resu", resumenEncuestas);
            request.getSession().setAttribute("canti", resumenEncuestas.size());
            request.getSession().setAttribute("formato", dateFormat);           
            response.sendRedirect("generarorden.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(CargarOrdenesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CargarOrdenesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
