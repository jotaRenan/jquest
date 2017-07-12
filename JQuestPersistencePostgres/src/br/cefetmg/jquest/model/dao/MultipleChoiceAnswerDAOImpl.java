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

            String sql = "INSERT INTO MultipleChoiceAnswer (SEQ_use, COD_question, SEQ_useanswer) "
                    + "    VALUES (?, ?, ?) returning SEQ_option;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswer.getUseSeq());
            pstmt.setLong(2, multChoiceAnswer.getQuestionId());
            pstmt.setLong(3, multChoiceAnswer.getUserAnswerSeq());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userAnswerSeq = rs.getLong("SEQ_option");
                multChoiceAnswer.setUseSeq(userAnswerSeq);
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
                    + " SET SEQ_use = ?, "
                    + "     SEQ_useAnswer = ?, "
                    + "     COD_question = ? "
                    + " WHERE SEQ_option = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswer.getUseSeq());
            pstmt.setLong(2, multChoiceAnswer.getUserAnswerSeq());
            pstmt.setLong(3, multChoiceAnswer.getQuestionId());
            pstmt.setLong(4, multChoiceAnswer.getOptionSeq());
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
    public boolean remove(Long multChoiceAnswerId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM MultipleChoiceAnswer WHERE SEQ_useAnswer = ?, SEQ_option = ?, COD_question = ?";

            MultipleChoiceAnswer multChoiceAnswer = new MultipleChoiceAnswer();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswerId);
            pstmt.setLong(2, multChoiceAnswer.getOptionSeq());
            pstmt.setLong(3, multChoiceAnswer.getQuestionId());
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
    public MultipleChoiceAnswer getToFAnswerById(Long multChoiceAnswerId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM multiplechoiceanswer WHERE SEQ_useAnswer = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswerId);
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

    @Override
    public MultipleChoiceAnswer getAnswersByUserAndQuestionId(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM MultipleChoicewAnswer WHERE COD_userIDUseLog = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();

            MultipleChoiceAnswer multChoiceAnswer = new MultipleChoiceAnswer();
            if (rs.next()) {
                multChoiceAnswer.setUseSeq(userId);
                multChoiceAnswer.setUseSeq(rs.getLong("seq_use"));
                multChoiceAnswer.setQuestionId(rs.getLong("cod_question"));
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


}
    
