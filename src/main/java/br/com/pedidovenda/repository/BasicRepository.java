/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author CRISTIANO
 * @param <T>
 * @param <K>
 */
public abstract class BasicRepository<T,K> implements Serializable{
     
    private static final long serialVersionUID = 1L;
    final  Class<T> clazz;

    public BasicRepository(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    protected  abstract EntityManager getEntityManager();
    
    public T adicionar(T entity){     
        return getEntityManager().merge(entity);
    }
    
    public T pesquisarPorID(K id){       
        return getEntityManager().find(clazz, id);
    }
     public CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    public List<T> pesquisar(String propriedade, String valor) {
        CriteriaQuery<T> criteriaQuery = getCriteriaBuilder().createQuery(clazz);
        Root<T> r = criteriaQuery.from(clazz);
        criteriaQuery.select(r);
        criteriaQuery.where(getCriteriaBuilder().like(r.<String>get(propriedade), "%" + valor + "%"));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
    
    public List<T> listar() {
        return getEntityManager().createQuery("select t from " + getClazz().getSimpleName() + " t").getResultList();
    }
    
    public Number count(){       
        Number num = getEntityManager().createQuery("select COUNT(t) from " + getClazz().getSimpleName()  + " t ",Number.class).getSingleResult();    
         return  num ;
        
    }
    
    public Class<T> getClazz() {
        return clazz;
    }
    
        
}
