/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.TrueOrFalseAnswerDAO;
import br.cefetmg.jquest.model.domain.TrueOrFalseAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Pedro Renan
 */
public class TrueOrFalseAnswerManagementImpl implements TrueOrFalseAnswerManagement {

    private final TrueOrFalseAnswerDAO tofadao;

    public TrueOrFalseAnswerManagementImpl(TrueOrFalseAnswerDAO tofadao) {
        this.tofadao = tofadao;
    }
    
    @Override
    public Long insert(TrueOrFalseAnswer tofAnswer) throws BusinessException, PersistenceException {
        List<String> errMsgList = new ArrayList<>();
        if (tofAnswer == null) {
            throw new BusinessException("No TrueOrFalseAnswer was informed");
        }
        if (tofAnswer.getUserAnswer() == null) {
            errMsgList.add("No answer to the question was informed");
        }
        if ( tofAnswer.getUserId() == null) {
            errMsgList.add("No user id was informed");
        }
        if ( tofAnswer.getQuestionId() == null) {
            errMsgList.add("No question ID was associated to the answer");
        }
        if ( tofAnswer.getOptionSeq() == null) {
            errMsgList.add("No option ID was informed");
        }
        if ( tofAnswer.getUseSeq() == null) {
            errMsgList.add("No user Sequence was informed");
        }
        if (!errMsgList.isEmpty()) {
            String errMsg = "";
            errMsg = errMsgList.stream().reduce( "", (errMsgStack, msg) -> errMsgStack = errMsgStack.concat(msg + "\n"));
            throw new BusinessException(errMsg);
        }
        return tofadao.insert(tofAnswer);
    }

    @Override
    public void update(TrueOrFalseAnswer tofAnswer) throws BusinessException, PersistenceException {
        List<String> errMsgList = new ArrayList<>();
        if (tofAnswer == null) {
            throw new BusinessException("No TrueOrFalseAnswer was informed");
        }
        if (tofAnswer.getUserAnswer() == null) {
            errMsgList.add("No answer to the question was informed");
        }
        if ( tofAnswer.getUserId() == null) {
            errMsgList.add("No user id was informed");
        }
        if ( tofAnswer.getQuestionId() == null) {
            errMsgList.add("No question ID was associated to the answer");
        }
        if ( tofAnswer.getOptionSeq() == null) {
            errMsgList.add("No option ID was informed");
        }
        if ( tofAnswer.getUseSeq() == null) {
            errMsgList.add("No user Sequence was informed");
        }
        if (!errMsgList.isEmpty()) {
            final String errMsg = "";
            errMsgList.stream().forEach( msg -> errMsg.concat(msg + "\n"));
            throw new BusinessException(errMsg);
        }
        tofadao.update(tofAnswer);
    }

    @Override
    public TrueOrFalseAnswer remove(Long tofAnswerId) throws PersistenceException, BusinessException {
        if (tofAnswerId == null) {
            throw new BusinessException("No Answer ID was informed");
        }
        return tofadao.remove(tofAnswerId);
    }

    @Override
    public TrueOrFalseAnswer getToFAnswerById(Long tofAnswerId) throws BusinessException, PersistenceException {
        if (tofAnswerId == null) {
            throw new BusinessException("No Answer ID was informed");
        }
        return tofadao.getToFAnswerById(tofAnswerId);
    }

    @Override
    public List<TrueOrFalseAnswer> listAll() throws PersistenceException {
        return tofadao.listAll();
    }
    
}
