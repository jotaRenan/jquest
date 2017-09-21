/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.QuestionManagement;
import br.cefetmg.jquest.model.service.QuestionManagementImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Breno Mariz
 */
public class GetQuestionsByGC extends HttpServlet {
    private QuestionManagement questionManagement;
    private String result;
    private List<Question> questionsList;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        questionManagement = new QuestionManagementImpl(QuestionDAOImpl.getInstance());
        
        try {
            //pega parametros da URL
            String user = request.getParameter("userId");
            Long userId = new Long(user);
            String IdtUser = request.getParameter("IdtUser");
            Long IdtProfileUser = new Long(IdtUser);
            
            questionsList = questionManagement.getQuestionsByCreatorId(userId);

            if (IdtProfileUser.equals(2)) { //checa se o usuário é GC
                if (questionsList != null) {
                    result = "[";
                    for (Question question : questionsList) {
                        result += "{"
                                + "\"id\": " + question.getId()
                                + ", \"heading\": \"" + question.getHeadline() + "\""
                                + "},";
                    }
                    int ult = result.lastIndexOf(',');
                    result = result.substring(0, ult);
                    result += "]";
                } else {
                    result = "[]";
                }
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
        return "Get questions by GC";
    }// </editor-fold>

}
