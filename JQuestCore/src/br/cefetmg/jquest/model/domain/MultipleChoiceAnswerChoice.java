/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.domain;

/**
 *
 * @author João Pedro Renan
 */
public class MultipleChoiceAnswerChoice {
    
    private Long userId;
    private Long useSeq;
    private Long questionId;
    private Long userAnswerSeq;
    private Long optionSeq;

    public MultipleChoiceAnswerChoice() {
    }

    public MultipleChoiceAnswerChoice(Long userId, Long useSeq, Long questionId, Long userAnswerSeq, Long optionSeq) {
        this.userId = userId;
        this.useSeq = useSeq;
        this.questionId = questionId;
        this.userAnswerSeq = userAnswerSeq;
        this.optionSeq = optionSeq;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUseSeq() {
        return useSeq;
    }

    public void setUseSeq(Long useSeq) {
        this.useSeq = useSeq;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserAnswerSeq() {
        return userAnswerSeq;
    }

    public void setUserAnswerSeq(Long userAnswerSeq) {
        this.userAnswerSeq = userAnswerSeq;
    }

    public Long getOptionSeq() {
        return optionSeq;
    }

    public void setOptionSeq(Long optionSeq) {
        this.optionSeq = optionSeq;
    }
    
}
