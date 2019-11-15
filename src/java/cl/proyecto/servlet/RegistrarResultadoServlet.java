/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.servlet;

import cl.proyecto.modelo.DetalleEncuesta;
import cl.proyecto.modelo.DetalleRespuestas;
import cl.proyecto.modelo.GeneradarId;
import cl.proyecto.modelo.Mensaje;
import cl.proyecto.modelo.Respuesta;
import cl.proyecto.negocio.RegistroDetalleEncuesta;
import cl.proyecto.negocio.RegistroRespuesta;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Worlan
 */
public class RegistrarResultadoServlet extends HttpServlet {

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
            request.setCharacterEncoding("UTF-8");
            String respuesta = request.getParameter("respuestas");
            Gson gson = new Gson();
            DetalleRespuestas detalleRespuestas = gson.fromJson(respuesta, DetalleRespuestas.class);
            if (detalleRespuestas != null) {
                for (Respuesta res : detalleRespuestas.getDetalleRespuesta()) {
                    String id = GeneradarId.generar();
                    res.setId((id));
                    RegistroRespuesta registroRespuesta = new RegistroRespuesta();
                    registroRespuesta.agregar(res);
                    RegistroDetalleEncuesta registroDetalleEncuesta = new RegistroDetalleEncuesta();
                    DetalleEncuesta detalleEncuesta = new DetalleEncuesta(0, detalleRespuestas.getUsuario_id(), detalleRespuestas.getEncuesta_id(), res);
                    registroDetalleEncuesta.agregar(detalleEncuesta);
                }
                 Mensaje m = new Mensaje(1, "Encuesta Registrada exitosamente.");
                String json = gson.toJson(m);
                out.print(json);

            } else {
                Mensaje m = new Mensaje(1, "No se pudo registrar, intente nuevamente.");
                String json = gson.toJson(m);
                out.print(json);
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
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
