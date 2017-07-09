/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.domain;

/**
 *
 * @author Jota Renan
 */
public class User {
    private String userName;
    private String email;
    private Long id;
    private String password;
    private char idtProfile;

    public User() {}
    
    public User(String name, String email, Long id, String password, char idt) {
        this.userName = name;
        this.email = email;
        this.id = id;
        this.password = password;
        this.idtProfile = idt;
    }

    public char getIdtProfile() {
        return idtProfile;
    }

    public void setIdtProfile(char idt) {
        this.idtProfile = idt;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }
    
}
