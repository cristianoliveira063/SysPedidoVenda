/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.modelFilter.UsuarioFilter;
import br.com.pedidovenda.util.validation.Validador;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    public List<Usuario> filtrar(UsuarioFilter usuarioFilter) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(clazz);
        Root<Usuario> r = criteriaQuery.from(clazz);
        criteriaQuery.select(r);
        List<Predicate> predicates = new ArrayList<>();
        if (Validador.isStringValida(usuarioFilter.getNome())) {
            ParameterExpression<String> paramNome = builder.parameter(String.class, "nome");
            predicates.add(builder.like(r.<String>get("nome"), paramNome));
        }
        if (Validador.isEmailValido(usuarioFilter.getEmail())) {
            ParameterExpression<String> paramEmail = builder.parameter(String.class, "email");
            predicates.add(builder.equal(r.get("email"), paramEmail));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Usuario> typedQuery = em.createQuery(criteriaQuery);
        if (Validador.isStringValida(usuarioFilter.getNome())) {
            typedQuery.setParameter("nome", "%" + usuarioFilter.getNome() + "%");
        }
        if (Validador.isEmailValido(usuarioFilter.getEmail())) {
            typedQuery.setParameter("email", usuarioFilter.getEmail());
        }
        return typedQuery.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
