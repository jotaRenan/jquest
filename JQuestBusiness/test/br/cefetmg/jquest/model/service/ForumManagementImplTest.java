/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.ForumDAO;
import br.cefetmg.jquest.model.dao.ForumDAOImpl;
import br.cefetmg.jquest.model.domain.Forum;
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
public class ForumManagementImplTest {
    
    private static ForumDAOImpl forumDAO;
    private static ForumManagementImpl forumManager;
    private Forum forum;
    
    public ForumManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        forumDAO = ForumDAOImpl.getInstance();
        forumManager = new ForumManagementImpl(forumDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        forum = new Forum(0L, 0L, 0L, "Test", "Discussion about tests");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setDAO method, of class ForumManagementImpl.
     */
    @Test
    public void testSetDAO() {
        System.out.println("setDAO");
        ForumDAO DAO = null;
        ForumManagementImpl instance = new ForumManagementImpl();
        instance.setDAO(DAO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of forumInsert method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumInsertNull() throws Exception {
        forum = null;
        forumManager.forumInsert(forum);
    }

    /**
     * Test of forumUpdate method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumUpdate() throws Exception {
        forum = null;
        forumManager.forumUpdate(forum);
    }

    /**
     * Test of forumRemove method, of class ForumManagementImpl.
     */
    @Test(expected = PersistenceException.class)
    public void testForumRemoveNullDiscussionSeq() throws Exception {
        forum.setDiscussionSeq(null);
        forumManager.forumRemove(forum.getDiscussionSeq());
    }

    @Test(expected = PersistenceException.class)
    public void testForumRemoveNullQuestionId() throws Exception {
        forum.setDiscussionSeq(null);
        forumManager.forumRemove(forum.getDiscussionSeq());
    }
    
    /**
     * Test of getForumById method, of class ForumManagementImpl.
     */
    @Test(expected = PersistenceException.class)
    public void testGetForumByIdNullQuestionId() throws Exception {
        forum.setUserId(null);
        forumManager.getForumById(forum.getDiscussionSeq());
    }
    
    /**
     * Test of getForumById method, of class ForumManagementImpl.
     */
    @Test(expected = PersistenceException.class)
    public void testGetForumByIdNullDiscussionSeq() throws Exception {
        forum.setDiscussionSeq(null);
        forumManager.getForumById(forum.getDiscussionSeq());
    }

    /**
     * Test of getAll method, of class ForumManagementImpl.
     */
    @Test
    public void testGetAll() throws Exception {
        
    }
    
}
