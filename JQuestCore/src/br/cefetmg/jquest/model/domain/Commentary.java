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
public class Commentary {
    private Long questionId;
    private Long discussionId;
    private Long commentarySeq;
    private Long userId;
    private String textCommentary;

    public Commentary() {}

    public Commentary(Long questionId, Long discussionId, Long commentarySeq, Long userId, String textCommentary) {
        this.questionId = questionId;
        this.discussionId = discussionId;
        this.commentarySeq = commentarySeq;
        this.userId = userId;
        this.textCommentary = textCommentary;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
    }

    public Long getCommentarySeq() {
        return commentarySeq;
    }

    public void setCommentarySeq(Long commentarySeq) {
        this.commentarySeq = commentarySeq;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTextCommentary() {
        return textCommentary;
    }

    public void setTextCommentary(String textCommentary) {
        this.textCommentary = textCommentary;
    }
    
}
