/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.modelFilter.ProdutoFilter;
import br.com.pedidovenda.repository.Produtos;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author CRISTIANO
 */
@Named
@ViewScoped
public class PesquisarProdutosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Produtos produtos;
    private LazyDataModel<Produto> dataModel;
    @Inject
    private ProdutoFilter produtoFilter;
    

    @PostConstruct
    public void init() {    
        pesquisar();
    }

    public LazyDataModel<Produto> pesquisar() {
        dataModel = new LazyDataModel<Produto>() {
            private static final long serialVersionUID = 1L;
            @Override
            public List<Produto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                    Map<String, Object> filters) {
                produtoFilter.getFilter().setPrimeiroRegistro(first);
                produtoFilter.getFilter().setPropriedadeOrdenacao(sortField);
                produtoFilter.getFilter().setQuantidadeRegistros(pageSize);
                produtoFilter.getFilter().setAscendente(SortOrder.ASCENDING.equals(sortOrder));
                setRowCount(produtos.count().intValue());
                return produtos.filtrar(produtoFilter);
            }
        };
        return dataModel;
    }

    public ProdutoFilter getProdutoFilter() {
        return produtoFilter;
    }
    
    public LazyDataModel<Produto> getDataModel() {
        return dataModel;
    }
}
