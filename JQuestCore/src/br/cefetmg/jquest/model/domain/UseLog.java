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

    public UseLog(Long idUser, Long useSeq, Date useDate) {
        this.idUser = idUser;
        this.useSeq = useSeq;
        this.useDate = useDate;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getUseSeq() {
        return useSeq;
    }

    public void setUseSeq(Long useSeq) {
        this.useSeq = useSeq;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }
    
}
