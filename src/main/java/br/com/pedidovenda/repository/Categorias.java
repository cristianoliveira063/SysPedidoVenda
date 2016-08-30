/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Categoria;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author CRISTIANO
 */
public class Categorias extends BasicRepository<Categoria, Long> {

    private static final long serialVersionUID = 1L;
    @Inject
    private EntityManager em;

    public Categorias() {
        super(Categoria.class);
    }

    public List<Categoria> raizes() {
        return em.createQuery("from Categoria where categoriaPai is null",
                Categoria.class).getResultList();
    }

    public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
        return em.createQuery("from Categoria where categoriaPai = :raiz",
                Categoria.class).setParameter("raiz", categoriaPai).getResultList();
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
