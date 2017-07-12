/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.DissertiveQuestionAnswer;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 * @author Thalesgsn
 */
public interface DissertiveQuestionAnswerDAO {
    
    /**
     * Insert the DissertiveQuestionAnswer object in the persistence.
     * @param openEndedAnswer The object to be stored by the DAO.
     * @return 
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public Long insert(DissertiveQuestionAnswer openEndedAnswer) throws PersistenceException;
    
    /**
     * Updates the DissertiveQuestionAnswer object in the persistence.
     * @param openEndedAnswer The object to be updated by the DAO.
     * @return 
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public boolean update(DissertiveQuestionAnswer openEndedAnswer) throws PersistenceException;
    
    /**
     * Removes the DissertiveQuestionAnswer object in the persistence.
     * @param seqAnswerUser The sequential number of the user answer to this question.
     * @return The object to be removed by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public boolean remove(Long seqAnswerUser) throws PersistenceException;
    
    /**
     * Get the DissertiveQuestionAnswer object by id in the persistence.
     * @param seqAnswerUser The sequential number of the user answer to this question.
     * @return The object to be removed by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public DissertiveQuestionAnswer getDissertiveQuestionAnswerById(Long seqAnswerUser) throws PersistenceException;
    
    /**
     * List all the DissertiveQuestionAnswer objects in the persistence.
     * @param userID
     * @return A <b>List</b> of all OpenEndedAnswers Objects.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public List<DissertiveQuestionAnswer> listAllDissetiveAnswersByUserID(Long userID) throws PersistenceException; 
}
