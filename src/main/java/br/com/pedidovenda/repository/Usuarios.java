/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Usuario;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author CRISTIANO
 */
public class Usuarios extends BasicRepository<Usuario, Long> {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    public Usuarios() {
        super(Usuario.class);
    }

    public Usuario porEmail(String email) {
        try {
            return em.createQuery("from Usuario where email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
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
