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

    private long idQuestion;

    private long idUser;

    private long seqAnswerUser;

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
     * @param IdQuestion The id of the question Answered. Refers to the ID of the question to be awnsered in the
     * database.
     * @param IdAwnser Refers to the ID of this awnser in the database.
     * @param SeqAwnserUser The sequential number of the user answer to this question. Refers to the sequence of the user awnser in this
     * question.
     * @param TxtAwnser Refers to the awnser text of the user.
     * @param ValueGrade Refers to the grade of the user awnser
     */
    public OpenEndedAnswer(long IdQuestion, long IdAwnser, long SeqAwnserUser,
            String TxtAwnser, double ValueGrade) {
        this.idQuestion = IdQuestion;
        this.idUser = IdAwnser;
        this.seqAnswerUser = SeqAwnserUser;
        this.txtAnswer = TxtAwnser;
        this.valueScore = ValueGrade;
    }

    /**
     * Get the value of idUser
     *
     * @return the value of idUser
     */
    public long getIdUser() {
        return idUser;
    }

    /**
     * Set the value of idUser
     *
     * @param idUser new value of idUser
     */
    public void setIdUser(long idUser) {
        this.idUser = idUser;
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
    public long getSeqAnswerUser() {
        return seqAnswerUser;
    }

 
    /**
     * Set the value of IDUser
     *
     * @param IDAwnser new value of IDUser
     */
    public void setIDAnswer(long IDAwnser) {
        this.idUser = IDAwnser;
    }

    /**
     * Get the value of iDQuestion
     *
     * @return the value of iDQuestion
     */
    public long getIDQuestion() {
        return idQuestion;
    }

    /**
     * Set the value of iDQuestion
     *
     * @param IdQuestion The id of the question Answered. new value of iDQuestion
     */
    public void setIdQuestion(long IdQuestion) {
        this.idQuestion = IdQuestion;
    }

}
