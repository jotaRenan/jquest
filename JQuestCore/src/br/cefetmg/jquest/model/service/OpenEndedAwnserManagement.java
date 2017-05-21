/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.OpenEndedAwnser;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface OpenEndedAwnserManagement {
    
    /**
     * Apply the business rules and insert the OpenEndedAwnser object in the 
     * persistence.
     * @param openEndedAwnser The object to be stored by the DAO.
     * @throws br.cefetmg.jquest.model.exception.BusinessException When some 
     * business rule were broken;
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public void OpenEndedAwnserInsert(OpenEndedAwnser openEndedAwnser) throws BusinessException, PersistenceException;
     
    /**
     * Apply the business rules and updates the OpenEndedAwnser object in the 
     * persistence.
     * @param openEndedAwnser The object to be updated by the DAO.
     * @throws br.cefetmg.jquest.model.exception.BusinessException When some
     * business rule were broken.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public void OpenEndedAwnserUpdate(OpenEndedAwnser openEndedAwnser) throws BusinessException, PersistenceException;
    
    /**
     * Removes the OpenEndedAwnser object in the persistence.
     * @param idQuestion The id of the question Answered. The id of the question Answered.
     * @param idUser The id of the user who Answered the question. The id of the user who Answered the question.
     * @param seqAwnserUser The sequential number of the user answer to this question. The sequential number of the user answer to this question.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public void OpenEndedAwnserRemove(long idQuestion, long idUser, long seqAwnserUser) throws PersistenceException;
    
    /**
     * Get the OpenEndedAwnser object by id in the persistence.
     * @param idQuestion The id of the question Answered.
     * @param idUser The id of the user who Answered the question.
     * @param seqAwnserUser The sequential number of the user answer to this question.
     * @return The object to be returned by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public OpenEndedAwnser getOpenEndedAwnserById(long idQuestion, long idUser, long seqAwnserUser) throws PersistenceException;
   
    /**
     * List all the OpenEndedAwnser objects in the persistence.
     * @return A <b>List</b> of all OpenEndedAwnsers Objects.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public List<OpenEndedAwnser> getAll() throws PersistenceException;    
}
