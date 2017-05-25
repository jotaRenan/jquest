/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.OpenEndedAnswer;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 * @author Thalesgsn
 */
public interface OpenEndedAnswerDAO {
    
    /**
     * Insert the OpenEndedAnswer object in the persistence.
     * @param openEndedAnswer The object to be stored by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public void insert(OpenEndedAnswer openEndedAnswer) throws PersistenceException;
    
    /**
     * Updates the OpenEndedAnswer object in the persistence.
     * @param openEndedAnswer The object to be updated by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public void update(OpenEndedAnswer openEndedAnswer) throws PersistenceException;
    
    /**
     * Removes the OpenEndedAnswer object in the persistence.
     * @param questionID The id of the question Answered.
     * @param userID The id of the user who Answered the question.
     * @param seqAnswerUser The sequential number of the user answer to this question.
     * @return The object to be removed by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public OpenEndedAnswer remove(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException;
    
    /**
     * Get the OpenEndedAnswer object by id in the persistence.
     * @param questionID The id of the question Answered.
     * @param userID The id of the user who Answered the question.
     * @param seqAnswerUser The sequential number of the user answer to this question.
     * @return The object to be removed by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public OpenEndedAnswer getOpenEndedAnswerById(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException;
    
    /**
     * List all the OpenEndedAnswer objects in the persistence.
     * @return A <b>List</b> of all OpenEndedAnswers Objects.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public List<OpenEndedAnswer> listAll() throws PersistenceException; 
}