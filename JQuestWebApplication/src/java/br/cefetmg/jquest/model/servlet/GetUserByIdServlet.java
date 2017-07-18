/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.UserDAOImpl;
import br.cefetmg.jquest.model.domain.User;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.UserManagement;
import br.cefetmg.jquest.model.service.UserManagementImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Breno Mariz
 */
public class GetUserByIdServlet extends HttpServlet {
    private User user;
    private UserManagement userManagement;
   
    private String result;
    
    public GetUserByIdServlet() {
        user = null;
        result = "";
    }
    
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin","*");
        
        String input = request.getParameter("id");
        Long id = new Long(input);
        
        try {
            userManagement = new UserManagementImpl(UserDAOImpl.getInstance());
            user = userManagement.getUserById(id);
            
            if (user != null) {
                result = "{"
                    + "\"userName\": " + user.getUserName()
                    + ", \"email\": \"" + user.getEmail() + "\""
                    + ", \"id\": \"" + user.getId() + "\""
                    + ", \"password\": \"" + user.getPassword() + "\""
                    + ", \"idtProfile\": \"" + user.getIdtProfile() + "\""
                    + "}";          
            }
            else {
                result = "[]";
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            
        } catch (PersistenceException ex) {
            result = ex.getMessage();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Gets a user by its Id";
    }// </editor-fold>

}
