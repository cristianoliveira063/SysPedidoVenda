/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Grupo;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author CRISTIANO
 */
public class Grupos extends BasicRepository<Grupo,Long>{

    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;

    public Grupos() {
        super(Grupo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
