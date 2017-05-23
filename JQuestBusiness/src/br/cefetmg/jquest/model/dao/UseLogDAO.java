/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.UseLog;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Haddad
 */
public interface UseLogDAO {
    public void insert(UseLog useLog) throws PersistenceException;
    public void update(UseLog useLog) throws PersistenceException;
    public UseLog remove(Long useLogId) throws PersistenceException;
    public UseLog getUseLogById(Long useLogId) throws PersistenceException;
    public List<UseLog> listAll() throws PersistenceException; 
}
