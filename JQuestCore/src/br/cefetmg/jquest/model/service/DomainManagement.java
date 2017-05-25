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
 * @author Jota Renan
 */
public interface DomainManagement {
    public Long domainInsert(Domain domain) throws BusinessException, PersistenceException;
    public void domainUpdate(Domain domain) throws BusinessException, PersistenceException;
    public Domain domainRemove(Long domainId) throws PersistenceException;
    public Domain getDomainById(Long domainId) throws PersistenceException;
    public List<Domain> getAll() throws PersistenceException;    
}
