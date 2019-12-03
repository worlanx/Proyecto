/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.servlet;

import cl.proyecto.modelo.DetallePago;
import cl.proyecto.modelo.OrdenPago;
import cl.proyecto.modelo.Persona;
import cl.proyecto.modelo.ResumenEncuesta;
import cl.proyecto.negocio.RegistroDetallePago;
import cl.proyecto.negocio.RegistroOrdenPago;
import cl.proyecto.negocio.RegistroPersona;
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
public class RegistrarOrdenServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistrarOrdenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarOrdenServlet at " + request.getContextPath() + "</h1>");
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
            OrdenPago ordenPago = (OrdenPago)request.getSession().getAttribute("ori");
            ArrayList<Integer> numeros = (ArrayList<Integer>)request.getSession().getAttribute("nume");
            RegistroOrdenPago registroOrdenPago = new RegistroOrdenPago();
            registroOrdenPago.agregar(ordenPago);
            RegistroDetallePago registroDetallePago = new RegistroDetallePago();
            for (Integer numero : numeros) {
                DetallePago detallePago = new DetallePago(0, 0, ordenPago.getId(), numero);
                registroDetallePago.agregar(detallePago);
            }           
            request.getSession().setAttribute("ordenCorrecto", "Orden registrada existosamente");
            Persona per = (Persona)request.getSession().getAttribute("enc");
            ArrayList<OrdenPago> ordenPagos = registroOrdenPago.listarOrden(per.getId());            
            request.getSession().setAttribute("ordenes", ordenPagos);
            request.getSession().setAttribute("cantidadO", ordenPagos.size());
            request.getSession().setAttribute("enc", per);
            response.sendRedirect("listapago.jsp");            
        } catch (SQLException ex) {
            request.getSession().setAttribute("ordenError", "No se pudo registrar el orden");
            Logger.getLogger(RegistrarOrdenServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("listapago.jsp");
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
