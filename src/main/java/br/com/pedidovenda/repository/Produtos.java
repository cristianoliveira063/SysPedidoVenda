/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.modelFilter.ProdutoFilter;
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
public class Produtos extends BasicRepository<Produto, Long> {

    private static final long serialVersionUID = 1L;
    @Inject
    private EntityManager em;

    public Produtos() {
        super(Produto.class);
    }

    public Produto porSku(String sku) {
        try {
            return em.createQuery("from Produto where upper(sku) = :sku", Produto.class)
                    .setParameter("sku", sku.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Produto> filtrar(ProdutoFilter filter) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = builder.createQuery(Produto.class);
        Root<Produto> r = criteriaQuery.from(Produto.class);
        criteriaQuery.select(r);
        if (filter.getFilter().isAscendente()
                && Validador.isStringValida(filter.getFilter().getPropriedadeOrdenacao())) {
            criteriaQuery.orderBy(builder.asc(r.get(filter.getFilter().getPropriedadeOrdenacao())));
        } else if (Validador.isStringValida(filter.getFilter().getPropriedadeOrdenacao())) {
            criteriaQuery.orderBy(builder.desc(r.get(filter.getFilter().getPropriedadeOrdenacao())));
        }
        List<Predicate> predicates = new ArrayList<>();
        if (Validador.isStringValida(filter.getSku())) {
            ParameterExpression<String> paramSKu = builder.parameter(String.class,"sku");
            predicates.add(builder.like(r.<String>get("sku"), paramSKu));
        }
        if (Validador.isStringValida(filter.getNome())) {
            ParameterExpression<String> paramNome = builder.parameter(String.class,"nome");
            predicates.add(builder.like(r.<String>get("nome"), paramNome));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Produto> typedQuery = em.createQuery(criteriaQuery);
        if (Validador.isStringValida(filter.getSku())) {
            typedQuery.setParameter("sku", filter.getSku());
        }
        if (Validador.isStringValida(filter.getNome())) {
            typedQuery.setParameter("nome", "%" + filter.getNome() + "%");
        }
        typedQuery.setFirstResult(filter.getFilter().getPrimeiroRegistro());
        typedQuery.setMaxResults(filter.getFilter().getQuantidadeRegistros());
        return typedQuery.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
