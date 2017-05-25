/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.ForumDAOImpl;
import br.cefetmg.jquest.model.domain.Forum;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

    /**
     * Test of forumInsert method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumInsertNull() throws Exception {
        forum = null;
        forumManager.forumInsert(forum);
    }
    
    /**
     * Test of forumInsert method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumInsertNullQuestionId() throws Exception {
        forum.setQuestionId(null);
        forumManager.forumInsert(forum);
    }
    
    /**
     * Test of forumInsert method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumInsertNullName() throws Exception {
        forum.setName(null);
        forumManager.forumInsert(forum);
    }
    
    /**
     * Test of forumInsert method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumInsertEmptyName() throws Exception {
        forum.setName("");
        forumManager.forumInsert(forum);
    }
    
    /**
     * Test of forumInsert method, of class ForumManagementImpl.
     */
    @Test
    public void testForumInsert() {
        try {
            forumManager.forumInsert(forum);
        } catch (BusinessException | PersistenceException ex) {
            fail("Couldn't insert forum correctly");
        }
    }

    /**
     * Test of forumUpdate method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumUpdateNull() throws Exception {
        forum = null;
        forumManager.forumUpdate(forum);
    }

    /**
     * Test of forumUpdate method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumUpdateNullQuestionId() throws Exception {
        forum.setQuestionId(null);
        forumManager.forumUpdate(forum);
    }
    
    /**
     * Test of forumUpdate method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumUpdateNullName() throws Exception {
        forum.setName(null);
        forumManager.forumUpdate(forum);
    }
    
    /**
     * Test of forumUpdate method, of class ForumManagementImpl.
     */
    @Test(expected = BusinessException.class)
    public void testForumUpdateEmptyName() throws Exception {
        forum.setName("");
        forumManager.forumUpdate(forum);
    }
    
    /**
     * Test of forumUpdate method, of class ForumManagementImpl.
     */
    @Test
    public void testForumUpdate() throws PersistenceException, BusinessException {
        forum.setName("Update test");
        forumManager.forumInsert(forum);
        try {
            forumManager.forumUpdate(forum);
        } catch (BusinessException | PersistenceException ex) {
            fail("Couldn't insert forum correctly 1");
            return;
        }
        if (!forum.getName().equals("Update test")) {
            fail("Couldn't insert forum correctly");
        }
        forumManager.forumRemove(forum.getQuestionId(), forum.getDiscussionSeq());
    }
    
    /**
     * Test of forumRemove method, of class ForumManagementImpl.
     */
    @Test(expected = PersistenceException.class)
    public void testForumRemoveNullDiscussionSeq() throws Exception {
        forum.setDiscussionSeq(null);
        forumManager.forumRemove(forum.getQuestionId(), forum.getDiscussionSeq());
    }

    @Test(expected = PersistenceException.class)
    public void testForumRemoveNullQuestionId() throws Exception {
        forum.setDiscussionSeq(null);
        forumManager.forumRemove(forum.getQuestionId(), forum.getDiscussionSeq());
    }
    
    /**
     * Test of forumRemove method, of class ForumManagementImpl.
     */
    public void testForumRemove() {
        try {
            forumManager.forumInsert(forum);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(ModuleManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            forumManager.forumRemove(forum.getQuestionId(), forum.getDiscussionSeq());
        } catch (PersistenceException ex) {
            fail("Failed to remove module");
        }
    }
    
    /**
     * Test of getForumById method, of class ForumManagementImpl.
     */
    @Test(expected = PersistenceException.class)
    public void testGetForumByIdNullQuestionId() throws Exception {
        forum.setUserId(null);
        forumManager.getForumById(forum.getQuestionId(), forum.getDiscussionSeq());
    }
    
    /**
     * Test of getForumById method, of class ForumManagementImpl.
     */
    @Test(expected = PersistenceException.class)
    public void testGetForumByIdNullDiscussionSeq() throws Exception {
        forum.setDiscussionSeq(null);
        forumManager.getForumById(forum.getQuestionId(), forum.getDiscussionSeq());
    }
    
    /**
     * Test of getForumById method, of class ForumManagementImpl.
     */
    @Test
    public void testGetForumById() {
        try {
            forumManager.getForumById(forum.getQuestionId(), forum.getDiscussionSeq());
        } catch (PersistenceException ex) {
            fail("Failed to get forum by id");
        }
    }

    /**
     * Test of getAll method, of class ForumManagementImpl.
     */
    @Test
    public void testGetAll() throws Exception {
        forumManager.forumInsert(forum);
        List<Forum> list = forumManager.getAll();
        if (list.isEmpty()) {
            fail("Failed to get all forums correctly");
        }
        
    }
    
}
