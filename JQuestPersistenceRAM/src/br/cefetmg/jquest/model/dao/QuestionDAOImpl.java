/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Question;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class QuestionDAOImpl implements QuestionDAO {

    private static QuestionDAOImpl questionDAO = null;
    private static HashMap<Long, Question> questionDB = new HashMap<Long, Question>();

    private QuestionDAOImpl() {
    }
    
    public static QuestionDAOImpl getInstance() {
        if (questionDAO == null) {
            questionDAO = new QuestionDAOImpl();
        }
        return questionDAO;
    }
    
    @Override
    public void insert(Question question) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Question question) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question remove(Long questionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question getQuestionById(Long questionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Question> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
