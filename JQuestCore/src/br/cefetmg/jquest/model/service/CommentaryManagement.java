/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.Commentary;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Haddad
 */
public interface CommentaryManagement {
    public Long commentaryInsert(Commentary commentary) throws BusinessException, PersistenceException;
    public void commentaryUpdate(Commentary commentary) throws BusinessException, PersistenceException;
    public void commentaryRemove(Long commentaryId) throws PersistenceException;
    public Commentary getcommentaryBySeq(Long commentarySeq) throws PersistenceException;
    public List<Commentary> getCommentarysByForumId(Long forumId) throws PersistenceException;
    public List<Commentary> getAll() throws PersistenceException;  
}
