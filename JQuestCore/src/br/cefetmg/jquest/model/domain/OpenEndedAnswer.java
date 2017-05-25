/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.domain;

/**
 *
 * @author Thalesgsn
 */
public class OpenEndedAnswer {

    private Long questionID;

    private Long userID;

    private Long seqAnswerUser;

    private String txtAnswer;

    private double valueScore;

    public OpenEndedAnswer() {
    }

    public OpenEndedAnswer(Long questionID, Long userID, Long seqAnswerUser, String txtAnswer, double valueScore) {
        this.questionID = questionID;
        this.userID = userID;
        this.seqAnswerUser = seqAnswerUser;
        this.txtAnswer = txtAnswer;
        this.valueScore = valueScore;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getSeqAnswerUser() {
        return seqAnswerUser;
    }

    public void setSeqAnswerUser(Long seqAnswerUser) {
        this.seqAnswerUser = seqAnswerUser;
    }

    public String getTxtAnswer() {
        return txtAnswer;
    }

    public void setTxtAnswer(String txtAnswer) {
        this.txtAnswer = txtAnswer;
    }

    public double getValueScore() {
        return valueScore;
    }

    public void setValueScore(double valueScore) {
        this.valueScore = valueScore;
    }

   
}
