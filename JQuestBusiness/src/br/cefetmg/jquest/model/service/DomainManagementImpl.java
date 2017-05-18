/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.Domain;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class DomainManagementImpl implements DomainManagement {

    @Override
    public List<Domain> getAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long domainInsert(Domain domain) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void domainUpdate(Domain domain) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void domainRemove(Long domainId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Domain getDomainById(Long domainId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
