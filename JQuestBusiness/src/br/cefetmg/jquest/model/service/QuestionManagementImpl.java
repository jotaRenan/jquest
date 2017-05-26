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
 * @author Aluno
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
        
        if (question.getHeadline() == null || question.getHeadline().isEmpty())
            throw new BusinessException("Question's statement cannot be null");
        
        if (question.getDificulty()== null)
            throw new BusinessException("Question's dificulty cannot be null");
        
        if (question.getDomainId() == null)
            throw new BusinessException("Question's domain cannot be null");
        
        if (question.getModuleId() == null)
            throw new BusinessException("Question's module cannot be null");
        
        if (question.getType() == ' ')
            throw new BusinessException("Question's type cannot be empty");
                    
        questionDAO.insert(question);
        return question.getId();
    }

    @Override
    public void questionUpdate(Question question) throws BusinessException, PersistenceException {
        if (question == null)
            throw new BusinessException("Domain cannot be null");
        
        if (question.getId() == null) 
            throw new BusinessException("Question's Id cannot be null");

        if (question.getHeadline() == null || question.getHeadline().isEmpty())
            throw new BusinessException("Question's statement cannot be null");

        if (question.getDificulty()== null)
            throw new BusinessException("Question's dificulty cannot be null");

        if (question.getDomainId()== null) 
            throw new BusinessException("Question's domainId cannot be null");

        if (question.getModuleId() == null) 
            throw new BusinessException("Question's moduleId cannot be null");
        
        if (question.getType() == ' ')
            throw new BusinessException("Question's type cannot be empty");

        questionDAO.update(question);
    }

    @Override
    public Question questionRemove(Long questionId) throws PersistenceException {
        if (questionId == null)
            throw new PersistenceException("Question's id cannot be null");
        
       return questionDAO.remove(questionId);
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
        return list;
    }

    @Override
    public List<Question> getQuestionByModuleId(Long moduleId) throws PersistenceException {
        if (moduleId == null) {
            throw new PersistenceException("Module's id cannot be null");
        }

        return questionDAO.getQuestionsByModuleId(moduleId);
    }

    @Override
    public List<Question> getQuestionByDomainId(Long domainId) throws PersistenceException {
        if (domainId == null) {
            throw new PersistenceException("Domain's id cannot be null");
        }

        return questionDAO.getQuestionsByDomainId(domainId); 
    }



    
}
