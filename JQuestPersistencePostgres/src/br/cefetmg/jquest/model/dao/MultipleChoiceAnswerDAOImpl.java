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

        Long userAnswerSeq = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO multiplechoiceanswer (seq_use, cod_question, seq_useanswer, seq_option) "
                    + "    VALUES (?, ?, ?, ?) returning cod_id;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswer.getUseSeq());
            pstmt.setLong(2, multChoiceAnswer.getQuestionId());
            pstmt.setLong(3, multChoiceAnswer.getUserAnswerSeq());
            pstmt.setLong(4, multChoiceAnswer.getOptionSeq());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userAnswerSeq = rs.getLong("cod_id");
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

            String sql = "UPDATE multiplechoiceanswer "
                    + " SET seq_use = ?, "
                    + "     seq_useanswer = ? "
                    + " WHERE cod_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswer.getUseSeq());
            pstmt.setLong(2, multChoiceAnswer.getUserAnswerSeq());
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
    public boolean remove(Long multChoiceAnswerId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM multiplechoiceanswer WHERE cod_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswerId);
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

            String sql = "SELECT * FROM multiplechoiceanswer WHERE cod_id = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, multChoiceAnswerId);
            ResultSet rs = pstmt.executeQuery();

            MultipleChoiceAnswer multChoiceAnswer = new MultipleChoiceAnswer();
            if (rs.next()) {
                multChoiceAnswer.setUserAnswerSeq(multChoiceAnswerId);
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

    @Override
    public List<MultipleChoiceAnswer> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM multiplechoiceanswer ORDER BY cod_id";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<MultipleChoiceAnswer> listAll = null;
            MultipleChoiceAnswer multChoiceAnswer = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    multChoiceAnswer = new MultipleChoiceAnswer();
                    multChoiceAnswer.setUserAnswerSeq(rs.getLong("cod_id"));
                    multChoiceAnswer.setUseSeq(rs.getLong("seq_use"));
                    multChoiceAnswer.setQuestionId(rs.getLong("cod_question"));
                    multChoiceAnswer.setUserAnswerSeq(rs.getLong("seq_useanswer"));
                    multChoiceAnswer.setOptionSeq(rs.getLong("seq_option"));
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
    
