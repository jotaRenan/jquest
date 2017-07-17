/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.servlet;

import br.cefetmg.jquest.model.dao.CommentaryDAOImpl;
import br.cefetmg.jquest.model.dao.ForumDAOImpl;
import br.cefetmg.jquest.model.dao.UserDAOImpl;
import br.cefetmg.jquest.model.domain.Commentary;
import br.cefetmg.jquest.model.domain.Forum;
import br.cefetmg.jquest.model.domain.User;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.service.CommentaryManagement;
import br.cefetmg.jquest.model.service.CommentaryManagementImpl;
import br.cefetmg.jquest.model.service.ForumManagement;
import br.cefetmg.jquest.model.service.ForumManagementImpl;
import br.cefetmg.jquest.model.service.UserManagement;
import br.cefetmg.jquest.model.service.UserManagementImpl;
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
 * @url /GetForunsByQuestionIdServlet
 */
public class GetForunsByQuestionIdServlet extends HttpServlet {
    private ForumManagement forumManagement;
    private UserManagement userManagement;
    private CommentaryManagement commentaryManagement;
    private String result;
    private List<Forum> forunsList;
    private List<Commentary> commentariesList;
    private User user;
    
    public GetForunsByQuestionIdServlet() {
        forunsList = null;
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
            
            forumManagement = new ForumManagementImpl(ForumDAOImpl.getInstance());
            userManagement = new UserManagementImpl(UserDAOImpl.getInstance());
            commentaryManagement = new CommentaryManagementImpl(CommentaryDAOImpl.getInstance());
            
            //pega os foruns da questao do bd
            forunsList = forumManagement.getAllForunsByQuestionID(id);
            
            if (!forunsList.isEmpty()) {
                result = "[";
                for (Forum forum: forunsList) {
                    user = userManagement.getUserById(forum.getUserId());
                    commentariesList = commentaryManagement.getCommentariesByForumId(forum.getDiscussionSeq(), id);
                            
                    result += "{"
                           + "\"id\": " + forum.getDiscussionSeq()
                           + ", \"userId\": " + user.getId()
                           + ", \"userName\": \"" + user.getUserName() + "\""
                           + ", \"title\": \"" + forum.getName() + "\""
                           + ", \"text\": \"" + forum.getDescription() + "\""
                           + ", \"comments\": [";
                        
                        if (!commentariesList.isEmpty()) {
                            for (Commentary commentary: commentariesList) {
                                user = userManagement.getUserById(commentary.getUserId());

                                result += "{"
                                       + "\"id\": " + commentary.getCommentarySeq()
                                       + ", \"userId\": " + user.getId()
                                       + ", \"userName\": \"" + user.getUserName() + "\""
                                       + ", \"text\": \"" + commentary.getTextCommentary() + "\""
                                       + "},";
                            }
                            
                            int ult = result.lastIndexOf(',');
                            result = result.substring(0, ult);
                            
                            result += "]";
                        }
                        
                        else {
                            result += "]";
                        }
                    
                    result += "},";
                }
                
                int ult = result.lastIndexOf(',');
                result = result.substring(0, ult);
                
                result += "]";
            }
            
            else {
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
        return "Returns all discussions from a forum";
    }

}
