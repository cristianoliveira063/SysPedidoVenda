/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Produto;
import javax.persistence.EntityManager;

/**
 *
 * @author CRISTIANO
 */
public class Produtos extends BasicRepository<Produto,Long>{

    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public Produtos() {
        super(Produto.class);
    }

    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
    
}
