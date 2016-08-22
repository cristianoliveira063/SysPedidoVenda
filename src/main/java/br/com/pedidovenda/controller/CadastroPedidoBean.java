/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Cristiano Aparecido
 */
@Named
@ViewScoped
public class CadastroPedidoBean implements  Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private List<Integer> itens = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        
        itens.add(1);
        
    }

    public List<Integer> getItens() {
        return itens;
    }
    
    
    
}
