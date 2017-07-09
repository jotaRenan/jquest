/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.DomainDAOImpl;
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
    private final DomainManagement domainManagement;

    public ModuleManagementImpl(ModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
        this.domainManagement = new DomainManagementImpl(DomainDAOImpl.getInstance());
    }
    
    @Override
    synchronized public Long moduleInsert(Module module) throws BusinessException, PersistenceException {
        if (module == null)
            throw new BusinessException("Module cannot be null");
        
        if (module.getDomainId() == null || domainManagement.getDomainById(module.getDomainId()) == null)
            throw new BusinessException("Module's domain doesn't exist");
            
        if (module.getName() == null)
            throw new BusinessException("Module's name cannot be null");
        
        if (module.getDescription() == null)
            throw new BusinessException("Module's description cannot be null");
        
        moduleDAO.insert(module);
        return module.getId();
    }

    @Override
    public boolean moduleUpdate(Module module) throws BusinessException, PersistenceException {
        if (module == null)
            throw new BusinessException("Module cannot be null");
        
        if (module.getName() == null)
            throw new BusinessException("Module's name cannot be null");
        
        if (module.getDescription() == null)
            throw new BusinessException("Module's description cannot be null");
        
        if (module.getId() == null)
            throw new BusinessException("Module's id cannot be null when updating");
            
        return moduleDAO.update(module);
    }

    @Override
    public boolean moduleRemove(Long moduleId, Long domainId) throws PersistenceException {
        if (moduleId == null)
            throw new PersistenceException("Module's id cannot be null");
        
        if (moduleDAO.getModuleById(moduleId, domainId) == null)
            throw new PersistenceException("Module doesn't exist");
        
        return moduleDAO.remove(moduleId, domainId);
    }

    @Override
    public Module getModuleById(Long moduleId, Long domainId) throws PersistenceException {
        if (moduleId == null || domainId == null)
            throw new PersistenceException("Module's primary keys cannot be null");
        
        return moduleDAO.getModuleById(moduleId, domainId); //if the id isn't valid it throws an exception
    }

    @Override
    public List<Module> getAll() throws PersistenceException {
        List<Module> list = moduleDAO.listAll();
        return list;
    }
    
    @Override
    public List<Module> getModulesByDomainId(Long domainId) throws PersistenceException {
        List<Module> list = moduleDAO.listModulesByDomainId(domainId);
        return list;
    }
}
