/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.servlet;

import cl.proyecto.modelo.CuentaUsuario;
import cl.proyecto.modelo.Direccion;
import cl.proyecto.modelo.Genero;
import cl.proyecto.modelo.Persona;
import cl.proyecto.modelo.Rol;
import cl.proyecto.modelo.Telefono;
import cl.proyecto.negocio.RegistroCuentaUsuario;
import cl.proyecto.negocio.RegistroDireccion;
import cl.proyecto.negocio.RegistroGenero;
import cl.proyecto.negocio.RegistroPersona;
import cl.proyecto.negocio.RegistroRol;
import cl.proyecto.negocio.RegistroTelefono;
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
public class ActualizarEncuestadorServlet extends HttpServlet {

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
            out.println("<title>Servlet ActualizarEncuestadorServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarEncuestadorServlet at " + request.getContextPath() + "</h1>");
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
            String[] run = request.getParameter("run").split("-");
            RegistroPersona regPersona = new RegistroPersona();
            Persona persona = regPersona.obtenerPersonaPorRun(Integer.parseInt(run[0]));
            if (persona != null) {
                String rut = run[0];
                String dv = run[1];
                String nombre = request.getParameter("nombre");
                String apellidoPaterno = request.getParameter("paterno");
                String apellidoMaterno = request.getParameter("materno");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = formatter.parse(request.getParameter("fecha"));
                int genero_id = Integer.parseInt(request.getParameter("genero"));
                int comuna_id = Integer.parseInt(request.getParameter("comuna"));
                String direccion = request.getParameter("direccion");
                String detalle = request.getParameter("departamento");
                String celular = request.getParameter("celular");
                String fijo = request.getParameter("fijo");
                String email = request.getParameter("email");
                int rol_id = 4;
                String pass = request.getParameter("pass");
                String editarPass = request.getParameter("editarPass");
                
                //Dirección
                Direccion d = new Direccion(persona.getDireccion().getId(), direccion, detalle, comuna_id);
                RegistroDireccion registroDireccion = new RegistroDireccion();
                registroDireccion.modificar(d);

                //genero
                RegistroGenero registroGenero = new RegistroGenero();
                Genero genero = registroGenero.obtenerGenero(genero_id);

                //telefónos
                ArrayList<Telefono> telefonos = new ArrayList<>();
                //cel
                RegistroTelefono registroTelefono = new RegistroTelefono();
                Telefono cel = new Telefono(persona.getTelefono().get(0).getId(), celular, "+56", 2, persona.getId());
                registroTelefono.modificar(cel);
                //fijo
                if (!fijo.isEmpty()) {
                    Telefono f = new Telefono(persona.getTelefono().get(1).getId(), fijo, "+56", 1, persona.getId());
                    registroTelefono.modificar(f);
                }

                //Rol
                RegistroRol registroRol = new RegistroRol();
                Rol rol = registroRol.obtenerRol(rol_id);

                //cuenta
                RegistroCuentaUsuario registroCuentaUsuario = new RegistroCuentaUsuario();
                CuentaUsuario cuenta = new CuentaUsuario(persona.getCuenta().getId(), rut.concat(dv), persona.getCuenta().getContrasenia(), rol, persona.getId());
                
                
                Persona p = new Persona(persona.getId(), Integer.parseInt(rut), dv.charAt(0), nombre, apellidoPaterno, apellidoMaterno, fecha, email, telefonos, cuenta, genero, d);
                
                if(editarPass != null)
                {
                    p.getCuenta().setContrasenia(pass);
                    cuenta.setContrasenia(pass);
                }
                registroCuentaUsuario.modificar(cuenta);
                regPersona.modificarPorId(p);
                request.getSession().setAttribute("cuentaExito", "Cuenta modificada Exitosamente");
                response.sendRedirect("modificarencuestador.jsp");

            } else {
                request.getSession().setAttribute("cuentaError", "Run no registrado, no se puede actualizar");
                response.sendRedirect("modificarencuestador.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarCuentaServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("cuentaError", "No se pudo registar la cuenta, verifique la información");
            response.sendRedirect("modificarencuestador.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(ActualizarCuentaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
