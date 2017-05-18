/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Module;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class ModuleDAOImpl implements ModuleDAO {

    
    private static ModuleDAOImpl moduleDAO = null;
    private static HashMap<Long, Module> moduleDB = new HashMap<Long, Module>();

    private ModuleDAOImpl() {
    }

    public static ModuleDAOImpl getInstance() {
        if (moduleDAO == null) {
            moduleDAO = new ModuleDAOImpl();
        }
        return moduleDAO;
    }

    @Override
    public void insert(Module module) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Module module) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Module remove(Long moduleId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Module getModuleById(Long moduleId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Module> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
