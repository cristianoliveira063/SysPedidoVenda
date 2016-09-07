/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Pedido;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Cristiano Aparecido
 */
@Named
@ViewScoped
public class CadastroPedidoBean implements  Serializable{
    
    private static final long serialVersionUID = 1L;
    @Inject
    private Pedido pedido; 

    public CadastroPedidoBean() {
     
    }
    
       
    @PostConstruct
    public void init(){
              
    }
    public Pedido getPedido() {
        return pedido;
    }
    
     
    
}
