/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.OpenEndedAwnserDAO;
import br.cefetmg.jquest.model.domain.OpenEndedAwnser;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class OpenEndedMagnementImpl implements OpenEndedAwnserManagement{
    
    private OpenEndedAwnserDAO DAO;

    /**
     * Empty contructor for a javabeans object.
     */
    public OpenEndedMagnementImpl() { }

    /**
     * Constructor that injects the DAO persistence dependency.
     * @param DAO The Data Acess Object that carry the persistence dependency. 
     */
    public OpenEndedMagnementImpl(OpenEndedAwnserDAO DAO) {
        this.DAO = DAO;
    }
    
    /**
     * Get the value of DAO
     *
     * @return the value of DAO
     */
    public OpenEndedAwnserDAO getDAO() {
        return DAO;
    }

    /**
     * Set the value of DAO
     *
     * @param DAO new value of DAO
     */
    public void setDAO(OpenEndedAwnserDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public void OpenEndedAwnserInsert(OpenEndedAwnser openEndedAwnser) throws BusinessException, PersistenceException {
        if(openEndedAwnser == null){
            throw new BusinessException("The object cannot be null.");
        }
        if(openEndedAwnser.equals(new OpenEndedAwnser())){
            throw new BusinessException("The object cannot be empty.");
        }
        if(openEndedAwnser.getTxtAwnser().equals("")){
            throw new BusinessException("The Awnser text cannot be null.");
        }
        
        DAO.insert(openEndedAwnser);
    }

    @Override
    public void OpenEndedAwnserUpdate(OpenEndedAwnser openEndedAwnser) throws BusinessException, PersistenceException {
         if(openEndedAwnser == null){
            throw new BusinessException("The object cannot be null.");
        }
        if(openEndedAwnser.equals(new OpenEndedAwnser())){
            throw new BusinessException("The object cannot be empty.");
        }
        if(openEndedAwnser.getTxtAwnser().equals("")){
            throw new BusinessException("The Awnser text cannot be null.");
        }
        
        DAO.update(openEndedAwnser);
    }

    @Override
    public void OpenEndedAwnserRemove(long idQuestion, long idUser, long seqAwnserUser) throws PersistenceException {
         DAO.remove(idQuestion, idUser, seqAwnserUser);
    }

    @Override
    public OpenEndedAwnser getOpenEndedAwnserById(long idQuestion, long idUser, long seqAwnserUser) throws PersistenceException {
        return DAO.getOpenEndedAwnserById(idQuestion, idUser, seqAwnserUser);
    }

    @Override
    public List<OpenEndedAwnser> getAll() throws PersistenceException {
        return DAO.listAll();
    }


}
