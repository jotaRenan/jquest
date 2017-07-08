/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.Module;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface ModuleManagement {
    public Long moduleInsert(Module module) throws BusinessException, PersistenceException;
    public boolean moduleUpdate(Module module) throws BusinessException, PersistenceException;
    public boolean moduleRemove(Long moduleId) throws PersistenceException;
    public Module getModuleById(Long moduleId) throws PersistenceException;
    public List<Module> getAll() throws PersistenceException;    
    public List<Long> getAllDomains() throws PersistenceException;    
}
