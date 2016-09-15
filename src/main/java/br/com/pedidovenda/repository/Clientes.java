/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.TipoPessoa;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author CRISTIANO
 */
public class Clientes extends BasicRepository<Cliente, Long> {

    private static final long serialVersionUID = 1L;
    @Inject
    private EntityManager em;

    public Clientes() {
        super(Cliente.class);
    }

    public Cliente porNumeroDocumento(String documento, TipoPessoa tipo) {
        try {
            return getEntityManager().createQuery("from Cliente where email = :email and tipo = :tipo",
                    Cliente.class).setParameter("email", documento)
                    .setParameter("tipo", tipo).getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;

        }

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
