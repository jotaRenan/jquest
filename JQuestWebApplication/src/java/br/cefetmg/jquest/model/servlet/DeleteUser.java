/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.UserDAO;
import br.cefetmg.jquest.model.dao.UserDAOImpl;
import br.cefetmg.jquest.model.domain.User;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.UserManagement;
import br.cefetmg.jquest.model.service.UserManagementImpl;
import br.cefetmg.jquest.model.servlet.util.ServletUtil;
import com.google.gson.Gson;
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
public class DeleteUser extends HttpServlet {
    private UserManagement userManagement;

    private String result;

    private ServletUtil util;

    private Gson gson;
    

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
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        try {
            util = new ServletUtil();
            String payload = util.getJson(request);
            User user = this.userFromJson(payload);
            userManagement = new UserManagementImpl(UserDAOImpl.getInstance());
            
            userManagement.userRemove(user.getId());
            
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (PersistenceException e) {
            response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
            result = "";
        }
        finally {
            if (result != null) {
                PrintWriter writer = response.getWriter();
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Updates Users from the database";
    }// </editor-fold>
    
    private User userFromJson(String str) {
        gson = new Gson();
        User user = gson.fromJson(str, User.class);
        return user;
    }

}
