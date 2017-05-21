/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.OpenEndedAwnser;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 * @author Thalesgsn
 */
public interface OpenEndedAwnserDAO {
    
    /**
     * Insert the OpenEndedAwnser object in the persistence.
     * @param openEndedAwnser The object to be stored by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public void insert(OpenEndedAwnser openEndedAwnser) throws PersistenceException;
    
    /**
     * Updates the OpenEndedAwnser object in the persistence.
     * @param openEndedAwnser The object to be updated by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public void update(OpenEndedAwnser openEndedAwnser) throws PersistenceException;
    
    /**
     * Removes the OpenEndedAwnser object in the persistence.
     * @param idQuestion The id of the question Answered.
     * @param idUser The id of the user who Answered the question.
     * @param seqAwnserUser The sequential number of the user answer to this question.
     * @return The object to be removed by the DAO.
     * @throws PersistenceException When occours some unexpected error in the
     * presistence.
     */
    public OpenEndedAwnser remove(long idQuestion, long idUser, long seqAwnserUser) throws PersistenceException;
    
    /**
     * Get the OpenEndedAwnser object by id in the persistence.
     * @param idQuestion The id of the question Answered.
     * @param idUser The id of the user who Answered the question.
     * @param seqAwnserUser The sequential number of the user answer to this question.
     * @return The object to be removed by the DAO.
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
    public List<OpenEndedAwnser> listAll() throws PersistenceException; 
}
