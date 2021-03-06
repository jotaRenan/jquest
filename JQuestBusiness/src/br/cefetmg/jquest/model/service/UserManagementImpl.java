/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.UserDAO;
import br.cefetmg.jquest.model.domain.User;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public class UserManagementImpl implements UserManagement {

    private final UserDAO userDAO;

    public UserManagementImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    @Override
    public Long userInsert(User user) throws BusinessException, PersistenceException {
        if (user == null) {
            throw new BusinessException("User cannot be null");
        }
        if (user.getId() == null) {
            throw new BusinessException("User's ID is required");
        }
        if (user.getUserName() == null
                || user.getUserName().isEmpty()) {
            throw new BusinessException("User's name cannot be null");
        }
        if (user.getPassword() == null) {
            throw new BusinessException("User's password cannot be null");
        }
        if (user.getEmail() == null
                || user.getEmail().isEmpty()) {
            throw new BusinessException("User's email cannot be null");
        }
        if (user.getIdtProfile() == 0) {
            throw new BusinessException("User's profile idt cannot be null");
        }
        return userDAO.insert(user);
    }

    @Override
    public boolean userUpdate(User user) throws BusinessException, PersistenceException {
        if (user == null) {
            throw new BusinessException("User cannot be null");
        }
        if (user.getId() == null) {
            throw new BusinessException("User's ID is required");
        }
        if (user.getUserName() == null
                || user.getUserName().isEmpty()) {
            throw new BusinessException("User's name cannot be null");
        }
        if (user.getPassword() == null) {
            throw new BusinessException("User's password cannot be null");
        }
        if (user.getEmail() == null
                || user.getEmail().isEmpty()) {
            throw new BusinessException("User's email cannot be null");
        }
        if (user.getIdtProfile() == 0) {
            throw new BusinessException("User's profile idt cannot be null");
        }
        return userDAO.update(user);
    }

    @Override
    public boolean userRemove(Long userId) throws PersistenceException {
        if (userId == null) {
            throw new PersistenceException("User's Id cannot be null");
        }
        if (userDAO.getUserById(userId) == null) {
            throw new PersistenceException("User with id " + userId + "doesn't exist");
        }
        return userDAO.remove(userId);
    }

    @Override
    public User getUserById(Long userId) throws PersistenceException {
        if (userId == null) {
            throw new PersistenceException("User's Id cannot be null");
        }
        return userDAO.getUserById(userId);
    }

    @Override
    public List<User> getAll() throws PersistenceException {
        return userDAO.listAll();
    }

    @Override
    public User getUserByEmail(String email) throws PersistenceException {
        if (email == null || email.isEmpty()) {
            throw new PersistenceException("User's email cannot be null");
        }
        return userDAO.getUserByEmail(email);
    }
    
}
