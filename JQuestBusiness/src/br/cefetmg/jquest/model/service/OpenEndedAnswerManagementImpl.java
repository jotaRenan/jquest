/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.OpenEndedAnswerDAO;
import br.cefetmg.jquest.model.domain.OpenEndedAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class OpenEndedAnswerManagementImpl implements OpenEndedAnswerManagement{
    
    private OpenEndedAnswerDAO DAO;

    /**
     * Empty contructor for a javabeans object.
     */
    public OpenEndedAnswerManagementImpl() { }

    /**
     * Constructor that injects the DAO persistence dependency.
     * @param DAO The Data Acess Object that carry the persistence dependency. 
     */
    public OpenEndedAnswerManagementImpl(OpenEndedAnswerDAO DAO) {
        this.DAO = DAO;
    }
    
    /**
     * Set the value of DAO
     *
     * @param DAO new value of DAO
     */
    public void setDAO(OpenEndedAnswerDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public Long OpenEndedAnswerInsert(OpenEndedAnswer openEndedAnswer) throws BusinessException, PersistenceException {
        if(openEndedAnswer == null){
            throw new BusinessException("The object OpenEndedAnswer cannot be null.");
        }
        if(openEndedAnswer.getquestionID() == null || openEndedAnswer.getuserID() == null || openEndedAnswer.getSeqAnswerUser() == null){
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }
        if(openEndedAnswer.equals(new OpenEndedAnswer())){
            throw new BusinessException("The object OpenEndedAnswer cannot be empty.");
        }
        if(openEndedAnswer.getTxtAnswer().isEmpty()){
            throw new BusinessException("The Answer text cannot be empty.");
        }
        
        DAO.insert(openEndedAnswer);
        return openEndedAnswer.getSeqAnswerUser();
    }

    @Override
    public void OpenEndedAnswerUpdate(OpenEndedAnswer openEndedAnswer) throws BusinessException, PersistenceException {
        if(openEndedAnswer == null){
            throw new BusinessException("The object cannot be null.");
        }
        if(openEndedAnswer.getquestionID() == null || openEndedAnswer.getuserID() == null || openEndedAnswer.getSeqAnswerUser() == null){
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }
        if(openEndedAnswer.equals(new OpenEndedAnswer())){
            throw new BusinessException("The object cannot be empty.");
        }
        if(openEndedAnswer.getTxtAnswer().isEmpty()){
            throw new BusinessException("The Answer text cannot be empty.");
        }
        
        DAO.update(openEndedAnswer);
    }

    @Override
    public void OpenEndedAnswerRemove(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException {
        if(questionID == null || userID == null || seqAnswerUser == null){
            throw new PersistenceException("None of the parameters can be null.");
        } 
        DAO.remove(questionID, userID, seqAnswerUser);
    }

    @Override
    public OpenEndedAnswer getOpenEndedAnswerById(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException {
        if(questionID == null || userID == null || seqAnswerUser == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        return DAO.getOpenEndedAnswerById(questionID, userID, seqAnswerUser);
    }

    @Override
    public List<OpenEndedAnswer> getAll() throws PersistenceException {
        List<OpenEndedAnswer> aux = DAO.listAll();
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        return aux;
    }


}
