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
    private Long voteID;
    private boolean isLiked;

    public Vote() {}

    public Vote(Long questionId, Long discussionSeq, Long commentarySeq, Long userId, Long voteID, boolean isLiked) {
        this.questionId = questionId;
        this.discussionSeq = discussionSeq;
        this.commentarySeq = commentarySeq;
        this.userId = userId;
        this.voteID = voteID;
        this.isLiked = isLiked;
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

    public Long getVoteID() {
        return voteID;
    }

    public void setVoteID(Long voteID) {
        this.voteID = voteID;
    }

    public boolean isIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

   
    
}
