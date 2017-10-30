/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Clientes;
import Modelo.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author GUILLERMO NIÑO
 */
@WebServlet(name = "ClientesController", urlPatterns = {"/ClientesController"})
public class ClientesController extends HttpServlet {

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

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("create")) {
            Registrar(request, response);
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

    private void Registrar(HttpServletRequest request, HttpServletResponse response) {
        
         String razon_social = request.getParameter("razon_social");
        String nit = request.getParameter("nit");
        String ciudad = request.getParameter("ciudad");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String contrasena = request.getParameter("contrasena");

        Clientes cliente = new Clientes(razon_social, nit, ciudad, direccion, telefono, contrasena);

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        sesion.save(cliente);
        sesion.getTransaction().commit();
        sesion.close();

        try {
            response.sendRedirect("ClientesController?action=admin");//Se pierde la información
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    }
        
    


