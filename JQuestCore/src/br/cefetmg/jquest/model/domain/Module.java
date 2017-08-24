/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.domain;

import java.util.List;

/**
 *
 * @author Paula Ribeiro
 */
public class Module {
    private String name;
    private String description;
    private Long domainId;
    private Long id;

    public Module() {}
    public Module(String name, String description, Long domainId, Long id) {
        this.name = name;
        this.description = description;
        this.domainId = domainId;
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

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
