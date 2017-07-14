/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Vote;
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
public class VoteDAOImpl implements VoteDAO {
    private static VoteDAOImpl voteDAO = null;

    public static VoteDAOImpl getInstance(){
        if (voteDAO == null) {
            voteDAO = new VoteDAOImpl();
        }
        return voteDAO;
    }  
    
    @Override
    public Long insert(Vote vote) throws PersistenceException {
        if (vote == null) {
            throw new PersistenceException("Vote cannot be null");
        }

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO vote "
                    + "(COD_question, SEQ_discussion, SEQ_commentary, COD_ID, IDT_like) "
                    + "    VALUES (?, ?, ?, ? ,?);";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, vote.getQuestionId());
            pstmt.setLong(2, vote.getDiscussionSeq());
            pstmt.setLong(3, vote.getCommentarySeq());
            pstmt.setLong(4, vote.getUserId());
            pstmt.setBoolean(5, vote.isIsLiked());
            ResultSet rs = pstmt.executeQuery();

            

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VoteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vote.getCommentarySeq();
    }

    @Override
    public boolean update(Vote vote) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE vote "
                    + " SET COD_question = ?,"
                    + "     SEQ_discussion = ?, "
                    + "     IDT_like = ?,"
                    + " WHERE SEQ_commentary = ?"
                    + "AND COD_ID = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, vote.getQuestionId());
            pstmt.setLong(2, vote.getDiscussionSeq());
            pstmt.setBoolean(3, vote.isIsLiked());
            pstmt.setLong(4, vote.getCommentarySeq());
            pstmt.setLong(5, vote.getUserId());
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
    public boolean remove(Long COD_question, Long SEQ_discussion, Long seqCommentary, Long userID) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM vote "
                    + "WHERE COD_question = ? AND SEQ_discussion = ? AND SEQ_commentary = ? AND COD_ID = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, COD_question);
            pstmt.setLong(2, SEQ_discussion);
            pstmt.setLong(3, seqCommentary);
            pstmt.setLong(4, userID);
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
    public Vote getVoteById(Long COD_question, Long SEQ_discussion, Long seqCommentary, Long userID) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM vote "
                    + "WHERE COD_question = ? AND SEQ_discussion = ? AND SEQ_commentary = ? AND COD_ID = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, COD_question);
            pstmt.setLong(2, SEQ_discussion);
            pstmt.setLong(3, seqCommentary);
            pstmt.setLong(4, userID);
            ResultSet rs = pstmt.executeQuery();

            Vote vote = new Vote();
            if (rs.next()) {
                vote.setQuestionId(rs.getLong("COD_question"));
                vote.setDiscussionSeq(rs.getLong("SEQ_discussion"));
                vote.setCommentarySeq(seqCommentary);
                vote.setUserId(userID);
                vote.setIsLiked(rs.getBoolean("IDT_like"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return vote;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Vote> listAllVotesByCommentaryID(Long seqCommentary) throws PersistenceException {
        try {    
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM vote WHERE SEQ_commentary = ? ORDER BY SEQ_discussion";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, seqCommentary);
            
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Vote> listAll = null;
            Vote vote = null;
            
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    vote.setQuestionId(rs.getLong("COD_question"));
                    vote.setDiscussionSeq(rs.getLong("SEQ_discussion"));
                    vote.setCommentarySeq(rs.getLong("SEQ_commentary"));
                    vote.setUserId(rs.getLong("COD_ID"));
                    vote.setIsLiked(rs.getBoolean("IDT_like"));
                    listAll.add(vote);
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
