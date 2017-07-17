/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.QuestionDAO;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.QuestionManagement;
import br.cefetmg.jquest.model.service.QuestionManagementImpl;
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
 * @url /CreateQuestionServlet
 */
public class CreateQuestionServlet extends HttpServlet {
    private String result;
    private Gson gson;
    private ServletUtil util;
    private QuestionManagement questionManagement;

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
            Question question = this.questionFromJson(payload);
            QuestionDAO questionDAO = QuestionDAOImpl.getInstance();
            questionManagement = new QuestionManagementImpl(questionDAO);
            Long id = questionManagement.questionInsert(question);
            result = "{\"id\": " + id + "}";
            
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
        return "Receives json to create a new question";
    }
    
    private Question questionFromJson(String str) {
        gson = new Gson();
        Question question = gson.fromJson(str, Question.class);
        return question;
    }

}
