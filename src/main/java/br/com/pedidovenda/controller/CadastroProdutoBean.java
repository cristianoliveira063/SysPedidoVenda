/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Categorias;
import br.com.pedidovenda.util.jsf.MessageView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CRISTIANO
 */
@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Produto produto;
    @Inject
    private Categorias categorias;
    @NotNull
    private Categoria categoriaPai;
    private List<Categoria> categoriaRaizes;
    private List<Categoria> subcategorias;

    @PostConstruct
    public void init() {
        categoriaRaizes = categorias.raizes();
    }

    public void carregarSubcategorias() {
        subcategorias = categorias.subcategoriasDe(categoriaPai);
    }

    public void salvar() {   
        reset();
        MessageView.info("Cadastro realizado com sucesso.");  
    }
    private void reset(){  
       produto = new Produto(); 
       categoriaPai = new Categoria();
       subcategorias = new ArrayList<>();
    }

    public Produto getProduto() {
        return produto;
    }

    public List<Categoria> getCategoriaRaizes() {
        return categoriaRaizes;
    }

    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

}
