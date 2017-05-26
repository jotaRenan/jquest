/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.VoteDAO;
import br.cefetmg.jquest.model.dao.VoteDAOImpl;
import br.cefetmg.jquest.model.domain.Vote;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void testVoteNullIDRemoval() throws PersistenceException, BusinessException {
        try {
            this.vote.setVoteID(null);
            VoteManag.voteRemove(vote.getVoteID());
        } catch (PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "Vote can´t be null.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null vote ID");
    }
       
}
