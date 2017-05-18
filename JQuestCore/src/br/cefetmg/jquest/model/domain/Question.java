/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.domain;

/**
 *
 * @author Aluno
 */
public class Question {
    private Long id;
    private Domain domain;
    private Module module;
    private String headline;
    private String dificulty;
    private char type;

    public Question() {}

    public Question(Long id, Domain domain, Module module, String enunciado, String dificuldade, char tipo) {
        this.id = id;
        this.domain = domain;
        this.module = module;
        this.headline = enunciado;
        this.dificulty = dificuldade;
        this.type = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getEnunciado() {
        return headline;
    }

    public void setEnunciado(String enunciado) {
        this.headline = enunciado;
    }

    public String getDificuldade() {
        return dificulty;
    }

    public void setDificuldade(String dificuldade) {
        this.dificulty = dificuldade;
    }

    public char getTipo() {
        return type;
    }

    public void setTipo(char tipo) {
        this.type = tipo;
    }
    
}
