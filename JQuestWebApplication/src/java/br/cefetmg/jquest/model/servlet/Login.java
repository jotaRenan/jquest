/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.UseLogDAOImpl;
import br.cefetmg.jquest.model.dao.UserDAOImpl;
import br.cefetmg.jquest.model.domain.UseLog;
import br.cefetmg.jquest.model.domain.User;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.UseLogManagement;
import br.cefetmg.jquest.model.service.UseLogManagementImpl;
import br.cefetmg.jquest.model.service.UserManagement;
import br.cefetmg.jquest.model.service.UserManagementImpl;
import br.cefetmg.jquest.model.servlet.util.ServletUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paula Ribeiro
 * @url /Login
 */
public class Login extends HttpServlet {
    private User user;
    private UserManagement userManagement;
    private String result;
    private ServletUtil util;
    private Gson gson;
    private UseLogManagement useLogManagement;
    private UseLog useLog;
    
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
             
            util = new ServletUtil();
            // recebe a questao enviada no payload via POST
            String payload = util.getJson(request);
            user = this.userFromJson(payload);
            String email = user.getEmail();
            String password = user.getPassword();
            
            user = userManagement.getUserByEmail(email);
            
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    result = "{"
                           + "\"id\": " + user.getId()
                           + ", \"idt\": \"" + user.getIdtProfile() + "\""
                           + "}";
                    response.setStatus(HttpServletResponse.SC_OK);
                    
                    useLogManagement = new UseLogManagementImpl(UseLogDAOImpl.getInstance());
                    useLog = new UseLog();
                    useLog.setIdUser(user.getId());
                    useLog.setUseDate(new Date());
                    
                    useLogManagement.useLogInsert(useLog);
                }
                
                else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            }
            
            else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (PersistenceException | BusinessException ex) {
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

    private User userFromJson(String str) {
        gson = new Gson();
        user = gson.fromJson(str, User.class);
        return user;
    }
}
