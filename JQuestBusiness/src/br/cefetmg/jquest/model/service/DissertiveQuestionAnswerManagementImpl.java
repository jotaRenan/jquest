/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.DissertiveQuestionAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;
import br.cefetmg.jquest.model.dao.DissertiveQuestionAnswerDAO;

/**
 *
 * @author Thalesgsn
 */
public class DissertiveQuestionAnswerManagementImpl implements DissertiveQuestionAnswerManagement{
    
    private DissertiveQuestionAnswerDAO DAO;

    /**
     * Empty contructor for a javabeans object.
     */
    public DissertiveQuestionAnswerManagementImpl() { }

    /**
     * Constructor that injects the DAO persistence dependency.
     * @param DAO The Data Acess Object that carry the persistence dependency. 
     */
    public DissertiveQuestionAnswerManagementImpl(DissertiveQuestionAnswerDAO DAO) {
        this.DAO = DAO;
    }
    
    /**
     * Set the value of DAO
     *
     * @param DAO new value of DAO
     */
    public void setDAO(DissertiveQuestionAnswerDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public Long DissertiveQuestionAnswerInsert(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws BusinessException, PersistenceException {
        if(dissertiveQuestionAnswer == null){
            throw new BusinessException("The object DissertiveQuestionAnswer cannot be null.");
        }
        if(dissertiveQuestionAnswer.getQuestionID() == null || dissertiveQuestionAnswer.getUserID() == null || dissertiveQuestionAnswer.getSeqAnswerUser() == null){
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }
        if(dissertiveQuestionAnswer.getTxtAnswer() == null || dissertiveQuestionAnswer.getTxtAnswer().isEmpty()){
            throw new BusinessException("The Answer text cannot be null or empty.");
        }
        
        DAO.insert(dissertiveQuestionAnswer);
        return dissertiveQuestionAnswer.getSeqAnswerUser();
    }

    @Override
    public void DissertiveQuestionAnswerUpdate(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws BusinessException, PersistenceException {
        if(dissertiveQuestionAnswer == null)
            throw new BusinessException("The object cannot be null.");
        
        if(dissertiveQuestionAnswer.getQuestionID() == null || dissertiveQuestionAnswer.getUserID() == null || dissertiveQuestionAnswer.getSeqAnswerUser() == null)
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        
        if(dissertiveQuestionAnswer.getTxtAnswer() == null || dissertiveQuestionAnswer.getTxtAnswer().isEmpty()){
            throw new BusinessException("The Answer text cannot be null or empty.");
        }
        
        DAO.update(dissertiveQuestionAnswer);
    }

    @Override
    public void DissertiveQuestionAnswerRemove(Long seqAnswerUser) throws PersistenceException {
        if(seqAnswerUser == null)
            throw new PersistenceException("The seqAwnserUser can't be null.");
         
        DAO.remove(seqAnswerUser);
    }

    @Override
    public DissertiveQuestionAnswer getDissertiveQuestionAnswerById(Long seqAnswerUser) throws PersistenceException {
        if(seqAnswerUser == null)
            throw new PersistenceException("The seqAwnserUser can't be null.");

        return DAO.getDissertiveQuestionAnswerById(seqAnswerUser);
    }

    @Override
    public List<DissertiveQuestionAnswer> getAll() throws PersistenceException {
        List<DissertiveQuestionAnswer> aux = DAO.listAll();
        return aux;
    }


}
