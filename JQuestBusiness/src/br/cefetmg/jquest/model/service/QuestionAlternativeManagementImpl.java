/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.QuestionAlternative;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;
import br.cefetmg.jquest.model.dao.QuestionAlternativeDAO;

/**
 *
 * @author Paula Ribeiro
 */
public class QuestionAlternativeManagementImpl implements QuestionAlternativeManagement {
    
    private final QuestionAlternativeDAO questionAltDAO;

    public QuestionAlternativeManagementImpl(QuestionAlternativeDAO questionAltDAO) {
        this.questionAltDAO = questionAltDAO;
    }
    
    @Override
    public Long insert(QuestionAlternative questionAlt) throws BusinessException, PersistenceException {
        if (questionAlt == null)
            throw new BusinessException("Alternative cannot be null");
        
        if (questionAlt.getAssertionText() == null || questionAlt.getAssertionText().isEmpty())
            throw new BusinessException("Assertion cannot be null");
        
        if(questionAlt.getQuestionId() == null)
            throw new BusinessException("Question Id cannot be null");
        
        questionAltDAO.insert(questionAlt);
        return questionAlt.getOptionSeq();
    }

    @Override
    public void update(QuestionAlternative questionAlt) throws BusinessException, PersistenceException {
        if (questionAlt == null)
            throw new BusinessException("Alternative cannot be null");
        
        if (questionAlt.getAssertionText() == null || questionAlt.getAssertionText().isEmpty())
            throw new BusinessException("Assertion cannot be null");
        
        if (questionAlt.getQuestionId() == null)
            throw new BusinessException("Question Id cannot be null");
        
        if (questionAlt.getOptionSeq() == null)
            throw new BusinessException("Option Seq cannot be null");
        
        questionAltDAO.update(questionAlt);
    }

    @Override
    public QuestionAlternative remove(Long questionAltId) throws PersistenceException {
        if (questionAltId == null)
            throw new PersistenceException("Answer's id cannot be null");
        
        return questionAltDAO.remove(questionAltId);
    }

    @Override
    public QuestionAlternative getQuestionAlternativeById(Long questionAltId) throws PersistenceException {
        if (questionAltId == null)
            throw new PersistenceException("Answer's id cannot be null");
        
        return questionAltDAO.getQuestionAlternativeById(questionAltId); //if the id isn't valid it throws an exception
    }

    @Override
    public List<QuestionAlternative> listAll() throws PersistenceException {
        List<QuestionAlternative> list = questionAltDAO.listAll();        
        return list;
    }
    
}
