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
    private String description;
    private Long id;

    public Domain() {}
    
    public Domain(String name, String description, Long id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 
    
}
