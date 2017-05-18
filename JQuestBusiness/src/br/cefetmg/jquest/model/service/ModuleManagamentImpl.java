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
 * @author Aluno
 */
public class ModuleManagamentImpl implements ModuleManagement {

    @Override
    public Long moduleInsert(Module module) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moduleUpdate(Module module) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moduleRemove(Long moduleId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Module getModuleById(Long moduleId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Module> getAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
