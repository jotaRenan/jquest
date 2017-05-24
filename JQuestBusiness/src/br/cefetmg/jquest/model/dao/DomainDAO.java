/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Domain;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface DomainDAO {
    public Long insert(Domain domain) throws PersistenceException;
    public void update(Domain domain) throws PersistenceException;
    public Domain remove(Long domainId) throws PersistenceException;
    public Domain getDomainById(Long domainId) throws PersistenceException;
    public List<Domain> listAll() throws PersistenceException; 
}
