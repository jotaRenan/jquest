/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Module;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class ModuleDAOImpl implements ModuleDAO {

    
    private static ModuleDAOImpl moduleDAO = null;
    private static HashMap <Long, Module> moduleDB;
    private static long moduleCount = 0;

    private ModuleDAOImpl() {
        moduleDB = new HashMap <>();
    }

    public static ModuleDAOImpl getInstance() {
        if (moduleDAO == null) {
            moduleDAO = new ModuleDAOImpl();
        }
        return moduleDAO;
    }
    
    

    @Override
    public Long insert(Module module) throws PersistenceException {
        if (module == null) {
            throw new PersistenceException("Module cannot be null");
        }
        Long moduleId = module.getId();
        
        if (moduleId != null && moduleDB.containsKey(moduleId)) {
            throw new PersistenceException("Duplicate key");
        }
        
		moduleId = ++moduleCount;
        module.setId(moduleId);
        moduleDB.put(moduleId, module);
		return moduleId;
    }

    @Override
    public boolean update(Module module) throws PersistenceException {
        if (module == null) {
            throw new PersistenceException("Module cannot be null");
        }
        Long moduleId = module.getId();
        if (moduleId == null ) {
            throw new PersistenceException("Entity Id cannot be null");
        }
        if (!moduleDB.containsKey(moduleId)) {
            throw new PersistenceException("Module with id " + module.getId() + " is not persisted");
        }
        moduleDB.replace(moduleId, module);
        return true;
    }

    @Override
    public boolean remove(Long moduleId) throws PersistenceException {
        if (moduleId == null) {
            throw new PersistenceException("Module ID cant be null");
        }
        if (!moduleDB.containsKey(moduleId)){
            throw new PersistenceException("Module with id " + moduleId + " is not persisted");
        }
        moduleDB.remove(moduleId);
        return true;
    }

    @Override
    public Module getModuleById(Long moduleId) throws PersistenceException {
        if (moduleId == null) {
            throw new PersistenceException("Module ID cant be null");
        }
        if (!moduleDB.containsKey(moduleId)){
            throw new PersistenceException("Module with id " + moduleId + " is not persisted");
        }
        return moduleDB.get(moduleId);
    }

    @Override
    public List<Module> listAll() {
        List<Module> moduleList = new ArrayList<>(moduleDB.values());
        return moduleList;
    }
    
    @Override
    public List<Long> listAllDomains() {
        List<Long> domainList = new ArrayList<>();
        Iterator<Module> it = moduleDB.values().iterator();
        while (it.hasNext()) {
            domainList.add(it.next().getDomainId());
        }
        return domainList;
    }
    
    //Test
    
}