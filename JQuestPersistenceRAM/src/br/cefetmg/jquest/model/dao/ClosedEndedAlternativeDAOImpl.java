/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.ClosedEndedAlternative;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Paula Ribeiro
 */
public class ClosedEndedAlternativeDAOImpl implements ClosedEndedAlternativeDAO {
    private static ClosedEndedAlternativeDAOImpl cEAlternativeDAO = null;
    private static HashMap<Long, ClosedEndedAlternative> cEAlternativeDB = new HashMap<Long, ClosedEndedAlternative>();
    private static long cEAlternativeCount = 0;

    private ClosedEndedAlternativeDAOImpl() {}
    
    public static ClosedEndedAlternativeDAOImpl getInstance() {
        if (cEAlternativeDAO == null) {
            cEAlternativeDAO = new ClosedEndedAlternativeDAOImpl();
        }
        return cEAlternativeDAO;
    }
    
    @Override
    public Long insert(ClosedEndedAlternative closedEndedAlt) throws PersistenceException {
        if (closedEndedAlt == null) {
            throw new PersistenceException("Domain cannot be null");
        }
        Long answerId = closedEndedAlt.getOptionSeq();
        
        if (answerId != null && cEAlternativeDB.containsKey(answerId)) {
            throw new PersistenceException("Duplicate key");
        }
        answerId = ++cEAlternativeCount;
        closedEndedAlt.setOptionSeq(answerId);
        cEAlternativeDB.put(answerId, closedEndedAlt);
        return answerId;
    }

    @Override
    public void update(ClosedEndedAlternative closedEndedAlt) throws PersistenceException {
        if (closedEndedAlt == null) {
            throw new PersistenceException("Answer cannot be null");
        }
        Long answerId = closedEndedAlt.getOptionSeq();
        if (answerId == null) {
            throw new PersistenceException("Entity Id cannot be null");
        }
        if (!cEAlternativeDB.containsKey(answerId)) {
            throw new PersistenceException("Answer with id " + closedEndedAlt.getOptionSeq()+ " is not persisted");
        }
        cEAlternativeDB.replace(answerId, closedEndedAlt);
    }

    @Override
    public ClosedEndedAlternative remove(Long closedEndedAltId) throws PersistenceException {
        if (closedEndedAltId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!cEAlternativeDB.containsKey(closedEndedAltId)){
            throw new PersistenceException("Answer with id " + closedEndedAltId + " is not persisted");
        }
        return cEAlternativeDB.remove(closedEndedAltId);
    }

    @Override
    public ClosedEndedAlternative getClosedEndedAlternativeById(Long closedEndedAltId) throws PersistenceException {
        if (closedEndedAltId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!cEAlternativeDB.containsKey(closedEndedAltId)) {
            throw new PersistenceException("Answer with id " + closedEndedAltId + " is not persisted");
        }
        return cEAlternativeDB.get(closedEndedAltId);
    }

    @Override
    public List<ClosedEndedAlternative> listAll() throws PersistenceException {
        List<ClosedEndedAlternative> aux = new ArrayList(cEAlternativeDB.values());        
        return aux;
    }
}
