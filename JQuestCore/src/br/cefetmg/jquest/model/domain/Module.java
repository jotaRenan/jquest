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
    private Long id;
    private List<Question> questions;

    public Module() {}

    public Module(String name, String description, Long id, List<Question> questions) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.questions = questions;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    public void addQuestion(Question question) {
        questions.add(question);
    }
    
    public void deleteQuestion(Question question) {
        questions.remove(question);
    } 
    
}
