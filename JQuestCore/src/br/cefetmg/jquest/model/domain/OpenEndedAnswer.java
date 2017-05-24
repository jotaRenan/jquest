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

    /**
     * Empty JavaBeans constructor.
     */
    public OpenEndedAnswer() {
    }

    /**
     * Complete constructor.
     *
     * @param questionID The id of the question Answered. Refers to the ID of the question to be awnsered in the
     * database.
     * @param idAwnser Refers to the ID of this awnser in the database.
     * @param seqAnswerUser The sequential number of the user answer to this question. Refers to the sequence of the user awnser in this
     * question.
     * @param txtAwnser Refers to the awnser text of the user.
     * @param valueGrade Refers to the grade of the user awnser
     */
    public OpenEndedAnswer(Long questionID, Long idAwnser, Long seqAnswerUser,
            String txtAwnser, double valueGrade) {
        this.questionID = questionID;
        this.userID = idAwnser;
        this.seqAnswerUser = seqAnswerUser;
        this.txtAnswer = txtAwnser;
        this.valueScore = valueGrade;
    }

    /**
     * Get the value of userID
     *
     * @return the value of userID
     */
    public Long getuserID() {
        return userID;
    }

    /**
     * Set the value of userID
     *
     * @param userID new value of userID
     */
    public void setuserID(Long userID) {
        this.userID = userID;
    }

    /**
     * Get the value of ValueScore
     *
     * @return the value of ValueScore
     */
    public double getValueGrade() {
        return valueScore;
    }

    /**
     * Set the value of ValueScore
     *
     * @param ValueGrade new value of ValueScore
     */
    public void setValueGrade(double ValueGrade) {
        this.valueScore = ValueGrade;
    }

    /**
     * Get the value of txtAnswer
     *
     * @return the value of txtAnswer
     */
    public String getTxtAnswer() {
        return txtAnswer;
    }

    /**
     * Set the value of txtAnswer
     *
     * @param TxtAwnser new value of txtAnswer
     */
    public void setTxtAnswer(String TxtAwnser) {
        this.txtAnswer = TxtAwnser;
    }

    /**
     * Get the value of seqAnswerUser
     *
     * @return the value of seqAnswerUser
     */
    public Long getSeqAnswerUser() {
        return seqAnswerUser;
    }

 
    /**
     * Set the value of userID
     *
     * @param IDAwnser new value of userID
     */
    public void setIDAnswer(Long IDAwnser) {
        this.userID = IDAwnser;
    }

    /**
     * Get the value of questionID
     *
     * @return the value of questionID
     */
    public Long getquestionID() {
        return questionID;
    }

    /**
     * Set the value of questionID
     *
     * @param questionID The id of the question Answered. new value of questionID
     */
    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

}
