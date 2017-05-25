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
    public Long openEndedAnswerInsert(DissertiveQuestionAnswer openEndedAnswer) throws BusinessException, PersistenceException {
        if(openEndedAnswer == null){
            throw new BusinessException("The object OpenEndedAnswer cannot be null.");
        }
        if(openEndedAnswer.getQuestionID() == null || openEndedAnswer.getUserID() == null || openEndedAnswer.getSeqAnswerUser() == null){
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }
        if(openEndedAnswer.equals(new DissertiveQuestionAnswer())){
            throw new BusinessException("The object OpenEndedAnswer cannot be empty.");
        }
        if(openEndedAnswer.getTxtAnswer() == null){
            throw new BusinessException("The Answer text cannot be null.");
        }
        
        DAO.insert(openEndedAnswer);
        return openEndedAnswer.getSeqAnswerUser();
    }

    @Override
    public void openEndedAnswerUpdate(DissertiveQuestionAnswer openEndedAnswer) throws BusinessException, PersistenceException {
        if(openEndedAnswer == null)
            throw new BusinessException("The object cannot be null.");
        
        if(openEndedAnswer.getQuestionID() == null || openEndedAnswer.getUserID() == null || openEndedAnswer.getSeqAnswerUser() == null)
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        
        if(openEndedAnswer.equals(new DissertiveQuestionAnswer())){
            throw new BusinessException("The object cannot be empty.");
        }
        if(openEndedAnswer.getTxtAnswer() == null){
            throw new BusinessException("The Answer text cannot be null.");
        }
        
        DAO.update(openEndedAnswer);
    }

    @Override
    public void openEndedAnswerRemove(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException {
        if(questionID == null || userID == null || seqAnswerUser == null)
            throw new PersistenceException("None of the parameters can be null.");
         
        DAO.remove(questionID, userID, seqAnswerUser);
    }

    @Override
    public DissertiveQuestionAnswer getOpenEndedAnswerById(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException {
        if(questionID == null || userID == null || seqAnswerUser == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        return DAO.getOpenEndedAnswerById(questionID, userID, seqAnswerUser);
    }

    @Override
    public List<DissertiveQuestionAnswer> getAll() throws PersistenceException {
        List<DissertiveQuestionAnswer> aux = DAO.listAll();
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        return aux;
    }


}
