/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Commentary;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Haddad
 */
public interface CommentaryDAO {
    public Long insert(Commentary commentary) throws PersistenceException;
    public boolean update(Commentary commentary) throws PersistenceException;
    public boolean remove(Long commentarySeq) throws PersistenceException;
    public Commentary getCommentaryBySeq(Long commentarySeq) throws PersistenceException;
    public List<Commentary> getCommentarysByForumId(Long forumId) throws PersistenceException;
    public List<Commentary> listAll() throws PersistenceException; 
}
