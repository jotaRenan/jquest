/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.DissertiveQuestionAnswerDAOImpl;
import br.cefetmg.jquest.model.dao.MultipleChoiceAnswerDAOImpl;
import br.cefetmg.jquest.model.dao.TrueOrFalseAnswerDAOImpl;
import br.cefetmg.jquest.model.domain.DissertiveQuestionAnswer;
import br.cefetmg.jquest.model.domain.MultipleChoiceAnswer;
import br.cefetmg.jquest.model.domain.TrueOrFalseAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.DissertiveQuestionAnswerManagement;
import br.cefetmg.jquest.model.service.DissertiveQuestionAnswerManagementImpl;
import br.cefetmg.jquest.model.service.MultipleChoiceAnswerManagement;
import br.cefetmg.jquest.model.service.MultipleChoiceAnswerManagmentImpl;
import br.cefetmg.jquest.model.service.TrueOrFalseAnswerManagement;
import br.cefetmg.jquest.model.service.TrueOrFalseAnswerManagementImpl;
import br.cefetmg.jquest.model.servlet.util.ServletUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Paula Ribeiro
 */
public class CreateUserAnswerServlet extends HttpServlet {
    private MultipleChoiceAnswerManagement mcAnswerManagement;
    private TrueOrFalseAnswerManagement tofAnswerManagement;
    private DissertiveQuestionAnswerManagement dAnswerManagement;
    private String result;
    private ServletUtil util;
    private JSONObject jsonObject;

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
        
        try {               
            util = new ServletUtil();
            // recebe a resposta enviada no payload via POST
            String payload = util.getJson(request);
            jsonObject = new JSONObject(payload);
            char idt = jsonObject.getString("idt").charAt(0);
            
            switch(idt) {
                case 'A':
                    this.dissertiveQuestion();
                    break;

                case 'M':
                    this.multipleChoiceQuestion();
                    break;

                case 'V':
                    this.trueOrFalseQuestion();
                    break;
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
        return "Saves user Answer";
    }
    
    private void multipleChoiceQuestion() throws BusinessException, PersistenceException {
        mcAnswerManagement = new MultipleChoiceAnswerManagmentImpl(MultipleChoiceAnswerDAOImpl.getInstance()); 
        MultipleChoiceAnswer mcAnswer = new MultipleChoiceAnswer();
        
        mcAnswer.setQuestionId(jsonObject.getLong("questionId"));
        mcAnswer.setUseSeq(jsonObject.getLong("seqUse"));
        mcAnswer.setUserId(jsonObject.getLong("userId"));
        mcAnswer.setOptionSeq(jsonObject.getLong("optionSeq"));
        
        mcAnswerManagement.insert(mcAnswer);
    }
    
    private void trueOrFalseQuestion () throws BusinessException, PersistenceException {
        tofAnswerManagement = new TrueOrFalseAnswerManagementImpl(TrueOrFalseAnswerDAOImpl.getInstance()); 
        TrueOrFalseAnswer tofAnswer = new TrueOrFalseAnswer();
        
        tofAnswer.setQuestionId(jsonObject.getLong("questionId"));
        tofAnswer.setUseSeq(jsonObject.getLong("seqUse"));
        tofAnswer.setUserId(jsonObject.getLong("userId"));
        tofAnswer.setOptionSeq(jsonObject.getLong("optionSeq"));
        
        tofAnswerManagement.insert(tofAnswer);   
    }
    
    private void dissertiveQuestion () throws BusinessException, PersistenceException {
        dAnswerManagement = new DissertiveQuestionAnswerManagementImpl(DissertiveQuestionAnswerDAOImpl.getInstance()); 
        DissertiveQuestionAnswer dQuestionAnswer = new DissertiveQuestionAnswer();
        
        dQuestionAnswer.setQuestionID(jsonObject.getLong("questionId"));
        dQuestionAnswer.setSeqAnswerUser(new Long(jsonObject.getInt("seqUse")));
        dQuestionAnswer.setTxtAnswer(jsonObject.getString("txtAnswer"));
        dQuestionAnswer.setUserID(new Long(jsonObject.getInt("userId")));
        dQuestionAnswer.setValueScore(jsonObject.getInt("valueScore"));
        
        dAnswerManagement.DissertiveQuestionAnswerInsert(dQuestionAnswer);
        
    }
    
}
