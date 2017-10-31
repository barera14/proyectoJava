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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
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
        } else if (action.equalsIgnoreCase("admin")) {
            Admin(request, response);

        } else if (action.equalsIgnoreCase("update")) {
            actualizar(request, response);

        } else if (action.equalsIgnoreCase("delete")) {
            eliminar(request, response);

        }

        //Tarea para mañana que consulte desde la base de datos.
        // Hacer una jsp dentro de salones que se llame admin y poner una tabla con titulos ubicación 
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
        String password = request.getParameter("password");

        Clientes cliente = new Clientes(razon_social, nit, ciudad, direccion, telefono, password);

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

    private void Admin(HttpServletRequest request, HttpServletResponse response) {

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Query q = sesion.createQuery("FROM Clientes");
        //Query q = sesion.createQuery("FROM Odontologos WHERE especialidad = 'General'"); Con el WHERE para condición
        ArrayList listaObjetos = (ArrayList) q.list();
        sesion.close();

        ArrayList<Clientes> cli = new ArrayList<Clientes>();
        for (Object client : listaObjetos) {

            Clientes cliente = (Clientes) client;
            cli.add(cliente);

        }

        request.setAttribute("ArrayCliente", cli);

        try {
            request.getRequestDispatcher("AdministrarClientes.jsp").forward(request, response);//Redirecionar
        } catch (ServletException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Clientes cliente = (Clientes) sesion.get(Clientes.class, Integer.parseInt(request.getParameter("id")));

        if (request.getMethod().equalsIgnoreCase("GET")) {
            request.setAttribute("cliente", cliente);
            try {
                request.getRequestDispatcher("UpdateClientes.jsp").forward(request, response);//Redirecionar
            } catch (ServletException ex) {
                Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            cliente.setRazonSocial(request.getParameter("razon_social"));
            cliente.setNit(request.getParameter("nit"));
            cliente.setCiudad(request.getParameter("ciudad"));
            cliente.setDireccion(request.getParameter("direccion"));
            cliente.setTelefono(request.getParameter("telefono"));

            sesion.beginTransaction();
            sesion.saveOrUpdate(cliente);
            sesion.getTransaction().commit();

            try {
                response.sendRedirect("SalonesController?action=admin");
            } catch (IOException ex) {
                Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Clientes clientes = (Clientes) sesion.get(Clientes.class, Integer.parseInt(request.getParameter("id")));

        sesion.beginTransaction();
        sesion.delete(clientes);
        sesion.getTransaction().commit();
        sesion.close();

        try {
            response.sendRedirect("ClientesController?action=admin");
        } catch (IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
