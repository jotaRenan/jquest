/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Commentary;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author GABRIEL HADDAD
 */
public class CommentaryDAOImpl implements CommentaryDAO {
     private static CommentaryDAOImpl commentaryDAO = null;
    private static HashMap<Long, Commentary> commentaryDB = new HashMap<Long, Commentary>();
    private static long commentaryCount = 0; 
    
    public CommentaryDAOImpl() {}
    
    public static CommentaryDAOImpl getInstance() {
        if (commentaryDAO == null) {
            commentaryDAO = new CommentaryDAOImpl();
        }
        return commentaryDAO;
    }
    
    @Override
    synchronized public Long insert(Commentary commentary) throws PersistenceException {
       if (commentary == null) {
             throw new PersistenceException("Commentary cannot be null");           
        }
        Long commentarySeq = commentary.getCommentarySeq();
        
        if (commentarySeq!= null && commentaryDB.containsKey(commentarySeq)) {
            throw new PersistenceException("Duplicated key");
        }
        commentarySeq = ++commentaryCount;
        commentary.setCommentarySeq(commentarySeq);
        commentaryDB.put(commentarySeq, commentary);
        
        return commentarySeq;
    }

    @Override
    synchronized public void update(Commentary commentary) throws PersistenceException {
       if (commentary == null) {
            throw new PersistenceException("Commentary cannot be null");
        }
        Long commentarySeq = commentary.getCommentarySeq();
        if (commentarySeq == null) {
            throw new PersistenceException("Commentary sequence cannot be null");
        }
        if (commentaryDB.containsKey(commentarySeq)) {
            throw new PersistenceException("Commentary with sequence " + commentary.getCommentarySeq() + " is not persisted");
        }
        commentaryDB.replace(commentarySeq, commentary);
    }

    @Override
    synchronized public Commentary remove(Long commentarySeq) throws PersistenceException {
         if (commentarySeq == null) {
            throw new PersistenceException("Commentary sequence cannot be null");
        }
        if (!commentaryDB.containsKey(commentarySeq)) {
            throw new PersistenceException("Commentary with sequence " + commentarySeq + " is not persisted");
        }
        return commentaryDB.remove(commentarySeq);
    }

    @Override
    synchronized public Commentary getCommentaryBySeq(Long commentarySeq) throws PersistenceException {
        if (commentarySeq == null) {
            throw new PersistenceException("Commentary sequence cannot be null");
        }
        if (!commentaryDB.containsKey(commentarySeq)) {
            throw new PersistenceException("Commentary with sequence " + commentarySeq + " is not persisted");
        }
        return commentaryDB.get(commentarySeq);
    }

    @Override
    public List<Commentary> listAll() throws PersistenceException {
        List<Commentary> commentaryList = new ArrayList<>();
        Iterator<Commentary> it = commentaryDB.values().iterator();
        while (it.hasNext()) {
            commentaryList.add(it.next());
        }
        return commentaryList;
    }
    
}
