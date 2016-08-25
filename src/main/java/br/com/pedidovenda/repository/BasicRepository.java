/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author CRISTIANO
 * @param <T>
 * @param <K>
 */
public abstract class BasicRepository<T,K> implements Serializable{
     
    private static final long serialVersionUID = 1L;
    private Class<T> clazz;

    public BasicRepository(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    protected  abstract EntityManager getEntityManager();
    
    public T pesquisarPorID(K id){       
        return getEntityManager().find(clazz, id);
    }
   
     @SuppressWarnings("JPQLValidation")
    public List<T> listar() {
        return getEntityManager().createQuery("select t from " + getClazz().getSimpleName() + " t").getResultList();
    }

      
    public Class<T> getClazz() {
        return clazz;
    }
    
        
}
