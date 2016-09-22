/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.modelFilter.ProdutoFilter;
import br.com.pedidovenda.repository.Categorias;
import br.com.pedidovenda.service.CadastroProdutoService;
import br.com.pedidovenda.service.NegocioException;
import br.com.pedidovenda.util.jsf.MessageView;
import br.com.pedidovenda.util.validator.Validador;
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
    private Produto produtoParam;
    @Inject
    private Categorias categorias;
    @NotNull
    private Categoria categoriaPai;
    private List<Categoria> categoriaRaizes;
    private List<Categoria> subcategorias;
    @Inject
    private CadastroProdutoService cadastroProdutoService;
    @Inject
    private ProdutoFilter produtoFilter;

    @PostConstruct
    public void init() {
        categoriaRaizes = categorias.raizes();
    }

    public void carregarSubcategorias() {
        subcategorias = categorias.subcategoriasDe(categoriaPai);
    }

    public void salvar() {
        try {
            cadastroProdutoService.salvar(produto);
            reset();
            MessageView.info("Produto salvo com sucesso!");
        } catch (NegocioException ex) {
            MessageView.error(ex.getMessage());
        }
    }

    private void reset() {
        produto = new Produto();
        categoriaPai = null;
        subcategorias = new ArrayList<>();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public ProdutoFilter getProdutoFilter() {
        return produtoFilter;
    }

    public void setProdutoFilter(ProdutoFilter produtoFilter) {
        this.produtoFilter = produtoFilter;
        
    }

    public Produto getProdutoParam() {
        return produtoParam;
    }

    public void setProdutoParam(Produto produtoParam) {
        this.produtoParam = produtoParam;
        if (Validador.isObjectValido(produtoParam)) {
            this.produto = produtoParam;
            this.categoriaPai = this.produtoParam.getCategoria().getCategoriaPai();
            carregarSubcategorias();
        }
    }

    public boolean isEditando() {
        return Validador.isObjectValido(produto.getId());
    }
}
