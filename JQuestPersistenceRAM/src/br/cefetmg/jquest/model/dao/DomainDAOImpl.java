/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Domain;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class DomainDAOImpl implements DomainDAO {

    
    private static DomainDAOImpl domainDAO = null;
    private static HashMap<Long, Domain> domainDB = new HashMap<Long, Domain>();

    private DomainDAOImpl() {
    }

    public static DomainDAOImpl getInstance() {
        if (domainDAO == null) {
            domainDAO = new DomainDAOImpl();
        }
        return domainDAO;
    }

    @Override
    public void insert(Domain domain) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Domain domain) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Domain remove(Long domainId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Domain getDomainById(Long domainId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Domain> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
