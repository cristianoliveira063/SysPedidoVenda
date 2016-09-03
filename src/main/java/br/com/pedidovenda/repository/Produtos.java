/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.modelFilter.ProdutoFilter;
import br.com.pedidovenda.service.NegocioException;
import br.com.pedidovenda.util.jpa.Transactional;
import br.com.pedidovenda.util.validation.Validador;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
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

    @Transactional
    @Override
    public void remove(Produto produto) {
        try {
            super.remove(produto);
            em.flush();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw new NegocioException("Produto não pode ser excluído.");
        }
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
        r.fetch("categoria");
        criteriaQuery.select(r);
        if (filter.getFilter().isAscendente()
                && Validador.isStringValida(filter.getFilter().getPropriedadeOrdenacao())) {
            criteriaQuery.orderBy(builder.asc(r.get(filter.getFilter().getPropriedadeOrdenacao())));
        } else if (Validador.isStringValida(filter.getFilter().getPropriedadeOrdenacao())) {
            criteriaQuery.orderBy(builder.desc(r.get(filter.getFilter().getPropriedadeOrdenacao())));
        }
        criteriaQuery.where(getPredicates(filter, builder, r).toArray(new Predicate[0]));
        TypedQuery<Produto> typedQuery = criarConsulta(em, filter, criteriaQuery);
        typedQuery.setFirstResult(filter.getFilter().getPrimeiroRegistro());
        typedQuery.setMaxResults(filter.getFilter().getQuantidadeRegistros());
        return typedQuery.getResultList();
    }

    public int quantidadeFiltrados(ProdutoFilter filter) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<Produto> rt = cq.from(Produto.class);
        cq.select(builder.count(rt));
        cq.where(getPredicates(filter, builder, rt).toArray(new Predicate[0]));
        Query q = criarConsulta(em, filter, cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Predicate> getPredicates(ProdutoFilter filter, CriteriaBuilder builder, Root r) {
        List<Predicate> predicates = new ArrayList<>();
        if (Validador.isStringValida(filter.getSku())) {
            ParameterExpression<String> paramSKu = builder.parameter(String.class, "sku");
            predicates.add(builder.equal(r.get("sku"), paramSKu));
        }
        if (Validador.isStringValida(filter.getNome())) {
            ParameterExpression<String> paramNome = builder.parameter(String.class, "nome");
            predicates.add(builder.like(r.<String>get("nome"), paramNome));
        }
        if (Validador.isObjectValido(filter.getCategoria())) {
            ParameterExpression<Categoria> paramCategoria = builder.parameter(Categoria.class, "categoria");
            predicates.add(builder.equal(r.get("categoria"), paramCategoria));
        }
        return predicates;
    }

    public TypedQuery criarConsulta(EntityManager em, ProdutoFilter filter, CriteriaQuery criteriaQuery) {
        TypedQuery<Produto> typedQuery = em.createQuery(criteriaQuery);
        if (Validador.isStringValida(filter.getSku())) {
            typedQuery.setParameter("sku", filter.getSku());
        }
        if (Validador.isStringValida(filter.getNome())) {
            typedQuery.setParameter("nome", "%" + filter.getNome() + "%");
        }
        if (Validador.isObjectValido(filter.getCategoria())) {
            typedQuery.setParameter("categoria", filter.getCategoria());
        }
        return typedQuery;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
