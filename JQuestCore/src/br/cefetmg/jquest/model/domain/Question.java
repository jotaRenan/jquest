/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.domain;

/**
 *
 * @author Paula Ribeiro
 */
public class Question {
    private Long id;
    private Long domainId;
    private Long moduleId;
    private String headline;
    private String dificulty;
    private char type;

    public Question() {}

    public Question(Long id, Long domainId, Long moduleId, String headline, String dificulty, char type) {
        this.id = id;
        this.domainId = domainId;
        this.moduleId = moduleId;
        this.headline = headline;
        this.dificulty = dificulty;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String enunciado) {
        this.headline = enunciado;
    }

    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
    
}
