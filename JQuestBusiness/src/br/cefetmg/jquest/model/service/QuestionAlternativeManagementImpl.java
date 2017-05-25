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
    
    private final QuestionAlternativeDAO closedEndedAltDAO;

    public QuestionAlternativeManagementImpl(QuestionAlternativeDAO closedEndedAltDAO) {
        this.closedEndedAltDAO = closedEndedAltDAO;
    }
    
    @Override
    public Long insert(QuestionAlternative closedEndedAlt) throws BusinessException, PersistenceException {
        if (closedEndedAlt == null)
            throw new BusinessException("Alternative cannot be null");
        
        if (closedEndedAlt.getAssertionText() == null || closedEndedAlt.getAssertionText().isEmpty())
            throw new BusinessException("Assertion cannot be null");
        
        if(closedEndedAlt.getQuestionId() == null)
            throw new BusinessException("Question Id cannot be null");
        
        closedEndedAltDAO.insert(closedEndedAlt);
        return closedEndedAlt.getOptionSeq();
    }

    @Override
    public void update(QuestionAlternative closedEndedAlt) throws BusinessException, PersistenceException {
        if (closedEndedAlt == null)
            throw new BusinessException("Alternative cannot be null");
        
        if (closedEndedAlt.getAssertionText() == null || closedEndedAlt.getAssertionText().isEmpty())
            throw new BusinessException("Assertion cannot be null");
        
        if (closedEndedAlt.getQuestionId() == null)
            throw new BusinessException("Question Id cannot be null");
        
        if (closedEndedAlt.getOptionSeq() == null)
            throw new BusinessException("Option Seq cannot be null");
        
        closedEndedAltDAO.update(closedEndedAlt);
    }

    @Override
    public QuestionAlternative remove(Long closedEndedAltId) throws PersistenceException {
        if (closedEndedAltId == null)
            throw new PersistenceException("Answer's id cannot be null");
        
        return closedEndedAltDAO.remove(closedEndedAltId);
    }

    @Override
    public QuestionAlternative getQuestionAlternativeById(Long closedEndedAltId) throws PersistenceException {
        if (closedEndedAltId == null)
            throw new PersistenceException("Answer's id cannot be null");
        
        return closedEndedAltDAO.getQuestionAlternativeById(closedEndedAltId); //if the id isn't valid it throws an exception
    }

    @Override
    public List<QuestionAlternative> listAll() throws PersistenceException {
        List<QuestionAlternative> list = closedEndedAltDAO.listAll();        
        return list;
    }
    
}
