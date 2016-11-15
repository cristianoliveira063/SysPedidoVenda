/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.modelFilter.Filter;
import br.com.pedidovenda.modelFilter.PedidoFilter;
import br.com.pedidovenda.util.validator.Validador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
public class Pedidos extends BasicRepository<Pedido, Long> {

    private static final long serialVersionUID = 1L;
    @Inject
    private EntityManager em;

    public Pedidos() {
        super(Pedido.class);
    }

    public List<Pedido> filtrar(PedidoFilter pedidoFilter) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = builder.createQuery(clazz);
        Root<Pedido> r = criteriaQuery.from(clazz);
        criteriaQuery.select(r);
        ordenação(pedidoFilter.getFilter(), criteriaQuery, builder, r);
        criteriaQuery.where(getPredicates(pedidoFilter, builder, r).toArray(new Predicate[0]));
        TypedQuery<Pedido> typedQuery = criarConsulta(em, pedidoFilter, criteriaQuery);
        typedQuery.setFirstResult(pedidoFilter.getFilter().getPrimeiroRegistro());
        typedQuery.setMaxResults(pedidoFilter.getFilter().getQuantidadeRegistros());
        return typedQuery.getResultList();
    }

    public void ordenação(Filter filter, CriteriaQuery criteriaQuery, CriteriaBuilder builder, Root r) {
        if (filter.isAscendente()
                && Validador.isStringValida(filter.getPropriedadeOrdenacao())) {
            criteriaQuery.orderBy(builder.asc(r.get(filter.getPropriedadeOrdenacao())));
        } else if (Validador.isStringValida(filter.getPropriedadeOrdenacao())) {
            criteriaQuery.orderBy(builder.desc(r.get(filter.getPropriedadeOrdenacao())));
        }
    }

    public int quantidadeFiltrados(PedidoFilter filter) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<Pedido> rt = cq.from(clazz);
        cq.select(builder.count(rt));
        cq.where(getPredicates(filter, builder, rt).toArray(new Predicate[0]));
        Query q = criarConsulta(em, filter, cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Predicate> getPredicates(PedidoFilter filter, CriteriaBuilder builder, Root r) {
        List<Predicate> predicates = new ArrayList<>();
        if (Validador.isNumericoValido(filter.getNumeroDe())) {
            ParameterExpression<Long> paramNumeroDe = builder.parameter(Long.class, "numeroDe");
            predicates.add(builder.ge(r.get("id"), paramNumeroDe));
        }
        if (Validador.isNumericoValido(filter.getNumeroAte())) {
            ParameterExpression<Long> paramNumeroAte = builder.parameter(Long.class, "numeroAte");
            predicates.add(builder.le(r.get("id"), paramNumeroAte));
        }
        if (Validador.isDataValida(filter.getDataCriacaoDe())) {
            ParameterExpression<Date> paramDataCriacaoDe = builder.parameter(Date.class, "dataCriacaoDe");
            predicates.add(builder.greaterThanOrEqualTo(r.<Date>get("dataCriacao"), paramDataCriacaoDe));
        }
        if (Validador.isDataValida(filter.getDataCriacaoAte())) {
            ParameterExpression<Date> paramDataCriacaoAte = builder.parameter(Date.class, "dataCriacaoAte");
            predicates.add(builder.lessThanOrEqualTo(r.<Date>get("dataCriacao"), paramDataCriacaoAte));
        }
        if (Validador.isStringValida(filter.getNomeVendedor())) {
            ParameterExpression<String> paramNomeVendedor = builder.parameter(String.class, "nomeVendedor");
            predicates.add(builder.like(r.<String>get("vendedor").get("nome"), paramNomeVendedor));
        }
        if (Validador.isStringValida(filter.getNomeCliente())) {
            ParameterExpression<String> paramNomeCliente = builder.parameter(String.class, "nomeCliente");
            predicates.add(builder.like(r.<String>get("cliente").get("nome"), paramNomeCliente));
        }
        if (Validador.isArrayValido(filter.getStatus())) {
           // ParameterExpression<Collection>paramStatusPedido = builder.parameter(Collection.class,"statusPedido");
             //Predicates.add(builder.in());
        }

        return predicates;
    }

    public TypedQuery criarConsulta(EntityManager em, PedidoFilter filter, CriteriaQuery criteriaQuery) {
        TypedQuery<Pedido> typedQuery = em.createQuery(criteriaQuery);
        if (Validador.isNumericoValido(filter.getNumeroDe())) {
            typedQuery.setParameter("numeroDe", filter.getNumeroDe());
        }
        if (Validador.isNumericoValido(filter.getNumeroAte())) {
            typedQuery.setParameter("numeroAte", filter.getNumeroAte());
        }
        if (Validador.isDataValida(filter.getDataCriacaoDe())) {
            typedQuery.setParameter("dataCriacaoDe", filter.getDataCriacaoDe());
        }
        if (Validador.isDataValida(filter.getDataCriacaoAte())) {
            typedQuery.setParameter("dataCriacaoAte", filter.getDataCriacaoAte());
        }
        if (Validador.isStringValida(filter.getNomeVendedor())) {
            typedQuery.setParameter("nomeVendedor", "%" + filter.getNomeVendedor()+ "%");
        }
        if (Validador.isStringValida(filter.getNomeCliente())) {
            typedQuery.setParameter("nomeCliente",  "%" + filter.getNomeCliente()+ "%");
        }
         if (Validador.isArrayValido(filter.getStatus())) {
            typedQuery.setParameter("statusPedido", Arrays.asList(filter.getStatus()));
        }

        return typedQuery;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
