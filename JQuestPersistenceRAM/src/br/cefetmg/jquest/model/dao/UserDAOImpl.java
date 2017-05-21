/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.User;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class UserDAOImpl implements UserDAO {
    
    private static UserDAOImpl userDAO = null;
    private static HashMap<Long, User> userDB = new HashMap<Long, User>();
    private static long userCount = 0;
    private UserDAOImpl(){
        
    }
    
    public static UserDAOImpl getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
    
    @Override
    synchronized public void insert(User user) throws PersistenceException {
        if (user == null) {
            throw new PersistenceException("User cannot be null");
        }
        Long userId = user.getId();
              
        if (userId != null && userDB.containsKey(userId)) {
            throw new PersistenceException("Duplicate key");
        }
        userId = ++userCount;
        user.setId(userId);
        userDB.put(userId, user);
    }

    @Override
    synchronized public void update(User user) throws PersistenceException {
        if (user == null) {
            throw new PersistenceException("User cannot be null");
        }
        Long userId = user.getId();
        if (userId == null ) {
            throw new PersistenceException("Entity Id cannot be null");
        }
        if (!userDB.containsKey(userId)) {
            throw new PersistenceException("User with id " + user.getId() + " is not persisted");
        }
        userDB.replace(userId, user);
    }

    @Override
    synchronized public User remove(Long userId) throws PersistenceException{
       if (userId == null) {
            throw new PersistenceException("User ID cannot be null");
        }
        if (!userDB.containsKey(userId)){
            throw new PersistenceException("User with id " + userId + " is not persisted");
        }
        return userDB.remove(userId);
    }

    @Override
    synchronized public User getUserById(Long userId) throws PersistenceException{
        if (userId == null) {
            throw new PersistenceException("User ID cannot be null");
        }
        if (!userDB.containsKey(userId)){
            throw new PersistenceException("User with id " + userId + " is not persisted");
        }
        return userDB.get(userId);
    }

    @Override
    public List<User> listAll() {
        List<User> userList = new ArrayList<>();
        Iterator<User> it = userDB.values().iterator();
        while (it.hasNext()) {
            userList.add(it.next());
        }
        return userList;
    }
    
}
