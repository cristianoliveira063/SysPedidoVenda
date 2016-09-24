/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Cliente;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author CRISTIANO
 */
@Named
@ViewScoped
public class PesquisarClienteBean implements Serializable{    
    private static final long serialVersionUID = 1L;
    private LazyDataModel<Cliente>lazyClienteDataModel;
    
    @PostConstruct
    public void init(){
        
        
    }
    
    public LazyDataModel<Cliente> pesquisar(){
        
        
        return null;
    }

    public LazyDataModel<Cliente> getLazyClienteDataModel() {
        return lazyClienteDataModel;
    }
      
}
