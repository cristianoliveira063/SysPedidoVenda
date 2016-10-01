/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.Pedido;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author CRISTIANO
 */
public class Pedidos extends BasicRepository<Pedido, Long> {

    private static final long serialVersionUID = 1L;
    @Inject
    private EntityManager em;

    public Pedidos() {
        super(Pedido.class);
    }
    
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}