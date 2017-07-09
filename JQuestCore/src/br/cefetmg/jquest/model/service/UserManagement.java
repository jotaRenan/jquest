/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.User;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface UserManagement {
    public Long userInsert(User user) throws BusinessException, PersistenceException;
    public boolean userUpdate(User user) throws BusinessException, PersistenceException;
    public boolean userRemove(Long userId) throws PersistenceException;
    public User getUserById(Long userId) throws PersistenceException;
    public List<User> getAll() throws PersistenceException;
}
