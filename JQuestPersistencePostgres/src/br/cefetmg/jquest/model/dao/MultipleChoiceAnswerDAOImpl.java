/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.MultipleChoiceAnswer;
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
 * @author Breno Mariz
 */
public class MultipleChoiceAnswerDAOImpl implements MultipleChoiceAnswerDAO{
    
    private static MultipleChoiceAnswerDAOImpl MultipleChoiceAnswerDAO = null;
    
    private MultipleChoiceAnswerDAOImpl() {}
    
    public static MultipleChoiceAnswerDAOImpl getInstance() {
        if (MultipleChoiceAnswerDAO == null) {
            MultipleChoiceAnswerDAO = new MultipleChoiceAnswerDAOImpl();
        }
        return MultipleChoiceAnswerDAO;
    }
    
    
    @Override
    public Long insert(MultipleChoiceAnswer multChoiceAnswer) throws PersistenceException {
        if (multChoiceAnswer == null) {
            throw new PersistenceException("multipleChoiceAnswer cannot be null");
        }
        
        if (multChoiceAnswer.getUserId() == null || multChoiceAnswer.getUseSeq() == null || multChoiceAnswer.getQuestionId()== null || multChoiceAnswer.getUserAnswerSeq()== null) {
            throw new PersistenceException("none of parameters can be null");
        }

        Long userAnswerSeq = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO MultipleChoiceAnswer (COD_userIDUseLog, SEQ_use, COD_question, SEQ_option) "
                    + "    VALUES (?, ?, ?, ?) returning SEQ_useAnswer;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswer.getUserId());
            pstmt.setLong(2, multChoiceAnswer.getUseSeq());
            pstmt.setLong(3, multChoiceAnswer.getQuestionId());
            pstmt.setLong(4, multChoiceAnswer.getOptionSeq());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userAnswerSeq = rs.getLong("SEQ_useAnswer");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DomainDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userAnswerSeq;
    }

    @Override
    public boolean update(MultipleChoiceAnswer multChoiceAnswer) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE MultipleChoiceAnswer "
                    + " SET SRQ_option = ?"
                    + " WHERE COD_userIDUseLog ? AND SEQ_USE = ? AND COD_question = ? AND SEQ_useAnswer = ?";


            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswer.getUserId());
            pstmt.setLong(2, multChoiceAnswer.getUseSeq());
            pstmt.setLong(3, multChoiceAnswer.getQuestionId());
            pstmt.setLong(4, multChoiceAnswer.getUserAnswerSeq());
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
    public boolean remove(Long COD_userIDUseLog, Long SEQ_use, Long questionId, Long SEQ_useAnswer) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM MultipleChoiceAnswer WHERE COD_userIDUseLog ? AND SEQ_USE = ? AND COD_question = ? AND SEQ_useAnswer = ?";

            MultipleChoiceAnswer multChoiceAnswer = new MultipleChoiceAnswer();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, COD_userIDUseLog);
            pstmt.setLong(2, SEQ_use);
            pstmt.setLong(3, questionId);
            pstmt.setLong(4, SEQ_useAnswer);
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
    public MultipleChoiceAnswer getAnswerById(Long COD_userIDUseLog, Long SEQ_use, Long questionId, Long SEQ_useAnswer) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM multiplechoiceanswer WHERE COD_userIDUseLog ? AND SEQ_USE = ? AND COD_question = ? AND SEQ_useAnswer = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, COD_userIDUseLog);
            pstmt.setLong(2, SEQ_use);
            pstmt.setLong(3, questionId);
            pstmt.setLong(4, SEQ_useAnswer);
            ResultSet rs = pstmt.executeQuery();

            MultipleChoiceAnswer multChoiceAnswer = new MultipleChoiceAnswer();
            if (rs.next()) {
                multChoiceAnswer.setUserAnswerSeq(rs.getLong("COD_userIDUseLog"));
                multChoiceAnswer.setOptionSeq(rs.getLong("SEQ_option"));
                multChoiceAnswer.setUseSeq(rs.getLong("SEQ_use"));
                multChoiceAnswer.setQuestionId(rs.getLong("COD_question"));
                multChoiceAnswer.setUserAnswerSeq(rs.getLong("SEQ_useAnswer"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return multChoiceAnswer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }
    
    @Override
    public MultipleChoiceAnswer getAnswersByUserAndQuestionId(Long userId, Long questionId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM MultipleChoicewAnswer WHERE COD_userIDUseLog = ?, COD_question = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            pstmt.setLong(2, questionId);
            ResultSet rs = pstmt.executeQuery();

            MultipleChoiceAnswer multChoiceAnswer = new MultipleChoiceAnswer();
            if (rs.next()) {
                multChoiceAnswer.setUseSeq(userId);
                multChoiceAnswer.setQuestionId(questionId);
                multChoiceAnswer.setUseSeq(rs.getLong("seq_use"));
                multChoiceAnswer.setUserAnswerSeq(rs.getLong("seq_useanswer"));
                multChoiceAnswer.setOptionSeq(rs.getLong("seq_option"));

            }

            rs.close();
            pstmt.close();
            connection.close();

            return multChoiceAnswer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public MultipleChoiceAnswer getAllByUserId(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM MultipleChoicewAnswer WHERE COD_userIDUseLog = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();

            MultipleChoiceAnswer multChoiceAnswer = new MultipleChoiceAnswer();
            if (rs.next()) {
                multChoiceAnswer.setUseSeq(userId);
                multChoiceAnswer.setQuestionId(rs.getLong("COD_question"));
                multChoiceAnswer.setUseSeq(rs.getLong("SEQ_use"));
                multChoiceAnswer.setUserAnswerSeq(rs.getLong("SEQ_useanswer"));
                multChoiceAnswer.setOptionSeq(rs.getLong("SEQ_option"));

            }

            rs.close();
            pstmt.close();
            connection.close();

            return multChoiceAnswer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }
    
    public List<MultipleChoiceAnswer> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM multiplechoiceanswer ORDER BY SEQ_option";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<MultipleChoiceAnswer> listAll = null;
            MultipleChoiceAnswer multChoiceAnswer = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    multChoiceAnswer = new MultipleChoiceAnswer();
                    multChoiceAnswer.setUserAnswerSeq(rs.getLong("COD_userIDUseLog"));
                    multChoiceAnswer.setOptionSeq(rs.getLong("SEQ_option"));
                    multChoiceAnswer.setUseSeq(rs.getLong("SEQ_use"));
                    multChoiceAnswer.setQuestionId(rs.getLong("COD_question"));
                    multChoiceAnswer.setUserAnswerSeq(rs.getLong("SEQ_useAnswer"));
                    listAll.add(multChoiceAnswer);
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
    
