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
    public void update(Commentary commentary) throws PersistenceException;
    public Commentary remove(Long commentaryId) throws PersistenceException;
    public Commentary getCommentaryById(Long commentaryId) throws PersistenceException;
    public List<Commentary> listAll() throws PersistenceException; 
}
