/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Produto;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author CRISTIANO
 */
@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Inject
    private Produto produto;

    public Produto getProduto() {
        return produto;
    }
     
    
}
