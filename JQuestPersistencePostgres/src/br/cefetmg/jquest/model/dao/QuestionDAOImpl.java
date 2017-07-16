/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Question;
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
 * @author Paula Ribeiro
 */
public class QuestionDAOImpl implements QuestionDAO {
    
    private static QuestionDAOImpl questionDAO = null;
    
    private QuestionDAOImpl() {}
    
    public static QuestionDAOImpl getInstance(){
        if (questionDAO == null) {
            questionDAO = new QuestionDAOImpl();
        }
        return questionDAO;
    }  
    
    @Override
    public Long insert(Question question) throws PersistenceException {
        if (question == null) {
            throw new PersistenceException("Module cannot be null");
        }

        Long codQuestion = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO question (cod_domain, cod_module, cod_userid, txt_statement, idt_dificulty, idt_type, vle_weight) "
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?) returning cod_question";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, question.getDomainId());
            pstmt.setLong(2, question.getModuleId());
            pstmt.setLong(3, question.getUserId());
            pstmt.setString(4, question.getHeadline());
            pstmt.setString(5, String.valueOf(question.getDificulty()));
            pstmt.setString(6, String.valueOf(question.getType()));
            pstmt.setInt(7, question.getWeight());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                codQuestion = rs.getLong("cod_question");
                question.setId(codQuestion);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ModuleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codQuestion;
    }

    @Override
    public boolean update(Question question) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE question "
                    + " SET cod_domain = ?, "
                    + "     cod_module = ?, "
                    + "     cod_userId = ?, "
                    + "     txt_statement = ?, "
                    + "     idt_dificulty = ?, "
                    + "     idt_type = ?, "
                    + "     vle_weight = ? "
                    + " WHERE cod_question = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, question.getDomainId());
            pstmt.setLong(2, question.getModuleId());
            pstmt.setLong(3, question.getUserId());
            pstmt.setString(4, question.getHeadline());
            pstmt.setString(5, String.valueOf(question.getDificulty()));
            pstmt.setString(6, String.valueOf(question.getType()));
            pstmt.setInt(7, question.getWeight());
            pstmt.setLong(8, question.getId());
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
    public boolean remove(Long questionId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM question WHERE cod_question = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, questionId);
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
    public Question getQuestionById(Long questionId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM question WHERE cod_question = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, questionId);
            ResultSet rs = pstmt.executeQuery();

            Question question = null;
            if (rs.next()) {
                question = new Question();
                question.setId(questionId);
                question.setDificulty(rs.getString("idt_dificulty").charAt(0));
                question.setDomainId(rs.getLong("cod_domain"));
                question.setHeadline(rs.getString("txt_statement"));
                question.setModuleId(rs.getLong("cod_module"));
                question.setType(rs.getString("idt_type").charAt(0));
                question.setUserId(rs.getLong("cod_userId"));
                question.setWeight(rs.getInt("vle_weight"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return question;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Question> getQuestionsByModuleId(Long moduleId, Long domainId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM question WHERE cod_domain = ? AND cod_module = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, domainId);
            pstmt.setLong(2, domainId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Question> listAll = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Question question = new Question();
                    question.setId(rs.getLong("cod_question"));
                    question.setDificulty(rs.getString("idt_dificulty").charAt(0));
                    question.setDomainId(domainId);
                    question.setHeadline(rs.getString("txt_statement"));
                    question.setModuleId(moduleId);
                    question.setType(rs.getString("idt_type").charAt(0));
                    question.setUserId(rs.getLong("cod_userId"));
                    question.setWeight(rs.getInt("vle_weight"));
                    listAll.add(question);
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
    public List<Question> getQuestionsByDomainId(Long domainId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM question WHERE cod_domain = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, domainId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Question> listAll = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Question question = new Question();
                    question.setId(rs.getLong("cod_question"));
                    question.setDificulty(rs.getString("idt_dificulty").charAt(0));
                    question.setDomainId(domainId);
                    question.setHeadline(rs.getString("txt_statement"));
                    question.setModuleId(rs.getLong("cod_module"));
                    question.setType(rs.getString("idt_type").charAt(0));
                    question.setUserId(rs.getLong("cod_userId"));
                    question.setWeight(rs.getInt("vle_weight"));
                    listAll.add(question);
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
    public List<Question> getQuestionsByCreatorId(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM question WHERE cod_userId = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Question> listAll = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Question question = new Question();
                    question.setId(rs.getLong("cod_question"));
                    question.setDificulty(rs.getString("idt_dificulty").charAt(0));
                    question.setDomainId(rs.getLong("cod_domain"));
                    question.setHeadline(rs.getString("txt_statement"));
                    question.setModuleId(rs.getLong("cod_module"));
                    question.setType(rs.getString("idt_type").charAt(0));
                    question.setUserId(userId);
                    question.setWeight(rs.getInt("vle_weight"));
                    listAll.add(question);
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
    public List<Question> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM question ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Question> listAll = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Question question = new Question();
                    question.setId(rs.getLong("cod_question"));
                    question.setDificulty(rs.getString("idt_dificulty").charAt(0));
                    question.setDomainId(rs.getLong("cod_domain"));
                    question.setHeadline(rs.getString("txt_statement"));
                    question.setModuleId(rs.getLong("cod_module"));
                    question.setType(rs.getString("idt_type").charAt(0));
                    question.setUserId(rs.getLong("cod_userId"));
                    question.setWeight(rs.getInt("vle_weight"));
                    listAll.add(question);
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
