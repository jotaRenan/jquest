/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.domain;

import java.util.List;

/**
 *
 * @author Jota Renan
 */
public class Domain {
    private String name;
    private Long id;
    private List<Module> modules;

    public Domain() {}
    
    public Domain(String name, Long id, List<Module> modules) {
        this.name = name;
        this.id = id;
        this.modules = modules;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
    
    public void addModule(Module module) {
        modules.add(module);
    }
    
    public void deleteModule(Module module) {
        modules.remove(module);
    } 
    
}
