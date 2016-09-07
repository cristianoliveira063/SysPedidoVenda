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
import br.com.pedidovenda.repository.Produtos;
import br.com.pedidovenda.service.NegocioException;
import br.com.pedidovenda.util.jsf.MessageView;
import br.com.pedidovenda.util.validation.Validador;
import java.io.Serializable;
import java.math.BigDecimal;
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
    private Produto produtoSelecionado;
    private List<Categoria> subcategorias;
    @Inject
    private Categorias categorias;

    @PostConstruct
    public void init()  {
        subcategorias = categorias.filhas();    
        pesquisar();
    }
    
    public void excluir(){      
        try {
            produtos.remover(produtoSelecionado);
            MessageView.info("Produto " + produtoSelecionado.getSku() 
				+ " excluído com sucesso.");   
        } catch (NegocioException ex) {
          MessageView.fatal(ex.getMessage());
        }        
    }

    public LazyDataModel<Produto> pesquisar() {
        dataModel = new LazyDataModel<Produto>() {
            private static final long serialVersionUID = 1L;
            @Override
            public List<Produto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                    Map<String, Object> filters) {
                if (Validador.isStringValida(produtoFilter.getNome()) ||
                        Validador.isStringValida(produtoFilter.getSku())) {
                    first = BigDecimal.ZERO.intValue();
                }
                produtoFilter.getFilter().setPrimeiroRegistro(first);
                produtoFilter.getFilter().setPropriedadeOrdenacao(sortField);
                produtoFilter.getFilter().setQuantidadeRegistros(pageSize);
                produtoFilter.getFilter().setAscendente(SortOrder.ASCENDING.equals(sortOrder));
                setRowCount(produtos.quantidadeFiltrados(produtoFilter));
                return produtos.filtrar(produtoFilter);
            }
            @Override
            public Object getRowKey(Produto object) {
                return object.getId();
            }
            @Override
            public Produto getRowData(String rowKey) {
                List<Produto> list = (List<Produto>) getWrappedData();
                for (Produto p : list) {
                    if (p.getId() == Long.parseLong(rowKey)) {
                        return p;
                    }
                }
                return null;
            }
        };
        return null;
    }

    public ProdutoFilter getProdutoFilter() {
        return produtoFilter;
    }

    public LazyDataModel<Produto> getDataModel() {
        return dataModel;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

}
