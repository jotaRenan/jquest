/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.ModuleDAOImpl;
import br.cefetmg.jquest.model.domain.Module;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.ModuleManagement;
import br.cefetmg.jquest.model.service.ModuleManagementImpl;
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
 * @author Temp
 */
public class DeleteModuleSevlet extends HttpServlet {
private ModuleManagement moduleManagement;
    
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
            //Tenta a edição no banco de dados
            util = new ServletUtil();
            String payload = util.getJson(request);
            Module module = this.moduleFromJson(payload);
            moduleManagement = new ModuleManagementImpl(ModuleDAOImpl.getInstance());
            
            moduleManagement.moduleRemove(module.getId(), module.getId());
            
            response.setStatus(HttpServletResponse.SC_OK);
        } catch ( PersistenceException e) {
            response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
            result = "";
        }
        finally{
            if(result != null){
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
        return "Updates Module from the database";
    }// </editor-fold>

    private Module moduleFromJson(String str) {
        gson = new Gson();
        Module module = gson.fromJson(str, Module.class);
        return module;
    }
}
