/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.model.TipoPessoa;
import br.com.pedidovenda.modelFilter.ClienteFilter;
import br.com.pedidovenda.modelFilter.ProdutoFilter;
import br.com.pedidovenda.util.validator.Validador;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class Clientes extends BasicRepository<Cliente, Long> {

    private static final long serialVersionUID = 1L;
    @Inject
    private EntityManager em;

    public Clientes() {
        super(Cliente.class);
    }

    public Cliente porNumeroDocumento(String documento, TipoPessoa tipo) {
        try {
            return getEntityManager().createQuery("from Cliente where documentoReceitaFederal = :documento and tipo = :tipo",
                    Cliente.class).setParameter("documento", documento)
                    .setParameter("tipo", tipo).getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;

        }

    }

    public List<Cliente> filtrar(ClienteFilter clienteFilter) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);
        Root<Cliente> r = criteriaQuery.from(Cliente.class);
        criteriaQuery.select(r);
        if (clienteFilter.getFilter().isAscendente()
                && Validador.isStringValida(clienteFilter.getFilter().getPropriedadeOrdenacao())) {
            criteriaQuery.orderBy(builder.asc(r.get(clienteFilter.getFilter().getPropriedadeOrdenacao())));
        } else if (Validador.isStringValida(clienteFilter.getFilter().getPropriedadeOrdenacao())) {
            criteriaQuery.orderBy(builder.desc(r.get(clienteFilter.getFilter().getPropriedadeOrdenacao())));
        }
        criteriaQuery.where(getPredicates(clienteFilter, builder, r).toArray(new Predicate[0]));
        TypedQuery<Cliente> typedQuery = criarConsulta(em, clienteFilter, criteriaQuery);
        typedQuery.setFirstResult(clienteFilter.getFilter().getPrimeiroRegistro());
        typedQuery.setMaxResults(clienteFilter.getFilter().getQuantidadeRegistros());
        return typedQuery.getResultList();
    }

    public int quantidadeFiltrados(ClienteFilter filter) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<Cliente> rt = cq.from(Cliente.class);
        cq.select(builder.count(rt));
        cq.where(getPredicates(filter, builder, rt).toArray(new Predicate[0]));
        Query q = criarConsulta(em, filter, cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Predicate> getPredicates(ClienteFilter filter, CriteriaBuilder builder, Root r) {
        List<Predicate> predicates = new ArrayList<>();
        if (Validador.isStringValida(filter.getDocumentoReceitaFederal())) {
            ParameterExpression<String> paramDoc = builder.parameter(String.class, "documentoReceitaFederal");
            predicates.add(builder.equal(r.get("documentoReceitaFederal"), paramDoc));
        }
        if (Validador.isStringValida(filter.getNome())) {
            ParameterExpression<String> paramNome = builder.parameter(String.class, "nome");
            predicates.add(builder.like(r.<String>get("nome"), paramNome));
        }
        return predicates;
    }

    public TypedQuery criarConsulta(EntityManager em, ClienteFilter filter, CriteriaQuery criteriaQuery) {
        TypedQuery<Produto> typedQuery = em.createQuery(criteriaQuery);
        if (Validador.isStringValida(filter.getDocumentoReceitaFederal())) {
            typedQuery.setParameter("documentoReceitaFederal", filter.getDocumentoReceitaFederal());
        }
        if (Validador.isStringValida(filter.getNome())) {
            typedQuery.setParameter("nome", "%" + filter.getNome() + "%");
        }
        return typedQuery;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
