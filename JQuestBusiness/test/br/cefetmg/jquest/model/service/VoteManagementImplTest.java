/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.VoteDAOImpl;
import br.cefetmg.jquest.model.domain.Vote;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author Breno Mariz
 */
public class VoteManagementImplTest {
    private static VoteManagementImpl VoteManag;
    private static VoteDAOImpl VoteDAO;
    private Vote vote;
    
    public VoteManagementImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        VoteDAO = VoteDAOImpl.getInstance();
        VoteManag = new VoteManagementImpl(VoteDAO);
    }
    
    @Before
    public void setUp() {
        this.vote = new Vote(0L, 0L, 0L, 0L, 0L, true);
    }
    
    @Test
    public void testVoteNullInsertion() throws PersistenceException, BusinessException {
        try {
            this.vote = null;
            VoteManag.voteInsert(vote);
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "The object vote cannot be null.";
            assertTrue(msgErr.contains(msgEsperada));
        } catch (NullPointerException ex) {
            fail("Insertion of null vote");
        }
    }
    
    @Test
    public void testVoteInsertNullCommentarySeq() {
        vote.setCommentarySeq(null);
        try {
            VoteManag.voteInsert(vote);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Could insert vote with null CommentarySeq");
    }
    
    @Test
    public void testVoteInsertNullDiscussionSeq(){
        vote.setDiscussionSeq(null);
        try {
            VoteManag.voteInsert(vote);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Could insert vote with null DiscussionSeq");
    }
    
    @Test
    public void testVoteInsertNullQuestionId() {
        vote.setQuestionId(null);
        try {
            VoteManag.voteInsert(vote);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Could insert vote with null QuestionId");
    }
    
    @Test
    public void testVoteInsertCorrect() throws PersistenceException {
        try {
            VoteManag.voteInsert(vote);
        } catch (BusinessException | PersistenceException ex) {
            fail("Couldn't insert vote");
        }
        VoteManag.voteRemove(vote.getQuestionId(), vote.getDiscussionSeq(), vote.getCommentarySeq(), vote.getUserId());
    
    }
    
    @Test
    public void testVoteNullUpdate () throws PersistenceException, BusinessException {
            try {
                this.vote = null;
                VoteManag.voteUpdate(vote);
            } catch (BusinessException | PersistenceException ex) {
                ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
                String msgEsperada = "The object vote cannot be null.";
                assertTrue(msgErr.contains(msgEsperada));
                return;
            }
            fail("Update of null vote didnt throw an exception");
    }
    
    @Test
    public void testVoteUpdateNullCommentarySeq() throws BusinessException, PersistenceException {
        VoteManag.voteInsert(vote);
        vote.setCommentarySeq(null);
        try {
            VoteManag.voteUpdate(vote);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Could update vote with null CommentarySeq");
    }
    
    @Test
    public void testVoteUpdateNullDiscussionSeq() throws BusinessException, PersistenceException{
        VoteManag.voteInsert(vote);
        vote.setDiscussionSeq(null);
        try {
            VoteManag.voteUpdate(vote);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Could update vote with null DiscussionSeq");
    }
    
    @Test
    public void testVoteUpdateNullQuestionId() throws BusinessException, PersistenceException {
        VoteManag.voteInsert(vote);
        vote.setQuestionId(null);
        try {
            VoteManag.voteUpdate(vote);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Could insert vote with null QuestionId");
    }
    
    @Test
    public void testVoteUpdateCorrect() throws PersistenceException, BusinessException {
        VoteManag.voteInsert(vote);
        try {
            VoteManag.voteUpdate(vote);
        } catch (BusinessException | PersistenceException ex) {
            fail("Couldn't insert vote");
        }
    }
    
    @Test
    public void testVoteNullIDRemoval() throws PersistenceException, BusinessException {
        try {
            this.vote.setVoteID(null);
            VoteManag.voteRemove(vote.getCommentarySeq(), vote.getUserId());
        } catch (PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "Vote can´t be null.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null vote ID");
    }
    
    @Test
    public void testVoteRemove() {
        try {
            VoteManag.voteInsert(vote);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(VoteManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            VoteManag.voteRemove(vote.getCommentarySeq(), vote.getUserId());
        } catch (PersistenceException ex) {
            fail("Failed to remove VOTE");
        }
    }
    
    @Test
    public void testGetModuleById() {
        try {
            VoteManag.voteInsert(vote);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(VoteManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Vote voteTest;
        try {
            voteTest = VoteManag.getVoteById(vote.getCommentarySeq(), vote.getUserId());
        } catch (PersistenceException ex) {
            fail("Failed to get vote by id");
            return;
        }
        if (!voteTest.equals(vote)) {
            fail("Failed to get vote by id");
        }
    }
    
    @Test
    public void testGetAll() {
        Vote vote2 = vote;
        List<Vote> list;
        try {
            VoteManag.voteInsert(vote);
            VoteManag.voteInsert(vote2);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(VoteManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list = VoteManag.listAll();
        } catch (PersistenceException ex) {
            fail("Failed to get all domains");
            return;
        }
        if(list.isEmpty()) {
            fail("Failed to get all domains correctly");
        }
    }
       
}
