/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.DomainDAOImpl;
import br.cefetmg.jquest.model.dao.ModuleDAOImpl;
import br.cefetmg.jquest.model.dao.QuestionDAO;
import br.cefetmg.jquest.model.dao.UserDAOImpl;
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
    private final UserManagement userManagement;
    private final DomainManagement domainManagement;
    private final ModuleManagement moduleManagement;

    public QuestionManagementImpl(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
        this.userManagement = new UserManagementImpl(UserDAOImpl.getInstance());
        this.domainManagement = new DomainManagementImpl(DomainDAOImpl.getInstance());
        this.moduleManagement = new ModuleManagementImpl(ModuleDAOImpl.getInstance());
    }
    
    @Override
    synchronized public Long questionInsert(Question question) throws BusinessException, PersistenceException {
        if (question == null)
            throw new BusinessException("Question cannot be null");
        
        if (question.getHeadline() == null || question.getHeadline().isEmpty())
            throw new BusinessException("Question's statement cannot be null");
        
        if (question.getDificulty() == ' ')
            throw new BusinessException("Question's dificulty cannot be null");
        
        if (question.getDomainId() == null)
            throw new BusinessException("Question's domain cannot be null");
        
        if (question.getModuleId() == null || moduleManagement.getModuleById(question.getModuleId(), question.getDomainId()) == null)
            throw new BusinessException("Question's module cannot be null or inexistent");
        
        if (question.getUserId() == null || userManagement.getUserById(question.getUserId()) == null)
            throw new BusinessException("Question's creator cannot be null or inexistent");
        
        if (question.getType() == ' ')
            throw new BusinessException("Question's type cannot be empty");
                    
        questionDAO.insert(question);
        return question.getId();
    }

    @Override
    public boolean questionUpdate(Question question) throws BusinessException, PersistenceException {
        if (question == null)
            throw new BusinessException("Domain cannot be null");
        
        if (question.getId() == null) 
            throw new BusinessException("Question's Id cannot be null");

        if (question.getHeadline() == null || question.getHeadline().isEmpty())
            throw new BusinessException("Question's statement cannot be null");

        if (question.getDificulty()== ' ')
            throw new BusinessException("Question's dificulty cannot be null");

        if (question.getDomainId()== null) 
            throw new BusinessException("Question's domainId cannot be null");

        if (question.getModuleId() == null) 
            throw new BusinessException("Question's moduleId cannot be null");
        
        if (question.getType() == ' ')
            throw new BusinessException("Question's type cannot be empty");

        return questionDAO.update(question);
    }

    @Override
    public boolean questionRemove(Long questionId) throws PersistenceException {
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
    public List<Question> getQuestionByModuleId(Long moduleId, Long domainId) throws PersistenceException {
        if (moduleId == null || moduleManagement.getModuleById(moduleId, domainId) == null) {
            throw new PersistenceException("Module doesn't exist");
        }

        return questionDAO.getQuestionsByModuleId(moduleId, domainId);
    }

    @Override
    public List<Question> getQuestionByDomainId(Long domainId) throws PersistenceException {
        if (domainId == null || domainManagement.getDomainById(domainId) == null) {
            throw new PersistenceException("Domain doesn't exist");
        }

        return questionDAO.getQuestionsByDomainId(domainId); 
    }

    @Override
    public List<Question> getQuestionsByCreatorId(Long userId) throws PersistenceException {
        if (userId == null || userManagement.getUserById(userId) == null) {
            throw new PersistenceException("User doesn't exist");
        }
        
        return questionDAO.getQuestionsByCreatorId(userId);
    }



    
}
