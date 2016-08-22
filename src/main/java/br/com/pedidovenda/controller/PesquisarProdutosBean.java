/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author 016255421
 */
@Named
@ViewScoped
public class PesquisarProdutosBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private List<Integer> produtosFiltrados;
	
	public PesquisarProdutosBean() {
		produtosFiltrados = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			produtosFiltrados.add(i);
		}
	}

	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}
    
    
}
