/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.VoteDAO;
import br.cefetmg.jquest.model.domain.Vote;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class VoteManagementImpl implements VoteManagement {
       private VoteDAO DAO;

    public VoteManagementImpl() {
    }

    public VoteManagementImpl(VoteDAO DAO) {
        this.DAO = DAO;
    }
    

    /**
     * Set the value of DAO
     *
     * @param DAO new value of DAO
     */
    public void setDAO(VoteDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public Long voteInsert(Vote vote) throws BusinessException, PersistenceException {
        List<String> errMsgList = new ArrayList<>();
        if(vote == null)
            throw new PersistenceException("The object vote cannot be null.");
        if (vote.getVoteID() == null) {
            errMsgList.add("Vote can´t be null.");
        }
        if(vote.equals(new Vote()))
            errMsgList.add("The object Vote cannot be empty.");
        
        if (!errMsgList.isEmpty()) {
            String errMsg = "";
            errMsg = errMsgList.stream().reduce("", (errMsgStack, msg) -> errMsgStack = errMsgStack.concat(msg + "\n"));
            throw new BusinessException(errMsg);
        }
        DAO.insert(vote);
        return vote.getQuestionId();
    }

    @Override
    public void voteUpdate(Vote vote) throws BusinessException, PersistenceException {
        List<String> errMsgList = new ArrayList<>();
        if(vote == null)
            throw new PersistenceException("The object vote cannot be null.");
        if(vote.getVoteID() == null)
            errMsgList.add("Vote can´t be null.");
        if (!errMsgList.isEmpty()) {
            final String errMsg = "";
            errMsgList.stream().forEach(msg -> errMsg.concat(msg + "\n"));
            throw new BusinessException(errMsg);
        }
        
        DAO.update(vote);
    }

    @Override
    public void voteRemove(Long voteID) throws PersistenceException {
        if(voteID == null)
            throw new PersistenceException("Vote can´t be null.");
        DAO.remove(voteID);
    }

    @Override
    public Vote getVoteById(Long voteID) throws PersistenceException {
       if(voteID == null)
            throw new PersistenceException("Vote can´t be null.");
        
        return DAO.getVoteById(voteID);
    }

    @Override
    public List<Vote> getAll() throws PersistenceException {
        List<Vote> aux = DAO.listAll();
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        return aux;
    }
}
