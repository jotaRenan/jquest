/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.QuestionAlternative;
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
public class QuestionAlternativeDAOImpl implements QuestionAlternativeDAO {
    
    private static QuestionAlternativeDAOImpl QuestionAlternativeDAO = null;
    
    private QuestionAlternativeDAOImpl() {}
    
    public static QuestionAlternativeDAOImpl getInstance() {
        if (QuestionAlternativeDAO == null) {
           QuestionAlternativeDAO  = new QuestionAlternativeDAOImpl(); 
        }
        return QuestionAlternativeDAO;
    }

    @Override
    public Long insert(QuestionAlternative closedEndedAlt) throws PersistenceException {
        if (closedEndedAlt == null) {
            throw new PersistenceException("ClosedEndedAlternative cannot be null");
        }
        
        if (closedEndedAlt.getOptionSeq() == null || closedEndedAlt.getQuestionId() == null || closedEndedAlt.getAssertionText() == null) {
            throw new PersistenceException("none of parameters can be null");
        }

        Long optionSeq = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO QuestionAlternative (COD_question, TXT_assertative, IDT_isCorrect) "
                    + "    VALUES (?, ?, ?) returning SEQ_option;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedEndedAlt.getQuestionId());
            pstmt.setString(2, closedEndedAlt.getAssertionText());
            pstmt.setBoolean(3, closedEndedAlt.isIsCorrect());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                optionSeq = rs.getLong("SEQ_option");
                closedEndedAlt.setOptionSeq(optionSeq);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DomainDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return optionSeq;
    }

    @Override
    public boolean update(QuestionAlternative closedEndedAlt) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE QuestionAlternative "
                    + " SET TXT_assertative = ?, "
                    + "     IDT_isCorrect = ? "
                    + " WHERE SEQ_option = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, closedEndedAlt.getAssertionText());
            pstmt.setBoolean(2, closedEndedAlt.isIsCorrect());
            pstmt.setLong(3, closedEndedAlt.getOptionSeq());
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
    public boolean remove(Long closedEndedAltId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM QuestionAlternative WHERE SEQ_option = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedEndedAltId);
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
    public QuestionAlternative getQuestionAlternativeById(Long closedEndedAltId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM QuestionAlternative WHERE SEQ_question = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, closedEndedAltId);
            ResultSet rs = pstmt.executeQuery();

            
            QuestionAlternative questAlternative = new QuestionAlternative();
            if (rs.next()) {
                questAlternative.setOptionSeq(rs.getLong("SEQ_option"));
                questAlternative.setQuestionId(rs.getLong("COD_question"));
                questAlternative.setAssertionText(rs.getString("TXT_assertative"));
                questAlternative.setIsCorrect(rs.getBoolean("IDT_isCorrect"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return questAlternative;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public QuestionAlternative getAlternativeByQuestionId(Long questionId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM QuestionAlternative WHERE COD_question = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, questionId);
            ResultSet rs = pstmt.executeQuery();

            QuestionAlternative questAlternative = new QuestionAlternative();
            if (rs.next()) {
                questAlternative.setOptionSeq(rs.getLong("SEQ_option"));
                questAlternative.setQuestionId(rs.getLong("COD_question"));
                questAlternative.setAssertionText(rs.getString("TXT_assertative"));
                questAlternative.setIsCorrect(rs.getBoolean("IDT)_isCorrect"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return questAlternative;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }
    
    @Override
    public List<QuestionAlternative> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM multiplechoiceanswer ORDER BY SEQ_question";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<QuestionAlternative> listAll = null;
            QuestionAlternative questAlternative = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    questAlternative = new QuestionAlternative();
                    questAlternative.setOptionSeq(rs.getLong("SEQ_option"));
                    questAlternative.setQuestionId(rs.getLong("COD_question"));
                    questAlternative.setAssertionText(rs.getString("TXT_assertative"));
                    questAlternative.setIsCorrect(rs.getBoolean("IDT_isCorrect"));
                    
                    listAll.add(questAlternative);
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
