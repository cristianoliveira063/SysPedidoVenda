/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.ItemPedido;
import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.repository.Produtos;
import br.com.pedidovenda.repository.Usuarios;
import br.com.pedidovenda.service.CadastroPedidoService;
import br.com.pedidovenda.service.NegocioException;
import br.com.pedidovenda.util.jsf.MessageView;
import br.com.pedidovenda.util.validator.Validador;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CadastroPedidoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Inject
    private Pedido pedido;
    private List<Usuario> vendedores;
    @Inject
    private Usuarios usuarios;
    @Inject
    private CadastroPedidoService cadastroPedidoService;
    private Produto produtoLinhaEditavel;
    private String sku;
    @Inject
    private Produtos produtos;
    
    @PostConstruct
    public void init() {
        this.vendedores = usuarios.listar();
        this.pedido.setStatus(StatusPedido.ORCAMENTO);
        this.pedido.adicionarItemVazio();
        
        recalcularPedido();
    }
    
    public void salvar() {
        this.pedido.removerItemVazio();
        try {
            cadastroPedidoService.salvar(pedido);
            MessageView.info("Pedido salvo com sucesso.");
        } catch (NegocioException ex) {
            MessageView.error(ex.getMessage());
        } finally {
            this.pedido.adicionarItemVazio();
        }
    }
    
    public void recalcularPedido() {
        if (this.pedido != null) {
            this.pedido.recalcularValorTotal();
        }
    }
    
    public void carregarProdutoLinhaEditavel() {
        ItemPedido item = this.pedido.getItens().get(0);
        if (this.produtoLinhaEditavel != null) {
            if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
                MessageView.error("Já existe um item no pedido com o produto informado.");
            } else {
                item.setProduto(this.produtoLinhaEditavel);
                item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
                this.pedido.adicionarItemVazio();
                this.produtoLinhaEditavel = null;
                this.sku = null;
                this.pedido.recalcularValorTotal();
            }
        }
    }
    
    public void atualizarQuantidade(ItemPedido item, int linha) {
        if (item.getQuantidade() < 1) {
            if (linha == 0) {
                item.setQuantidade(1);
            } else {
                this.getPedido().getItens().remove(linha);
            }
        }
        
        this.pedido.recalcularValorTotal();
    }
    
    private boolean existeItemComProduto(Produto produto) {
        boolean existeItem = false;
        for (ItemPedido item : this.getPedido().getItens()) {
            if (produto.equals(item.getProduto())) {
                existeItem = true;
                break;
            }
        }
        return existeItem;
    }
    
    public List<Produto> completarProduto(String nome) {
        return this.produtos.porNome(nome);
    }
    
    public void carregarProdutoPorSku() {
        if (Validador.isStringValida(this.sku)) {
            this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
            this.carregarProdutoLinhaEditavel();
        }
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    
    public List<Usuario> getVendedores() {
        return vendedores;
    }
    
    public List<Cliente> completarCliente(String nome) {
        return this.cadastroPedidoService.pesquisarPorNome(nome);
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public Produto getProdutoLinhaEditavel() {
        return produtoLinhaEditavel;
    }
    
    public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
        this.produtoLinhaEditavel = produtoLinhaEditavel;
    }
    
    public String getSku() {
        return sku;
    }
    
    public void setSku(String sku) {
        this.sku = sku;
    }
    
}
