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
public class Forum {
    private Long questionId;
    private Long discussionSeq;
    private Long userId;
    private String name;
    private String description;

    public Forum() {}

    public Forum(Long questionId, Long discussionSeq, Long userId, String name, String description) {
        this.questionId = questionId;
        this.discussionSeq = discussionSeq;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getDiscussionSeq() {
        return discussionSeq;
    }

    public void setDiscussionSeq(Long discussionSeq) {
        this.discussionSeq = discussionSeq;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
    
}
