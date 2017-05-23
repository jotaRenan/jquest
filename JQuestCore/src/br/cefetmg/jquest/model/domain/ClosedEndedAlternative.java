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
public class ClosedEndedAlternative {
    private Long questionId;
    private Long optionSeq;
    private String assertionText;
    private boolean isCorrect;

    public ClosedEndedAlternative() {
    }

    public ClosedEndedAlternative(Long questionId, Long optionSeq, String assertionText, boolean isCorrect) {
        this.questionId = questionId;
        this.optionSeq = optionSeq;
        this.assertionText = assertionText;
        this.isCorrect = isCorrect;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getOptionSeq() {
        return optionSeq;
    }

    public void setOptionSeq(Long optionSeq) {
        this.optionSeq = optionSeq;
    }

    public String getAssertionText() {
        return assertionText;
    }

    public void setAssertionText(String assertionText) {
        this.assertionText = assertionText;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    
}
