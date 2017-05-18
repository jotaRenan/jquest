/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Module;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface ModuleDAO {
    public void insert(Module module) throws PersistenceException;
    public void update(Module module) throws PersistenceException;
    public Module remove(Long moduleId) throws PersistenceException;
    public Module getModuleById(Long moduleId) throws PersistenceException;
    public List<Module> listAll() throws PersistenceException; 
}
