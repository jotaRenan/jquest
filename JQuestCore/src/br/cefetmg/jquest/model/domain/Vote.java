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
public class Vote {
    private Long questionId;
    private Long discussionSeq;
    private Long commentarySeq;
    private Long userId;
    private boolean isLiked;

    public Vote() {}

    public Vote(Long questionId, Long discussionId, Long commentaryId, Long userId, boolean isLiked) {
        this.questionId = questionId;
        this.discussionSeq = discussionId;
        this.commentarySeq = commentaryId;
        this.userId = userId;
        this.isLiked = isLiked;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getDiscussionId() {
        return discussionSeq;
    }

    public void setDiscussionId(Long discussionId) {
        this.discussionSeq = discussionId;
    }

    public Long getCommentaryId() {
        return commentarySeq;
    }

    public void setCommentaryId(Long commentaryId) {
        this.commentarySeq = commentaryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }
    
}
