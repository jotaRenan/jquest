/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.CommentaryDAOImpl;
import br.cefetmg.jquest.model.domain.Commentary;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author João Pedro Renan
 */
public class CommentaryManagementImplTest {
    
    private static CommentaryDAOImpl commentDAO;
    private static CommentaryManagementImpl commentManager;
    private Commentary comment;
    
    public CommentaryManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        commentDAO = CommentaryDAOImpl.getInstance();
        commentManager = new CommentaryManagementImpl(commentDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.comment = new Commentary(0L, 0L, 0L, 0L, "MyComment");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of commentaryInsert method, of class CommentaryManagementImpl.
     */
    @Test(expected=BusinessException.class)
    public void testNullCommentaryInsert() throws BusinessException, PersistenceException {
        this.comment = null;
        commentManager.commentaryInsert(comment);
    }
    
    // I have no Idea why this is failing.
    @Test(expected=BusinessException.class)
    public void testEmptyCommentaryInsert() throws BusinessException, PersistenceException {
        comment.setTextCommentary("");
        try {
            commentManager.commentaryInsert(comment);
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("oi");
        }
    }
    /**
     * Test of commentaryUpdate method, of class CommentaryManagementImpl.
     */
    @Test(expected=BusinessException.class)
    public void testCommentaryUpdate() throws Exception {
        this.comment = null;
        commentManager.commentaryUpdate(comment);
    }

    /**
     * Test of commentaryRemove method, of class CommentaryManagementImpl.
     */
    @Test(expected=PersistenceException.class)
    public void testCommentaryRemove() throws Exception {
        Long commentSeq = null;
        commentManager.commentaryRemove(commentSeq);
    }

    /**
     * Test of getcommentaryBySeq method, of class CommentaryManagementImpl.
     */
    @Test(expected=PersistenceException.class)
    public void testGetcommentaryBySeq() throws Exception {
        Long commentSeq = null;
        commentManager.getcommentaryBySeq(commentSeq);
    }

    /**
     * Test of getCommentarysByForumId method, of class CommentaryManagementImpl.
     */
    @Test(expected=PersistenceException.class)
    public void testGetCommentariesByForumId() throws Exception {
        Long forumId = null;
        commentManager.getCommentarysByForumId(forumId);
    }

    /**
     * Test of getAll method, of class CommentaryManagementImpl.
     */
    @Test
    public void testGetAll() throws Exception {
        
    }
    
}
