package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.DomainDAO;
import br.cefetmg.jquest.model.dao.DomainDAOImpl;
import br.cefetmg.jquest.model.domain.Domain;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paula Ribeiro
 */
public class DomainManagementImplTest {
    private static DomainManagement domainManagement;
    private static DomainDAO domainDAO;
    private Domain domain;
    
    public DomainManagementImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        domainDAO = DomainDAOImpl.getInstance();
        domainManagement = new DomainManagementImpl(domainDAO);
    }
    
    @Before
    public void setUp() {
        this.domain = new Domain();
        this.domain.setName("Teste");
        this.domain.setDescription("Teste");
    }
    
    @Test
    public void testDomainInsertNull() {
        domain = null;
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Null domain registered");
    }
    
    @Test
    public void testDomainInsertNullName() {
        domain.setName(null);
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Domain with null name registered");
    }
    
    @Test
    public void testDomainInsertNullDescription() {
        domain.setDescription(null);
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Domain with null description registered");
    }
    
    @Test
    public void testDomainInsertCorrect() {
        Long id = -1L;
        try {
            id = domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            fail("Domain failed to be registered");
            return;
        }
        if (id == -1L) {
            fail("Domain failed to be registered");
            return;
        }
        
        //removes registered domain
        try {
            domainManagement.domainRemove(id);
        } catch (PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @Test
    public void testDomainUpdateNull() {
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        domain = null;
        try {
            domainManagement.domainUpdate(domain);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Domain updated to null");
    }
    
    @Test
    public void testDomainUpdateNullName() {
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        domain.setName(null);
        try {
            domainManagement.domainUpdate(domain);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Domain updated with null name");
    }
    
    @Test
    public void testDomainUpdateNullDescription() {
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        domain.setDescription(null);
        try {
            domainManagement.domainUpdate(domain);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Domain updated with null description");
    }
    
    @Test
    public void testDomainUpdateNullId() {
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        domain.setId(null);
        try {
            domainManagement.domainUpdate(domain);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Tried to update a domain with a null id");
    }
    
    @Test
    public void testDomainUpdateCorrect() {
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        domain.setName("Update test");
        try {
            domainManagement.domainUpdate(domain);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update domain");
            return;
        }
        try {
            if (!domainManagement.getDomainById(domain.getId()).getName().equals("Update test")) {
                fail("Failed to update domain");
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDomainRemove() {
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            domainManagement.domainRemove(domain.getId());
        } catch (PersistenceException ex) {
            fail("Failed to remove domain");
        }
    }
    
    @Test
    public void testGetModuleById() {
        try {
            domainManagement.domainInsert(domain);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Domain domainTest;
        try {
            domainTest = domainManagement.getDomainById(domain.getId());
        } catch (PersistenceException ex) {
            fail("Failed to get domain by id");
            return;
        }
        if (!domainTest.equals(domain)) {
            fail("Failed to get domain by id");
        }
    }
    
    @Test
    public void testGetAll() {
        Domain domain2 = domain;
        List<Domain> list;
        try {
            domainManagement.domainInsert(domain);
            domainManagement.domainInsert(domain2);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list = domainManagement.getAll();
        } catch (PersistenceException ex) {
            fail("Failed to get all domains");
            return;
        }
        if(list.isEmpty()) {
            fail("Failed to get all domains correctly");
        }
    }
}
