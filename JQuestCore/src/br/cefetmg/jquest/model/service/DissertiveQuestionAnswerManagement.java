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

/**
 *
 * @author Thalesgsn
 */
public interface DissertiveQuestionAnswerManagement {
    
    /**
     * Apply the business rules and insert the DissertiveQuestionAnswer object in the 
 persistence.
     * @param dissertiveQuestionAnswer The object to be stored by the DAO.
     * @return The seq of user answer. 
     * @throws br.cefetmg.jquest.model.exception.BusinessException When some 
     * business rule were broken;
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public Long DissertiveQuestionAnswerInsert(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws BusinessException, PersistenceException;
     
    /**
     * Apply the business rules and updates the DissertiveQuestionAnswer object in the 
 persistence.
     * @param dissertiveQuestionAnswer The object to be updated by the DAO.
     * @return 
     * @throws br.cefetmg.jquest.model.exception.BusinessException When some
     * business rule were broken.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public boolean DissertiveQuestionAnswerUpdate(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws BusinessException, PersistenceException;
    
    /**
     * Removes the DissertiveQuestionAnswer object in the persistence.
     * @param seqAnswerUser The sequential number of the user answer to this question. The sequential number of the user answer to this question.
     * @return 
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public boolean DissertiveQuestionAnswerRemove(Long seqAnswerUser) throws PersistenceException;
    
    /**
     * Get the DissertiveQuestionAnswer object by id in the persistence.
     * @param seqAnswerUser The sequential number of the user answer to this question.
     * @return The object to be returned by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public DissertiveQuestionAnswer getDissertiveQuestionAnswerById(Long seqAnswerUser) throws PersistenceException;
   
    /**
     * List all the DissertiveQuestionAnswer objects in the persistence.
     * @return A <b>List</b> of all DissertiveQuestionAnswers Objects.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public List<DissertiveQuestionAnswer> getAllDissetiveAnswersByUserID(Long userID) throws PersistenceException;    
}
