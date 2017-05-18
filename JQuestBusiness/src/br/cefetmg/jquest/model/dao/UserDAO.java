/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.User;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface UserDAO {
    public void insert(User user);
    public void update(User user);
    public User remove(Long userId);
    public User getUserById(Long userId);
    public List<User> listAll(); 
}
