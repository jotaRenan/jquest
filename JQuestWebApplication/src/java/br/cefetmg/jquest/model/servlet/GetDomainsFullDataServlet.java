/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.DomainDAOImpl;
import br.cefetmg.jquest.model.dao.ModuleDAOImpl;
import br.cefetmg.jquest.model.domain.Domain;
import br.cefetmg.jquest.model.domain.Module;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.DomainManagement;
import br.cefetmg.jquest.model.service.DomainManagementImpl;
import br.cefetmg.jquest.model.service.ModuleManagement;
import br.cefetmg.jquest.model.service.ModuleManagementImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paula Ribeiro
 * @url /GetDomainsFullDataServlet
 */
public class GetDomainsFullDataServlet extends HttpServlet {
    private List<Domain> domainsList;
    private List<Module> modulesList;
    private DomainManagement domainManagement;
    private ModuleManagement moduleManagement;
    private String result;
    
    public GetDomainsFullDataServlet() {
        domainsList = null;
        modulesList = null;
        result = "";
    }
    
    /* Handles the HTTP <code>GET</code> method.
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
        
        try {         
            //busca dominios no bd
            domainManagement = new DomainManagementImpl(DomainDAOImpl.getInstance());
            domainsList = domainManagement.getAll();
            
            if (!domainsList.isEmpty()) {
                result = "[";
                for (Domain domain: domainsList) {
                    
                    moduleManagement = new ModuleManagementImpl(ModuleDAOImpl.getInstance());
                    modulesList = moduleManagement.getModulesByDomainId(domain.getId());
                    
                    result += "{"
                            + "\"domainId\": " + domain.getId()
                            + ", \"domainName\": \"" + domain.getName() + "\""
                            + ", \"domainDescription\": \"" + domain.getDescription() + "\""
                            + ", \"modules\": [";
                    
                    if (modulesList != null) {
                        for (Module module: modulesList) {
                            result += "{"
                                    + "\"moduleId\": " + module.getId()
                                    + ", \"moduleName\": \"" + module.getName() + "\""
                                    + ", \"moduleDescription\": \"" + domain.getDescription() +"\""
                                    + "}, ";
                        }
                        int ult = result.lastIndexOf(',');
                        result = result.substring(0, ult);
                        result += "]";
                    }
                    
                    else {
                        result += "]";
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                    
                    result += "},";
                }
                int ult = result.lastIndexOf(',');
                result = result.substring(0, ult);
                result += "]";
            
            }
            
            else {
                result = "[]";
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            
        } catch (PersistenceException ex) {
            result = ex.getMessage();
        }
        
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        out.println(result);
        
    }

}
