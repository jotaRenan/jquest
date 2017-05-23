/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.domain;

import java.util.Date;

/**
 *
 * @author Paula Ribeiro
 */
public class UseLog {
    private Long idUser;
    private Long useSeq;
    private Date useDate;

    public UseLog() {}

    public UseLog(Long idUser, Long seqUso, Date dataUso) {
        this.idUser = idUser;
        this.useSeq = seqUso;
        this.useDate = dataUso;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getSeqUso() {
        return useSeq;
    }

    public void setSeqUso(Long seqUso) {
        this.useSeq = seqUso;
    }

    public Date getDataUso() {
        return useDate;
    }

    public void setDataUso(Date dataUso) {
        this.useDate = dataUso;
    }
    
}
