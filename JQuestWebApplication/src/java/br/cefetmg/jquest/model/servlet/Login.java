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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paula Ribeiro
 */
public class Login extends HttpServlet {
    private User user;
    private UserManagement userManagement;
    private String result;
    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        result = "";
        userManagement = new UserManagementImpl(UserDAOImpl.getInstance());
        
        try {
                        
            //pega parametro da URL
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            user = userManagement.getUserByEmail(email);
            
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    result = "{"
                           + "\"id\": " + user.getId()
                           + ", \"idt\": \"" + user.getIdtProfile() + "\""
                           + "}";
                    response.setStatus(HttpServletResponse.SC_OK);
                }
                
                else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            }
            
            else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (PersistenceException ex) {
            result = ex.getMessage();
        }
        
        PrintWriter out = response.getWriter();
        out.println(result);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Logs users in";
    }

}
