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
public class OpenEndedManagementImpl implements OpenEndedAnswerManagement{
    
    private OpenEndedAnswerDAO DAO;

    /**
     * Empty contructor for a javabeans object.
     */
    public OpenEndedManagementImpl() { }

    /**
     * Constructor that injects the DAO persistence dependency.
     * @param DAO The Data Acess Object that carry the persistence dependency. 
     */
    public OpenEndedManagementImpl(OpenEndedAnswerDAO DAO) {
        this.DAO = DAO;
    }
    
    /**
     * Get the value of DAO
     *
     * @return the value of DAO
     */
    public OpenEndedAnswerDAO getDAO() {
        return DAO;
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
    public void OpenEndedAnswerInsert(OpenEndedAnswer openEndedAnswer) throws BusinessException, PersistenceException {
        if(openEndedAnswer == null){
            throw new BusinessException("The object cannot be null.");
        }
        if(openEndedAnswer.equals(new OpenEndedAnswer())){
            throw new BusinessException("The object cannot be empty.");
        }
        if(openEndedAnswer.getTxtAnswer().equals("")){
            throw new BusinessException("The Answer text cannot be null.");
        }
        
        DAO.insert(openEndedAnswer);
    }

    @Override
    public void OpenEndedAnswerUpdate(OpenEndedAnswer openEndedAnswer) throws BusinessException, PersistenceException {
         if(openEndedAnswer == null){
            throw new BusinessException("The object cannot be null.");
        }
        if(openEndedAnswer.equals(new OpenEndedAnswer())){
            throw new BusinessException("The object cannot be empty.");
        }
        if(openEndedAnswer.getTxtAnswer().equals("")){
            throw new BusinessException("The Answer text cannot be null.");
        }
        
        DAO.update(openEndedAnswer);
    }

    @Override
    public void OpenEndedAnswerRemove(long idQuestion, long idUser, long seqAnswerUser) throws PersistenceException {
         DAO.remove(idQuestion, idUser, seqAnswerUser);
    }

    @Override
    public OpenEndedAnswer getOpenEndedAnswerById(long idQuestion, long idUser, long seqAnswerUser) throws PersistenceException {
        return DAO.getOpenEndedAnswerById(idQuestion, idUser, seqAnswerUser);
    }

    @Override
    public List<OpenEndedAnswer> getAll() throws PersistenceException {
        return DAO.listAll();
    }


}
