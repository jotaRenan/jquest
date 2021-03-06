/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Commentary;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thalesgsn
 */
public class CommentaryDAOImpl implements CommentaryDAO {
    private static CommentaryDAOImpl commentaryDAO = null;

    public static CommentaryDAOImpl getInstance(){
        if (commentaryDAO == null) {
            commentaryDAO = new CommentaryDAOImpl();
        }
        return commentaryDAO;
    }  
    
    @Override
    public Long insert(Commentary commentary) throws PersistenceException {
        if (commentary == null) {
            throw new PersistenceException("Commentary cannot be null");
        }
        Long seqCommentary = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO commentary "
                    + "(COD_questao, COD_discussion, COD_IDUser, TXT_commentary) "
                    + "    VALUES (?, ?, ?, ?) returning SEQ_commentary;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, commentary.getQuestionId());
            pstmt.setLong(2, commentary.getDiscussionId());
            pstmt.setLong(3, commentary.getUserId());
            pstmt.setString(4, commentary.getTextCommentary());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                seqCommentary = rs.getLong("SEQ_discussion");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CommentaryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return seqCommentary;
    }

    @Override
    public boolean update(Commentary commentary) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE commentary "
                    + " SET COD_IDUser = ?,"
                    + "     TXT_commentary = ?,"
                    + " WHERE COD_questao = ? AND COD_discussao = ? AND SEQ_commentary = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, commentary.getUserId());
            pstmt.setString(2, commentary.getTextCommentary());
            pstmt.setLong(3, commentary.getQuestionId());
            pstmt.setLong(4, commentary.getDiscussionId());
            pstmt.setLong(5, commentary.getCommentarySeq());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }        
    }

    @Override
    public boolean remove(Long COD_questao, Long COD_discussao, Long commentarySeq) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM commentary "
                    + "WHERE COD_questao = ? AND COD_discussao = ? AND SEQ_commentary = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, COD_questao);
            pstmt.setLong(2, COD_discussao);
            pstmt.setLong(3, commentarySeq);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public Commentary getCommentaryBySeq(Long COD_questao, Long COD_discussao, Long commentarySeq) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM commentary WHERE COD_questao = ? AND COD_discussao = ? AND SEQ_commentary = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, COD_questao);
            pstmt.setLong(2, COD_discussao);
            pstmt.setLong(3, commentarySeq);
            ResultSet rs = pstmt.executeQuery();

            Commentary commentary = new Commentary();
            if (rs.next()) {
                commentary.setQuestionId(COD_questao);
                commentary.setDiscussionId(COD_discussao);
                commentary.setCommentarySeq(commentarySeq);
                commentary.setUserId(rs.getLong("COD_IDUser"));
                commentary.setTextCommentary(rs.getString("TXT_commentary"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return commentary;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }
    
    @Override
    public List<Commentary> getCommentariesByForumId(Long forumId, Long questionId) throws PersistenceException{
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            List<Commentary> list = new ArrayList<>();
            
            String sql = "SELECT * FROM comentary "
                    + "WHERE COD_discussao = ? AND COD_questao = ?"
                    + "ORDER BY SEQ_commentary";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, forumId);
            pstmt.setLong(2, questionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                do {
                    Commentary commentary = new Commentary();
                    commentary.setQuestionId(questionId);
                    commentary.setDiscussionId(forumId);
                    commentary.setCommentarySeq(rs.getLong("SEQ_commentary"));
                    commentary.setUserId(rs.getLong("COD_userId"));
                    commentary.setTextCommentary(rs.getString("TXT_commentary"));
                    
                    list.add(commentary);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }
 
    public List<Commentary> listAll() throws PersistenceException {
        try {    
            Connection connection = ConnectionManager.getInstance().getConnection();

            ArrayList<Commentary> list = null;
            
            String sql = "SELECT * FROM commentary ORDER BY SEQ_commentary;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Commentary> listAll = null;
            Commentary commentary = null;
            
            if (rs.next()) {
                list = new ArrayList<>();
                do{
                    commentary.setQuestionId(rs.getLong("COD_questao"));
                    commentary.setDiscussionId(rs.getLong("COD_discussao"));
                    commentary.setCommentarySeq(rs.getLong("COD_commentary"));
                    commentary.setUserId(rs.getLong("COD_IDUser"));
                    commentary.setTextCommentary(rs.getString("TXT_commentary"));
                    
                    list.add(commentary);
                } while (rs.next());
            }
            
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }    

}