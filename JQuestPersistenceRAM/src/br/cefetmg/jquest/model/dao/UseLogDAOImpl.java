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
        Long seqUseLog = useLog.getSeqUso();
        
        if (seqUseLog!= null && useLogDB.containsKey(seqUseLog)) {
            throw new PersistenceException("Duplicated key");
        }
        seqUseLog = ++useLogCount;
        useLog.setSeqUso(seqUseLog);
        useLogDB.put(seqUseLog, useLog);
        
        return seqUseLog;
    }

    @Override
    synchronized public void update(UseLog useLog) throws PersistenceException {
        if (useLog == null) {
            throw new PersistenceException("UseLog cannot be null");
        }
        Long seqUseLog = useLog.getSeqUso();
        if (seqUseLog == null) {
            throw new PersistenceException("Sequence of UseLog cannot be null");
        }
        if (useLogDB.containsKey(seqUseLog)) {
            throw new PersistenceException("UseLog with sequence " + useLog.getSeqUso()+ " is not persisted");
        }
        useLogDB.replace(seqUseLog, useLog);
    }

    @Override
    synchronized public UseLog remove(Long seqUseLog) throws PersistenceException{
        if (seqUseLog == null) {
            throw new PersistenceException("Sequence of UseLog cannot be null");
        }
        if (!useLogDB.containsKey(seqUseLog)) {
            throw new PersistenceException("UseLog with sequence " + seqUseLog + " is not persisted");
        }
        return useLogDB.remove(seqUseLog);
    }

    @Override
    synchronized public UseLog getUseLogBySeq(Long seqUseLog) throws PersistenceException{
        if (seqUseLog == null) {
            throw new PersistenceException("UseLog sequence cannot be null");
        }
        if (!useLogDB.containsKey(seqUseLog)) {
            throw new PersistenceException("UseLog with sequence " + seqUseLog + " is not persisted");
        }
        return useLogDB.get(seqUseLog);
    }

    @Override
    public List<UseLog> listAll() {
        List<UseLog> useLogList = new ArrayList<>();
        Iterator<UseLog> it = useLogDB.values().iterator();
        while (it.hasNext()) {
            useLogList.add(it.next());
        }
        return useLogList;
    }
}
