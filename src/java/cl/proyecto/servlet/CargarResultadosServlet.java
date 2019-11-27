/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.servlet;

import cl.proyecto.modelo.Alternativa;
import cl.proyecto.modelo.ListaResultado;
import cl.proyecto.modelo.Pregunta;
import cl.proyecto.modelo.Resultado;
import cl.proyecto.negocio.RegistroPregunta;
import cl.proyecto.negocio.RegistroResultado;
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
public class CargarResultadosServlet extends HttpServlet {

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
            out.println("<title>Servlet CargarResultadosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CargarResultadosServlet at " + request.getContextPath() + "</h1>");
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
            int encuesta_id = Integer.parseInt(request.getParameter("encuesta_id"));
            RegistroResultado registroResultado = new RegistroResultado();
            ArrayList<Resultado> resultados = registroResultado.listarResultados(encuesta_id);
            request.getSession().setAttribute("resultados", resultados);
            RegistroPregunta registroPregunta = new RegistroPregunta();
            ArrayList<Pregunta> preguntas = registroPregunta.listarPreguntasPorEncuesta(encuesta_id);
            request.getSession().setAttribute("preguntas", preguntas);
            String[] colores = new String[]{"RGB(255, 0, 0, 0.50)", "RGB(255, 255, 0, 0.50)", "RGB(128, 128, 0, 0.50)", "RGB(0, 255, 0, 0.50)", "RGB(0, 128, 0, 0.50)", "RGB(0, 255, 255, 0.50)", "RGB(0, 128, 128, 0.50)", "RGB(0, 0, 255, 0.50)", "RGB(0, 0, 128, 0.50)", "RGB(255, 0, 255, 0.50)", "RGB(128, 0, 128, 0.50)"};
            ArrayList<String> resPie = new ArrayList<>();
            ArrayList<String> resBar = new ArrayList<>();
            ArrayList<String> resLine = new ArrayList<>();
            ListaResultado listaResultado = new ListaResultado();
            for (Pregunta pregunta : preguntas) {
                for (Alternativa alternativa : pregunta.getAlternativas()) {
                    Resultado resu = new Resultado(pregunta.getId(), alternativa.getId(), encuesta_id, 0, pregunta.getTitulo(), alternativa.getDescripcion());
                    listaResultado.getLista().add(resu);
                }
            }

            for (Resultado resultado : resultados) {
                listaResultado.modificarCantidad(resultado.getAlternativa_id(), resultado.getCantidad());
            }

            for (Pregunta pregunta : preguntas) {
                String label = "";
                StringBuilder labels = new StringBuilder();
                labels.append("labels: [");
                StringBuilder datos = new StringBuilder();
                datos.append("data: [");
                StringBuilder backgroundColor = new StringBuilder();
                backgroundColor.append("backgroundColor: [ ");
                int contador = 0;
                label = "'" + pregunta.getTitulo() + "'";
                for (Resultado resultado : listaResultado.getLista()) {
                    if (resultado.getPregunta_id() == pregunta.getId()) {
                        labels.append("'" + resultado.getDescripcion() + "',");
                        datos.append(resultado.getCantidad() + ",");
                        backgroundColor.append("'" + colores[contador] + "',");
                        contador++;
                        if(contador >= 11)
                        {
                            contador = 0;
                        }
                    }
                }
                datos.append("]");
                labels.append("]");
                backgroundColor.append("]");
                String borderColor = "'RGB(192, 192, 192)'";
                String datasets = String.format("datasets: [{ label: %s, %s, borderColor: %s, %s }]", label, backgroundColor.toString().replace(",]", "]"), borderColor, datos.toString().replace(",]", "]"));
                String data = String.format("{type: 'pie', data: { %s, %s }, options: {responsive: true}}", labels.toString().replace(",]", "]"), datasets);
                resPie.add(data);
                String dataBar = String.format("{type: 'bar', data: { %s, %s }, options: { scales: { yAxes: [ { ticks: { beginAtZero: true } } ] } }}", labels.toString().replace(",]", "]"), datasets);
                resBar.add(dataBar);
                String dataLine = String.format("{type: 'line', data: { %s, %s }, options: { scales: { yAxes: [ { ticks: { beginAtZero: true } } ] } }}", labels.toString().replace(",]", "]"), datasets);
                resLine.add(dataLine);
            }

//            for (Pregunta pregunta : preguntas) {
//                String label = "";
//                StringBuilder labels = new StringBuilder();
//                labels.append("labels: [");
//                StringBuilder datos = new StringBuilder();
//                datos.append("data: [");
//                StringBuilder backgroundColor = new StringBuilder();
//                backgroundColor.append("backgroundColor: [ ");
//                int contador = 0;
//                label = "'" + pregunta.getTitulo() + "'";
//                for (Resultado resultado : resultados) {
//                    if (resultado.getPregunta_id() == pregunta.getId()) {
//                        labels.append("'" + resultado.getDescripcion() + "',");
//                        datos.append(resultado.getCantidad() + ",");
//                        backgroundColor.append("'" + colores[contador] + "',");
//                        contador++;
//                    }
//                }
//                datos.append("]");
//                labels.append("]");
//                backgroundColor.append("]");
//                String borderColor = "'RGB(192, 192, 192)'";
//                String datasets = String.format("datasets: [{ label: %s, %s, borderColor: %s, %s }]", label, backgroundColor.toString().replace(",]", "]"), borderColor, datos.toString().replace(",]", "]"));
//                String data = String.format("{type: 'pie', data: { %s, %s }, options: {responsive: true}}", labels.toString().replace(",]", "]"), datasets);
//                res.add(data);
//            }
            request.getSession().setAttribute("datasetPie", resPie);
            request.getSession().setAttribute("datasetBar", resBar);
            request.getSession().setAttribute("datasetLine", resLine);

            response.sendRedirect("verresultados.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(CargarResultadosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
