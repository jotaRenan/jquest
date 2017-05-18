/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Module;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface ModuleDAO {
    public void insert(Module module);
    public void update(Module module);
    public Module remove(Long moduleId);
    public Module getModuleById(Long moduleId);
    public List<Module> listAll(); 
}
