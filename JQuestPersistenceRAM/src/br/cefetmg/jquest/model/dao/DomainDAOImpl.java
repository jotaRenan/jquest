/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Domain;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class DomainDAOImpl implements DomainDAO {

    
    private static DomainDAOImpl domainDAO = null;
    private static HashMap<Long, Domain> domainDB = new HashMap<Long, Domain>();
    private static long domainCount = 0;
    private DomainDAOImpl() {
    }

    public static DomainDAOImpl getInstance() {
        if (domainDAO == null) {
            domainDAO = new DomainDAOImpl();
        }
        return domainDAO;
    }

    @Override
    public Long insert(Domain domain) throws PersistenceException {
        if (domain == null) {
            throw new PersistenceException("Domain cannot be null");
        }
        Long domainId = domain.getId();
        
        if (domainId != null && domainDB.containsKey(domainId)) {
            throw new PersistenceException("Duplicate key");
        }
        domainId = ++domainCount;
        domain.setId(domainId);
        domainDB.put(domainId, domain);
        return domainId;
    }

    @Override
    public boolean update(Domain domain) throws PersistenceException {
        if (domain == null) {
            throw new PersistenceException("Domain cannot be null");
        }
        Long domainId = domain.getId();
        if (domainId == null ) {
            throw new PersistenceException("Entity Id cannot be null");
        }
        if (!domainDB.containsKey(domainId)) {
            throw new PersistenceException("Domain with id " + domain.getId() + " is not persisted");
        }
        domainDB.replace(domainId, domain);
        return true;
    }

    @Override
    public boolean remove(Long domainId) throws PersistenceException {
        if (domainId == null) {
            throw new PersistenceException("Domain ID cant be null");
        }
        if (!domainDB.containsKey(domainId)){
            throw new PersistenceException("Domain with id " + domainId + " is not persisted");
        }
        domainDB.remove(domainId);
        return true;
    }

    @Override
    public Domain getDomainById(Long domainId) throws PersistenceException {
        if (domainId == null) {
            throw new PersistenceException("Domain ID cant be null");
        }
        if (!domainDB.containsKey(domainId)){
            throw new PersistenceException("Domain with id " + domainId + " is not persisted");
        }
        return domainDB.get(domainId);
    }

    @Override
    public List<Domain> listAll() throws PersistenceException {
        List<Domain> domainList = new ArrayList<>();
        Iterator<Domain> it = domainDB.values().iterator();
        while (it.hasNext()) {
            domainList.add(it.next());
        }
        return domainList;
    }
    
}
