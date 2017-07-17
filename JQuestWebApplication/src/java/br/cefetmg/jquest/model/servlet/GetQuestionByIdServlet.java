/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.DomainDAOImpl;
import br.cefetmg.jquest.model.dao.ModuleDAOImpl;
import br.cefetmg.jquest.model.dao.QuestionAlternativeDAOImpl;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.domain.QuestionAlternative;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.DomainManagement;
import br.cefetmg.jquest.model.service.DomainManagementImpl;
import br.cefetmg.jquest.model.service.ModuleManagement;
import br.cefetmg.jquest.model.service.ModuleManagementImpl;
import br.cefetmg.jquest.model.service.QuestionAlternativeManagement;
import br.cefetmg.jquest.model.service.QuestionAlternativeManagementImpl;
import br.cefetmg.jquest.model.service.QuestionManagement;
import br.cefetmg.jquest.model.service.QuestionManagementImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    private DomainManagement domainManagement;
    private ModuleManagement moduleManagement;
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
        
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //pega parametro da URL
        String input = request.getParameter("id");
        Long id = new Long(input);
        
        try {                
            //busca a questão no banco de dados
            questionManagement = new QuestionManagementImpl(QuestionDAOImpl.getInstance());
            question = questionManagement.getQuestionById(id);   
            
            //testa se questão foi recebida
            if (question != null) {
                result = "";
                switch(question.getType()) {
                    case 'A':
                        result += this.dissertiveQuestion(question);
                        break;

                    case 'M':
                        result += this.multipleChoiceQuestion(question);
                        break;

                    case 'V':
                        result += this.trueOrFalseQuestion(question);
                        break;
                }

            }

            else {
                result = "[]";
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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
    
    private String multipleChoiceQuestion(Question q) {
        List<QuestionAlternative> alternativesList = getAlternatives(q.getId());
        String res = "{"
                + "\"id\": " + q.getId()
                + ", \"domain\": \"" + this.getDomain(q.getDomainId()) + "\""
                + ", \"module\": \"" + this.getModule(q.getModuleId(), q.getDomainId()) + "\""
                + ", \"heading\": \"" + q.getHeadline() + "\""
                + ", \"idt\": \"" + q.getType() + "\""
                + ", \"correctIndex\": " + this.selectCorrectAnswer(alternativesList)
                + ", \"alternatives\": [";
                       for (QuestionAlternative alternative: alternativesList) {
                           res += "\"" + alternative.getAssertionText()
                               + "\", ";
                       }
       int ult = res.lastIndexOf(',');
       res = res.substring(0, ult);
       res += "]}";
       return res;
    }
    
    private String trueOrFalseQuestion (Question q) {
        List<QuestionAlternative> alternativesList = getAlternatives(q.getId());
        String res = "{"
                + "\"id\": " + q.getId()
                + ", \"domain\": \"" + this.getDomain(q.getDomainId()) + "\""
                + ", \"module\": \"" + this.getModule(q.getModuleId(), q.getDomainId()) + "\""
                + ", \"heading\": \"" + q.getHeadline() + "\""
                + ", \"idt\": \"" + q.getType() + "\"" 
                + ", \"alternatives\": [";
                       for (QuestionAlternative alternative: alternativesList) {
                           res += "{"
                               + "\"assertive\": \"" + alternative.getAssertionText() + "\""
                               + ", \"isCorrect\": " + alternative.isIsCorrect()
                               + "},    ";
                       }
       int ult = res.lastIndexOf(',');
       res = res.substring(0, ult);
       res += "]}";
       return res;
    }
    
    private String dissertiveQuestion (Question q) {
        String res = "{"
                + "\"id\": " + q.getId()
                + ", \"domain\": \"" + this.getDomain(q.getDomainId()) + "\""
                + ", \"module\": \"" + this.getModule(q.getModuleId(), q.getDomainId()) + "\""
                + ", \"heading\": \"" + q.getHeadline() + "\""
                + ", \"idt\": \"" + q.getType() + "\""
                + "}";
        return res;
    }
    
    private List<QuestionAlternative> getAlternatives(Long questionId) {
        List <QuestionAlternative> list = null;
        try {
            //busca alternativas no banco de dados
            questionAlternativeManagement = new QuestionAlternativeManagementImpl(QuestionAlternativeDAOImpl.getInstance());
            list = questionAlternativeManagement.getAlternativesByQuestionId(questionId);              
        } catch (PersistenceException ex) {
            Logger.getLogger(GetQuestionsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    private Long selectCorrectAnswer (List<QuestionAlternative> alternatives) {
        Long seq = null;
        for (QuestionAlternative alternative: alternatives) {
            if (alternative.isIsCorrect())
                seq = alternative.getOptionSeq();
        }
        return seq;
    }
    
    private String getDomain(Long domainId) {
        String domain = "";
        try {
            domainManagement = new DomainManagementImpl(DomainDAOImpl.getInstance());
            domain = domainManagement.getDomainById(domainId).getName();
        } catch (PersistenceException ex) {
            Logger.getLogger(GetQuestionsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return domain;
    }
    
    private String getModule(Long moduleId, Long domainId) {
        String module = "";
        try {
            moduleManagement = new ModuleManagementImpl(ModuleDAOImpl.getInstance());
            module = moduleManagement.getModuleById(moduleId, domainId).getName();
        } catch (PersistenceException ex) {
            Logger.getLogger(GetQuestionsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return module;
    }
    
}
