/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.QuestionDAO;
import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.exception.BusinessException; 
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Gabriel Haddad
 */
public class QuestionManagementImpl implements QuestionManagement {

    private final QuestionDAO questionDAO;

    public QuestionManagementImpl(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }
    
    @Override
    synchronized public Long questionInsert(Question question) throws BusinessException, PersistenceException {
        if (question == null)
            throw new BusinessException("Question cannot be null");
        
        if (question.getHeadline() == null)
            throw new BusinessException("Question's statement cannot be null");
        
        if (question.getDificulty() == null)
            throw new BusinessException("Question's dificulty cannot be null");
        
        if (question.getDomainId()== null)
            throw new BusinessException("Question's domain cannot be null");
        
        if (question.getModuleId()== null)
            throw new BusinessException("Question's module cannot be null");
        
        if (question.getType() == ' ')
            throw new BusinessException("Question's type cannot be empty");
                    
        return questionDAO.insert(question);
    }

    @Override
    public void questionUpdate(Question question) throws BusinessException, PersistenceException {
        if (question == null)
            throw new BusinessException("Domain cannot be null");
        
        if (question.getId() == null) 
            throw new BusinessException("Question's Id cannot be null");

        if (question.getHeadline() == null) 
            throw new BusinessException("Question's statement cannot be null");

        if (question.getDificulty() == null)
            throw new BusinessException("Question's description cannot be null");

        if (question.getDomainId() == null) 
            throw new BusinessException("Question's domain cannot be null");

        if (question.getModuleId() == null) 
            throw new BusinessException("Question's module cannot be null");
        
        if (question.getType() == ' ')
            throw new BusinessException("Question's type cannot be empty");

        questionDAO.update(question);
    }

    @Override
    public void questionRemove(Long questionId) throws PersistenceException {
        if (questionId == null)
            throw new PersistenceException("Question's id cannot be null");
        
       questionDAO.remove(questionId);
    }

    @Override
    public Question getQuestionById(Long questionId) throws PersistenceException {
        if (questionId == null)
            throw new PersistenceException("Question's id cannot be null");
        
        return questionDAO.getQuestionById(questionId); //if the id isn't valid it throws an exception
    }

    @Override
    public List<Question> getAll() throws PersistenceException {
        List<Question> list = questionDAO.listAll();

        if (list.isEmpty()) {
            throw new PersistenceException("No questions found");
        }

        return list;
    }
    
}
