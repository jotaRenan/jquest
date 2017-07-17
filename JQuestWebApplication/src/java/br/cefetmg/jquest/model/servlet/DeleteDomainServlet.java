/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.DomainDAO;
import br.cefetmg.jquest.model.dao.DomainDAOImpl;
import br.cefetmg.jquest.model.domain.Domain;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.DomainManagement;
import br.cefetmg.jquest.model.service.DomainManagementImpl;
import br.cefetmg.jquest.model.servlet.util.ServletUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thalesgsn
 */
@WebServlet(name = "DeleteDomainServlet", urlPatterns = {"/DeleteDomainServlet"})
public class DeleteDomainServlet extends HttpServlet {

 
    private DomainManagement domainManagement;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        try {
            util = new ServletUtil();
            
            // recebe o dominio enviado no payload via POST
            String payload = util.getJson(request);
            Domain domain = this.domainFromJson(payload);
            DomainDAO domainDAO = DomainDAOImpl.getInstance();
            domainManagement = new DomainManagementImpl(domainDAO);
            domainManagement.domainRemove(domain.getId());
            
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (PersistenceException ex) {
            result = ex.getMessage();
            response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
        }
        
        finally {
            if (result != null) {
                PrintWriter writer = response.getWriter();
                writer.println(result);
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
        return "Receives json to create a new domain";
    }
    
    private Domain domainFromJson(String str) {
        gson = new Gson();
        Domain domain = gson.fromJson(str, Domain.class);
        return domain;
    }

}
