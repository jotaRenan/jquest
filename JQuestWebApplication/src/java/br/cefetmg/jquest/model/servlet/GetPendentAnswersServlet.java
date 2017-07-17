/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.DissertiveQuestionAnswerDAOImpl;
import br.cefetmg.jquest.model.domain.DissertiveQuestionAnswer;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.DissertiveQuestionAnswerManagement;
import br.cefetmg.jquest.model.service.DissertiveQuestionAnswerManagementImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Temp
 */
public class GetPendentAnswersServlet extends HttpServlet {
    private DissertiveQuestionAnswerManagement dissertiveQuestionAnswerManagement;
    private String result;
    private List<DissertiveQuestionAnswer> dissertiveQuestionAnswerList;
    
    public GetPendentAnswersServlet() {
        dissertiveQuestionAnswerList = null;
        result = "";
    }

    /**
     * Handles the HTTP <code>GET</code> method.
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
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        //pega parametro da URL
        String input = request.getParameter("id");
        Long id = new Long(input);
        
        try {
            
            dissertiveQuestionAnswerManagement = new DissertiveQuestionAnswerManagementImpl(DissertiveQuestionAnswerDAOImpl.getInstance());

            
            //pega os foruns da questao do bd
            dissertiveQuestionAnswerList = dissertiveQuestionAnswerManagement.getPendentDissertativeQuestions();
            
            if (!dissertiveQuestionAnswerList.isEmpty()) {
                result = "[";
                for (DissertiveQuestionAnswer dissertiveQuestionAnswer: dissertiveQuestionAnswerList) {
                            
                    result += "{"
                           + " \"userId\": " + dissertiveQuestionAnswer.getUserID() 
                           + ", \"questionId\": " + dissertiveQuestionAnswer.getQuestionID() 
                           + ", \"seqUse\": " + dissertiveQuestionAnswer.getSeqAnswerUser()
                           + ", \"TXT_answer\": \"" + dissertiveQuestionAnswer.getTxtAnswer() + "\"";
                    result += "},";
                }
                
                int ult = result.lastIndexOf(',');
                result = result.substring(0, ult);
                
                result += "]";
            } else {
                result = "[]";
            }
        
        } catch (PersistenceException ex) {
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
        return "Returns all pendent Answers from dissertiveQuestionAnswer table";
    }
}
