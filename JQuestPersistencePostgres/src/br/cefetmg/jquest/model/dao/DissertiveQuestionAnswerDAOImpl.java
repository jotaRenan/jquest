/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.DissertiveQuestionAnswer;
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
public class DissertiveQuestionAnswerDAOImpl implements DissertiveQuestionAnswerDAO {
    private static DissertiveQuestionAnswerDAOImpl dissertiveQuestionAnswerDAO = null;

    public static DissertiveQuestionAnswerDAOImpl getInstance(){
        if (dissertiveQuestionAnswerDAO == null) {
            dissertiveQuestionAnswerDAO = new DissertiveQuestionAnswerDAOImpl();
        }
        return dissertiveQuestionAnswerDAO;
    }  
    
    @Override
    public Long insert(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws PersistenceException {
        if (dissertiveQuestionAnswer == null) {
            throw new PersistenceException("dissertiveQuestionAnswer cannot be null");
        }

        Long dissertiveQuestionAnswerId = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql= "INSERT INTO UseLog (COD_ID, DAT_use)"
                    + "VALUES (?, current_date) RETURNING SEQ_use;";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, dissertiveQuestionAnswer.getUserID());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                dissertiveQuestionAnswerId = rs.getLong("SEQ_use");
                dissertiveQuestionAnswer.setSeqAnswerUser(dissertiveQuestionAnswerId);
            }

            sql = "INSERT INTO DissertiveQuestionAnswer "
                    + "(COD_ID, COD_IDQuestion, SEQ_use, NOM_DissertiveQuestionAnswer, TXT_description) "
                    + "    VALUES (?, ?, ?, ?, ?) returning SEQ_discussion;";

            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, dissertiveQuestionAnswer.getUserID());
            pstmt.setLong(2, dissertiveQuestionAnswer.getQuestionID());
            pstmt.setLong(3, dissertiveQuestionAnswer.getSeqAnswerUser());
            pstmt.setString(4, dissertiveQuestionAnswer.getTxtAnswer());
            pstmt.setDouble(5, dissertiveQuestionAnswer.getValueScore());
            pstmt.executeQuery();

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DissertiveQuestionAnswerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dissertiveQuestionAnswerId;
    }

    @Override
    public boolean update(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE DissertiveQuestionAnswer "
                    + " SET COD_ID = ?, "
                    + "     COD_question = ? "
                    + "     TXT_answer = ?"
                    + "     VLE_grade = ?"
                    + "WHERE SEQ_discussion = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, dissertiveQuestionAnswer.getUserID());
            pstmt.setLong(2, dissertiveQuestionAnswer.getQuestionID());
            pstmt.setString(3, dissertiveQuestionAnswer.getTxtAnswer());
            pstmt.setDouble(4, dissertiveQuestionAnswer.getValueScore());
            pstmt.setLong(5, dissertiveQuestionAnswer.getSeqAnswerUser());
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
    public boolean remove(Long seqUse) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE A, B FROM DissertiveQuestionAnswer A"
                    + " JOIN UseLog ON A.SEQ_use = B.SEQ_use "
                    + "WHERE SEQ_use = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqUse);
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
    public DissertiveQuestionAnswer getDissertiveQuestionAnswerById(Long seqUse) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM DissertiveQuestionAnswer WHERE SEQ_discussion = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqUse);
            ResultSet rs = pstmt.executeQuery();

            DissertiveQuestionAnswer dissertiveQuestionAnswer = new DissertiveQuestionAnswer();
            if (rs.next()) {
                dissertiveQuestionAnswer.setUserID(rs.getLong("COD_ID"));
                dissertiveQuestionAnswer.setQuestionID(rs.getLong("COD_question"));
                dissertiveQuestionAnswer.setSeqAnswerUser(seqUse);
                dissertiveQuestionAnswer.setTxtAnswer(rs.getString("TXT_answer"));
                dissertiveQuestionAnswer.setValueScore(rs.getDouble("VLE_grade"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return dissertiveQuestionAnswer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<DissertiveQuestionAnswer> listAllDissetiveAnswersByUserID(Long userID) throws PersistenceException {
        try {    
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM DissertiveQuestionAnswer WHERE COD_userIDUseLog = ? ORDER BY SEQ_use";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, userID);
            
            ResultSet rs = pstmt.executeQuery();

            ArrayList<DissertiveQuestionAnswer> listAll = null;
            DissertiveQuestionAnswer dissertiveQuestionAnswer = null;
            
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    dissertiveQuestionAnswer.setUserID(rs.getLong("COD_ID"));
                    dissertiveQuestionAnswer.setQuestionID(rs.getLong("COD_question"));
                    dissertiveQuestionAnswer.setSeqAnswerUser(rs.getLong("SEQ_use"));
                    dissertiveQuestionAnswer.setTxtAnswer(rs.getString("TXT_answer"));
                    dissertiveQuestionAnswer.setValueScore(rs.getDouble("VLE_grade"));
                    listAll.add(dissertiveQuestionAnswer);
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
