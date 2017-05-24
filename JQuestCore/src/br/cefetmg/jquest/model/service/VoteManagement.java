/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.Vote;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface VoteManagement {
    //@TODO: Javadocs
    public Long insert(Vote vote) throws BusinessException, PersistenceException;
    public void update(Vote vote) throws BusinessException, PersistenceException;
    public void remove(Long questionID, Long discussionID, Long commentaryID, Long userID) throws PersistenceException;
    public Vote getVoteById(Long questionID, Long discussionID, Long commentaryID, Long userID) throws PersistenceException;
    public List<Vote> getAll() throws PersistenceException;
}
