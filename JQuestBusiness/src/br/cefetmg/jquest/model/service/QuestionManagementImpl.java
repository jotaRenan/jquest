/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class QuestionManagementImpl implements QuestionManagement {

    @Override
    public Question questionInsert(Question question) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void questionUpdate(Question question) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void questionRemove(Long questionId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question getQuestionById(Long questionId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Question> getAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
