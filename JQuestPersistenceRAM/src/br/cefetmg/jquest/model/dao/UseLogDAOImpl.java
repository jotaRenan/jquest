/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.UseLog;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author GABRIEL HADDAD
 */
public class UseLogDAOImpl implements UseLogDAO{
    
    private static UseLogDAOImpl useLogDAO = null;
    private static HashMap<Long, UseLog> useLogDB = new HashMap<Long, UseLog>();
    private static long useLogCount = 0; 

    private UseLogDAOImpl() {
    }
    
    public static UseLogDAOImpl getInstance() {
        if (useLogDAO == null) {
            useLogDAO = new UseLogDAOImpl();
        }
        return useLogDAO;
    }
    
    @Override
    synchronized public Long insert(UseLog useLog) throws PersistenceException{
        if (useLog == null) {
             throw new PersistenceException("UseLog cannot be null");           
        }
        Long useLogSeq = useLog.getUseSeq();
        
        if (useLogSeq!= null && useLogDB.containsKey(useLogSeq)) {
            throw new PersistenceException("Duplicated key");
        }
        useLogSeq = ++useLogCount;
        useLog.setUseSeq(useLogSeq);
        useLogDB.put(useLogSeq, useLog);
        
        return useLogSeq;
    }

    @Override
    synchronized public void update(UseLog useLog) throws PersistenceException {
        if (useLog == null) {
            throw new PersistenceException("UseLog cannot be null");
        }
        Long useLogSeq = useLog.getUseSeq();
        if (useLogSeq == null) {
            throw new PersistenceException("UseLog sequence cannot be null");
        }
        if (useLogDB.containsKey(useLogSeq)) {
            throw new PersistenceException("UseLog with sequence " + useLog.getUseSeq()+ " is not persisted");
        }
        useLogDB.replace(useLogSeq, useLog);
    }

    @Override
    synchronized public UseLog remove(Long useLogSeq) throws PersistenceException{
        if (useLogSeq == null) {
            throw new PersistenceException("UseLog sequence cannot be null");
        }
        if (!useLogDB.containsKey(useLogSeq)) {
            throw new PersistenceException("UseLog with sequence " + useLogSeq + " is not persisted");
        }
        return useLogDB.remove(useLogSeq);
    }

    @Override
    synchronized public UseLog getUseLogBySeq(Long useLogSeq) throws PersistenceException{
        if (useLogSeq == null) {
            throw new PersistenceException("UseLog sequence cannot be null");
        }
        if (!useLogDB.containsKey(useLogSeq)) {
            throw new PersistenceException("UseLog with sequence " + useLogSeq + " is not persisted");
        }
        return useLogDB.get(useLogSeq);
    }

    @Override
    synchronized public List<UseLog> listAll() {
        List<UseLog> useLogList = new ArrayList<>();
        Iterator<UseLog> it = useLogDB.values().iterator();
        while (it.hasNext()) {
            useLogList.add(it.next());
        }
        return useLogList;
    }
}
