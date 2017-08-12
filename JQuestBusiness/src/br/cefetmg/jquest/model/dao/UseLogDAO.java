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
    public Long insert(UseLog useLog) throws PersistenceException;
    public boolean update(UseLog useLog) throws PersistenceException;
    public boolean remove(Long userId, Long useLogSeq) throws PersistenceException;
    public UseLog getUseLogBySeq(Long userId, Long useLogSeq) throws PersistenceException;
    public List<UseLog> getAllLogsByUserId(Long userId) throws PersistenceException; 
}
