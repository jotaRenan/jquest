/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.ModuleDAO;
import br.cefetmg.jquest.model.domain.Module;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Paula Ribeiro
 */
public class ModuleManagementImpl implements ModuleManagement {

    private final ModuleDAO moduleDAO;

    public ModuleManagementImpl(ModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
    }
    
    @Override
    synchronized public Long moduleInsert(Module module) throws BusinessException, PersistenceException {
        if (module == null)
            throw new BusinessException("Module cannot be null");
        
        if (module.getDomainId() == null)
            throw new BusinessException("Module's domain cannot be null");
            
        if (module.getName() == null)
            throw new BusinessException("Module's name cannot be null");
        
        if (module.getDescription() == null)
            throw new BusinessException("Module's description cannot be null");
        
        moduleDAO.insert(module);
        return module.getId();
    }

    @Override
    public void moduleUpdate(Module module) throws BusinessException, PersistenceException {
        if (module == null)
            throw new BusinessException("Module cannot be null");
        
        if (module.getName() == null)
            throw new BusinessException("Module's name cannot be null");
        
        if (module.getDescription() == null)
            throw new BusinessException("Module's description cannot be null");
        
        if (module.getId() == null)
            throw new BusinessException("Module's id cannot be null when updating");
            
        moduleDAO.update(module);
    }

    @Override
    public Module moduleRemove(Long moduleId) throws PersistenceException {
        if (moduleId == null)
            throw new PersistenceException("Module's id cannot be null");
        
        return moduleDAO.remove(moduleId);
    }

    @Override
    public Module getModuleById(Long moduleId) throws PersistenceException {
        if (moduleId == null)
            throw new PersistenceException("ModuleId's id cannot be null");
        
        return moduleDAO.getModuleById(moduleId); //if the id isn't valid it throws an exception
    }

    @Override
    public List<Module> getAll() throws PersistenceException {
        List<Module> list = moduleDAO.listAll();
        return list;
    }
    
}
