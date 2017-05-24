/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.TrueOrFalseAnswerDAOImpl;
import br.cefetmg.jquest.model.domain.TrueOrFalseAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Joao Pedro Renan
 */
public class TrueOrFalseAnswerManagementImplTest {
    private static TrueOrFalseAnswerManagementImpl tofaManagement;
    private static TrueOrFalseAnswerDAOImpl tofaDAO;
    private TrueOrFalseAnswer tofa;
    
    public TrueOrFalseAnswerManagementImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        tofaDAO = TrueOrFalseAnswerDAOImpl.getInstance();
        tofaManagement = new TrueOrFalseAnswerManagementImpl(tofaDAO);
    }
    
    @Before
    public void setUp() {
        this.tofa = new TrueOrFalseAnswer(0L, 0L, 0L, 0L, 0L);
    }
    
    @Test
    public void testTOFANullInsertion() throws PersistenceException, BusinessException {
        try {
            this.tofa = null;
            tofaManagement.insert(tofa);
        } catch (BusinessException | PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "No TrueOrFalseAnswer was informed";
            assertTrue(msgErr.contains(msgEsperada));
        } catch (NullPointerException ex) {
            fail("Insertion of null TrueOrFalseAnswer");
        }
    }
    
    @Test
    public void testTOFANullUpdate() throws PersistenceException, BusinessException {
        try {
            this.tofa = null;
            tofaManagement.update(tofa);
        } catch (BusinessException | PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "No TrueOrFalseAnswer was informed";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null TrueOrFalseAnswer");
    }
    
    @Test
    public void testTOFANullOptionSeqRemoval() throws PersistenceException, BusinessException {
        try {
            this.tofa.setOptionSeq(null);
            tofaManagement.remove(tofa.getOptionSeq());
        } catch (BusinessException | PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "No Answer ID was informed";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null TrueOrFalseAnswer ID");
    }
}
