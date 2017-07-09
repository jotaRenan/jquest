/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.Forum;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface ForumManagement {
    //@TODO JAVADOCS
    public Long forumInsert(Forum forum) throws BusinessException, PersistenceException;
    public boolean forumUpdate(Forum forum) throws BusinessException, PersistenceException;
    public boolean forumRemove(Long discussionSeq) throws PersistenceException;
    public Forum getForumById(Long discussionSeq) throws PersistenceException;
    public List<Forum> getAll() throws PersistenceException;
}
