/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.ClosedEndedAlternativeDAO;
import br.cefetmg.jquest.model.domain.ClosedEndedAlternative;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Paula Ribeiro
 */
public class ClosedEndedAlternativeManagementImpl implements ClosedEndedAlternativeManagement {
    
    private final ClosedEndedAlternativeDAO closedEndedAltDAO;

    public ClosedEndedAlternativeManagementImpl(ClosedEndedAlternativeDAO closedEndedAltDAO) {
        this.closedEndedAltDAO = closedEndedAltDAO;
    }
    
    @Override
    public Long insert(ClosedEndedAlternative closedEndedAlt) throws BusinessException, PersistenceException {
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
    public void update(ClosedEndedAlternative closedEndedAlt) throws BusinessException, PersistenceException {
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
    public ClosedEndedAlternative remove(Long closedEndedAltId) throws PersistenceException {
        if (closedEndedAltId == null)
            throw new PersistenceException("Answer's id cannot be null");
        
        return closedEndedAltDAO.remove(closedEndedAltId);
    }

    @Override
    public ClosedEndedAlternative getClosedEndedAlternativeById(Long closedEndedAltId) throws PersistenceException {
        if (closedEndedAltId == null)
            throw new PersistenceException("Answer's id cannot be null");
        
        return closedEndedAltDAO.getClosedEndedAlternativeById(closedEndedAltId); //if the id isn't valid it throws an exception
    }

    @Override
    public List<ClosedEndedAlternative> listAll() throws PersistenceException {
        List<ClosedEndedAlternative> list = closedEndedAltDAO.listAll();        
        return list;
    }
    
}
