/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.QuestionAlternativeDAOImpl;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.domain.QuestionAlternative;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.QuestionAlternativeManagement;
import br.cefetmg.jquest.model.service.QuestionAlternativeManagementImpl;
import br.cefetmg.jquest.model.service.QuestionManagement;
import br.cefetmg.jquest.model.service.QuestionManagementImpl;
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
 * @url /GetQuestionByIdServlet
 */
public class GetQuestionByIdServlet extends HttpServlet {
    private Question question;
    private QuestionManagement questionManagement;
    private QuestionAlternativeManagement questionAlternativeManagement;
    private String result;
    
    public GetQuestionByIdServlet() {
        question = null;
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
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //pega parametro da URL
        String input = request.getParameter("id");
        Long id = new Long(input);
        
        try {                
            //busca a questão no banco de dados
            questionManagement = new QuestionManagementImpl(QuestionDAOImpl.getInstance());
            question = questionManagement.getQuestionById(id);   
            
            
            //busca alternativas no banco de dados
            questionAlternativeManagement = new QuestionAlternativeManagementImpl(QuestionAlternativeDAOImpl.getInstance());
            List<QuestionAlternative> alternativesList = questionAlternativeManagement.getAlternativesByQuestionId(id);
            
            //testa se questão foi recebida
            if (question != null) {
                result = "{"
                         + "     id: " + question.getId()
                         + ",    heading: '" + question.getHeadline()
                         + "',    isMultipleChoice: " + this.isMultipleChoice(question.getType())
                         + ",    correct: " + this.selectCorrectAnswer(alternativesList)
                         + ",    alternatives: [";
                                for (QuestionAlternative alternative: alternativesList) {
                                    result += alternative.getAssertionText();
                                    result += ",    ";
                                }
                int ult = result.lastIndexOf(',');
                result = result.substring(0, ult);
                result += "]    }";

            }

            else {
                result = "[]";
            }
        
        }
        
        catch (PersistenceException ex) {
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
        return "Gets a question by its Id";
    }// </editor-fold>
    
    private boolean isMultipleChoice(char idt) {
        return idt == 'M';
    }
    
    private Long selectCorrectAnswer (List<QuestionAlternative> alternatives) {
        Long seq = null;
        for (QuestionAlternative alternative: alternatives) {
            if (alternative.isIsCorrect())
                seq = alternative.getOptionSeq();
        }
        return seq;
    }
    
}
