/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Forum;
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
public class ForumDAOImpl implements ForumDAO {
    private static ForumDAOImpl forumDAO = null;

    public static ForumDAOImpl getInstance(){
        if (forumDAO == null) {
            forumDAO = new ForumDAOImpl();
        }
        return forumDAO;
    }  
    
    @Override
    public Long insert(Forum forum) throws PersistenceException {
        if (forum == null) {
            throw new PersistenceException("Forum cannot be null");
        }

        Long forumId = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO forum "
                    + "(COD_questao, COD_IDUser, NOM_forum, TXT_description) "
                    + "    VALUES (?, ?, ?, ?) returning SEQ_discussion;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, forum.getQuestionId());
            pstmt.setLong(2, forum.getUserId());
            pstmt.setString(3, forum.getName());
            pstmt.setString(4, forum.getDescription());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                forumId = rs.getLong("SEQ_discussion");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ForumDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return forumId;
    }

    @Override
    public boolean update(Forum forum) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE forum "
                    + " SET COD_questao =?,"
                    + "     COD_IDUser = ?, "
                    + "     NOM_forum = ?,"
                    + "     TXT_description = ?,"
                    + " WHERE SEQ_discussion = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, forum.getQuestionId());
            pstmt.setLong(2, forum.getUserId());
            pstmt.setString(3, forum.getName());
            pstmt.setString(4, forum.getDescription());
            pstmt.setLong(5, forum.getDiscussionSeq());
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
    public boolean remove(Long seqDiscussion, Long codQuestion) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM forum "
                    + "WHERE SEQ_discussion = ? AND COD_questao = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqDiscussion);
            pstmt.setLong(2, codQuestion);
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
    public Forum getForumById(Long seqDiscussion, Long codQuestion) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM forum WHERE SEQ_discussion = ? "
                    + "AND COD_questao = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqDiscussion);
            pstmt.setLong(2, codQuestion);
            ResultSet rs = pstmt.executeQuery();

            Forum forum = new Forum();
            if (rs.next()) {
                forum.setQuestionId(rs.getLong("COD_questao"));
                forum.setDiscussionSeq(seqDiscussion);
                forum.setUserId(rs.getLong("COD_IDUser"));
                forum.setName(rs.getString("NOM_forum"));
                forum.setDescription(rs.getString("TXT_description"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return forum;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Forum> listAllForumsByQuestionID(Long codQuestion) throws PersistenceException {
        try {    
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM forum WHERE COD_questao = ? ORDER BY SEQ_discussion";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, codQuestion);
            
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Forum> listAll = null;
            Forum forum = null;
            
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    forum.setQuestionId(rs.getLong("COD_questao"));
                    forum.setDiscussionSeq(rs.getLong("SEQ_discussion"));
                    forum.setUserId(rs.getLong("COD_IDUser"));
                    forum.setName(rs.getString("NOM_forum"));
                    forum.setDescription(rs.getString("TXT_description"));
                    listAll.add(forum);
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

