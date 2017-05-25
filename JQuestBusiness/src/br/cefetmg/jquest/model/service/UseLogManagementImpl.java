/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.UseLogDAO;
import br.cefetmg.jquest.model.domain.UseLog;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author GABRIEL HADDAD
 */
public class UseLogManagementImpl implements UseLogManagement{
    private final UseLogDAO useLogDAO;

    public UseLogManagementImpl(UseLogDAO useLogDAO) {
        this.useLogDAO = useLogDAO;
    }

    @Override
    public Long useLogInsert(UseLog useLog) throws BusinessException, PersistenceException {
        if (useLog == null)
            throw new BusinessException("UseLog cannot be null");
        
        if (useLog.getUseSeq()== null)
            throw new BusinessException("UseLog's sequence cannot be null");
            
        if (useLog.getIdUser()== null)
            throw new BusinessException("UseLog's userID cannot be null");
        
        if (useLog.getUseDate()== null)
            throw new BusinessException("UseLog's use date cannot be null");
      
        useLogDAO.insert(useLog);
        return useLog.getUseSeq();
    }

    @Override
    public void useLogUpdate(UseLog useLog) throws BusinessException, PersistenceException {
        if (useLog == null)
            throw new BusinessException("UseLog cannot be null");
        
        if (useLog.getUseSeq()== null)
            throw new BusinessException("UseLog's sequence cannot be null");
            
        if (useLog.getIdUser()== null)
            throw new BusinessException("UseLog's userID cannot be null");
        
        if (useLog.getUseDate()== null)
            throw new BusinessException("UseLog's use date cannot be null");
        
        useLogDAO.update(useLog);
    }

    @Override
    public UseLog useLogRemove(Long useLogSeq) throws PersistenceException {
        if (useLogSeq == null) {
            throw new PersistenceException("UseLog's sequence cannot be null");
        }
        return useLogDAO.remove(useLogSeq);
    }

    @Override
    public UseLog getUseLogBySeq(Long useLogSeq) throws PersistenceException {
        if (useLogSeq == null)
            throw new PersistenceException("UseLogs's sequence cannot be null");
        
        return useLogDAO.getUseLogBySeq(useLogSeq); //if the id isn't valid it throws an exception
    }

    @Override
    public List<UseLog> getAll() throws PersistenceException {
        List<UseLog> list = useLogDAO.listAll();

        if (list.isEmpty()) {
            throw new PersistenceException("No useLogs found");
        }

        return list;
    }
    
    
 
    
}
