/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Forum;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface ForumDAO {
    //@TODO JAVADOCS
    public void insert(Forum forum) throws BusinessException, PersistenceException;
    public void update(Forum forum) throws BusinessException, PersistenceException;
    public Forum remove(Long questionID, Long discussionSeq) throws PersistenceException;
    public Forum getForumById(Long questionID, Long discussionSeq) throws PersistenceException;
    public List<Forum> listAll() throws PersistenceException;
}
