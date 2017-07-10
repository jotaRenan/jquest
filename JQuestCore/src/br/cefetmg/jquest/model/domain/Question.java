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
    private Long userId;
    private String headline;
    private char dificulty;
    private int weight;
    private char type;

    public Question() {}

    public Question(Long id, Long domainId, Long moduleId, Long userId, String headline, char dificulty, char type) {
        this.domainId = domainId;
        this.moduleId = moduleId;
        this.userId = userId;
        this.headline = headline;
        this.dificulty = dificulty;
        this.weight = 1;
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public char getDificulty() {
        return dificulty;
    }

    public void setDificulty(char dificulty) {
        this.dificulty = dificulty;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    
    
}
