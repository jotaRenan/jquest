/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.QuestionAlternativeDAOImpl;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.dao.TrueOrFalseAnswerDAO;
import br.cefetmg.jquest.model.dao.UserDAOImpl;
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
     private final QuestionManagement questionManagement;
     private final UserManagement userManagement;
     public final QuestionAlternativeManagement questionAlternativeManagement;

    public TrueOrFalseAnswerManagementImpl(TrueOrFalseAnswerDAO tofadao) {
        this.tofadao = tofadao;
        questionManagement = new QuestionManagementImpl(QuestionDAOImpl.getInstance());
        userManagement = new UserManagementImpl(UserDAOImpl.getInstance());
        questionAlternativeManagement = new QuestionAlternativeManagementImpl(QuestionAlternativeDAOImpl.getInstance());
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
        if (tofAnswer.getUserId() == null || userManagement.getUserById(tofAnswer.getUserId()) == null) {
            errMsgList.add("userID doesn't exist.");
        }
        if (tofAnswer.getQuestionId() == null || questionManagement.getQuestionById(tofAnswer.getQuestionId()) == null) {
            errMsgList.add("questionID doesn't exist.");
        }
        if (tofAnswer.getOptionSeq() == null || questionAlternativeManagement.getQuestionAlternativeById(tofAnswer.getOptionSeq(), tofAnswer.getQuestionId()) == null) {
            errMsgList.add("optionID doesn't exist.");
        }
        if (tofAnswer.getUseSeq() == null) {
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
    public boolean update(TrueOrFalseAnswer tofAnswer) throws BusinessException, PersistenceException {
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
        return tofadao.update(tofAnswer);
    }

    @Override
    public boolean remove(Long tofAnswerId) throws PersistenceException, BusinessException {
        if (tofAnswerId == null) {
            throw new BusinessException("No Answer ID was informed");
        }
        return tofadao.remove(tofAnswerId);
    }

    @Override
    public TrueOrFalseAnswer getToFAnswerById(Long tofAnswerId, Long questionId) throws BusinessException, PersistenceException {
        if (tofAnswerId == null) {
            throw new BusinessException("No Answer ID was informed");
        }
        if (questionId == null) {
            throw new BusinessException("No Question ID was informed");
        }
        
        return tofadao.getToFAnswerById(tofAnswerId, questionId);
    }
    
    @Override
    public TrueOrFalseAnswer getAnswersByUserAndQuestionId(Long userId, Long questionId)throws BusinessException, PersistenceException {
        List<String> errMsgList = new ArrayList<>();
        if (userId == null) {
            errMsgList.add("No user id was informed");
        }
        
        if (questionId == null) {
            errMsgList.add("No question id was informed");
        }

        if (!errMsgList.isEmpty()) {
            final String errMsg = "";
            errMsgList.stream().forEach(msg -> errMsg.concat(msg + "\n"));
            throw new BusinessException(errMsg);
        }
        return tofadao.getAnswersByUserAndQuestionId(userId, questionId);
    }
    
    @Override
    public TrueOrFalseAnswer getAllByUserId(Long userId) throws BusinessException, PersistenceException {
        List<String> errMsgList = new ArrayList<>();
        if (userId == null) {
            errMsgList.add("No user id was informed");
        }

        if (!errMsgList.isEmpty()) {
            final String errMsg = "";
            errMsgList.stream().forEach(msg -> errMsg.concat(msg + "\n"));
            throw new BusinessException(errMsg);
        }
        return tofadao.getAllByUserId(userId);
    }
    
    @Override
    public List<TrueOrFalseAnswer> getAll() throws PersistenceException {
        return tofadao.listAll();
    }
    
}
