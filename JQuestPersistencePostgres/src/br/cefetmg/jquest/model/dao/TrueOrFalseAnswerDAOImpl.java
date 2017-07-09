/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.TrueOrFalseAnswer;
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
public class TrueOrFalseAnswerDAOImpl implements TrueOrFalseAnswerDAO{
    
    private static TrueOrFalseAnswerDAOImpl TrueOrFalseAnswerDAO = null;
    
    private TrueOrFalseAnswerDAOImpl() {}
    
    public static TrueOrFalseAnswerDAOImpl getInstance() {
        if (TrueOrFalseAnswerDAO == null) {
            TrueOrFalseAnswerDAO = new TrueOrFalseAnswerDAOImpl();
        }
        return TrueOrFalseAnswerDAO;
    }
    
    @Override
    public Long insert(TrueOrFalseAnswer tofAnswer) throws PersistenceException {
        if (tofAnswer == null) {
            throw new PersistenceException("TrueOrFalseAnswer cannot be null");
        }

        Long userAnswerSeq = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO trueorfalsequestionanswer (seq_use, cod_question, seq_useanswer, seq_option) "
                    + "    VALUES (?, ?, ?, ?) returning cod_id;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, tofAnswer.getUseSeq());
            pstmt.setLong(2, tofAnswer.getQuestionId());
            pstmt.setLong(3, tofAnswer.getUserAnswer());
            pstmt.setLong(4, tofAnswer.getOptionSeq());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userAnswerSeq = rs.getLong("cod_id");
                tofAnswer.setUseSeq(userAnswerSeq);
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
    public boolean update(TrueOrFalseAnswer tofAnswer) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE trueorfalsequestionanswer "
                    + " SET seq_use = ?, "
                    + "     seq_useanswer = ? "
                    + " WHERE cod_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, tofAnswer.getUseSeq());
            pstmt.setLong(2, tofAnswer.getUserAnswer());
            pstmt.setLong(3, tofAnswer.getQuestionId());
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
    public boolean remove(Long tofAnswerId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM trueorfalsequestionanswer WHERE cod_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, tofAnswerId);
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
    public TrueOrFalseAnswer getToFAnswerById(Long tofAnswerId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM trueorfalsequestionanswer WHERE cod_id = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, tofAnswerId);
            ResultSet rs = pstmt.executeQuery();

            TrueOrFalseAnswer tofAnswer = new TrueOrFalseAnswer();
            if (rs.next()) {
                tofAnswer.setUserAnswer(tofAnswerId);
                tofAnswer.setUseSeq(rs.getLong("seq_use"));
                tofAnswer.setQuestionId(rs.getLong("cod_question"));
                tofAnswer.setUserAnswer(rs.getLong("seq_useanswer"));
                tofAnswer.setOptionSeq(rs.getLong("seq_option"));

            }

            rs.close();
            pstmt.close();
            connection.close();

            return tofAnswer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<TrueOrFalseAnswer> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM trueorfalseanswer ORDER BY cod_id";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<TrueOrFalseAnswer> listAll = null;
            TrueOrFalseAnswer tofAnswer = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    tofAnswer = new TrueOrFalseAnswer();
                    tofAnswer.setUserAnswer(rs.getLong("cod_id"));
                    tofAnswer.setUseSeq(rs.getLong("seq_use"));
                    tofAnswer.setQuestionId(rs.getLong("cod_question"));
                    tofAnswer.setUserAnswer(rs.getLong("seq_useanswer"));
                    tofAnswer.setOptionSeq(rs.getLong("seq_option"));
                    listAll.add(tofAnswer);
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
