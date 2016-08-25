/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Categoria;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author 016255421
 */
public class Categorias extends BasicRepository<Categoria,Long>{

    private static final long serialVersionUID = 1L;
    @Inject
    private EntityManager em;

    public Categorias() {
        super(Categoria.class);
    }
         
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
