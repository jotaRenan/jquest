/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.DomainDAO;
import br.cefetmg.jquest.model.domain.Domain;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Paula Ribeiro
 */
public class DomainManagementImpl implements DomainManagement {

    private final DomainDAO domainDAO;

    public DomainManagementImpl(DomainDAO domainDAO) {
        this.domainDAO = domainDAO;
    }
    
    @Override
    synchronized public Long domainInsert(Domain domain) throws BusinessException, PersistenceException {
        if (domain == null)
            throw new BusinessException("Domain cannot be null");
        
        if (domain.getName() == null || domain.getName().isEmpty())
            throw new BusinessException("Domain's name cannot be null or empty.");
        
        if (domain.getDescription() == null || domain.getDescription().isEmpty())
            throw new BusinessException("Domain's description cannot be null or empty");
        
        domainDAO.insert(domain);
        return domain.getId();
    }

    @Override
    public boolean domainUpdate(Domain domain) throws BusinessException, PersistenceException {
        if (domain == null)
            throw new BusinessException("Domain cannot be null");
        
        if (domain.getName() == null || domain.getName().isEmpty())
            throw new BusinessException("Domain's name cannot be null or empty.");
        
        if (domain.getDescription() == null || domain.getDescription().isEmpty())
            throw new BusinessException("Domain's description cannot be null or empty");
        
        if (domain.getId() == null)
            throw new BusinessException("Domain's id cannot be null when updating");
            
        return domainDAO.update(domain);
    }

    @Override
    public boolean domainRemove(Long domainId) throws PersistenceException {
        if (domainId == null)
            throw new PersistenceException("Domain's id cannot be null");
        
        if (domainDAO.getDomainById(domainId) == null)
            throw new PersistenceException("Domain with id " + domainId + "doesn't exist");
        
        return domainDAO.remove(domainId);
    }

    @Override
    public Domain getDomainById(Long domainId) throws PersistenceException {
        if (domainId == null)
            throw new PersistenceException("Domain's id cannot be null");
        
        return domainDAO.getDomainById(domainId); //if the id isn't valid it throws an exception
    }
    
    @Override
    public List<Domain> getAll() throws PersistenceException {
        List<Domain> list = domainDAO.listAll();
        return list;
    }
    
}
