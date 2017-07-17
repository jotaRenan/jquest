/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.QuestionAlternativeDAO;
import br.cefetmg.jquest.model.dao.QuestionAlternativeDAOImpl;
import br.cefetmg.jquest.model.domain.QuestionAlternative;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.QuestionAlternativeManagement;
import br.cefetmg.jquest.model.service.QuestionAlternativeManagementImpl;
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
 * @author Paula Ribeiro
 */
public class CreateQuestionAlternativeServlet extends HttpServlet {
    private Gson gson;
    private String result;
    private ServletUtil util;
    private QuestionAlternativeManagement questionAlternativeManagement;
    

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
        result = "";
        
        try {
            
            util = new ServletUtil();
            // recebe a questao enviada no payload via POST
            String payload = util.getJson(request);
            QuestionAlternative questionAlternative = this.questionAlternativeFromJson(payload);
            QuestionAlternativeDAO questionAlternativeDAO = QuestionAlternativeDAOImpl.getInstance();
            questionAlternativeManagement = new QuestionAlternativeManagementImpl(questionAlternativeDAO);
            Long seq = questionAlternativeManagement.insert(questionAlternative);
            result = "{\"id\": " + seq + "}";
            
        } catch (BusinessException | PersistenceException ex) {
            result = ex.getMessage();
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
        return "Receives json to create a new question alternative";
    }
    
    private QuestionAlternative questionAlternativeFromJson(String str) {
        gson = new Gson();
        QuestionAlternative questionAlternative = gson.fromJson(str, QuestionAlternative.class);
        return questionAlternative;
    }

}
