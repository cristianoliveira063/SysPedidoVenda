/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.model;

import br.com.pedidovenda.util.validator.Validador;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CRISTIANO
 */
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_criacao", nullable = false)
    @NotNull
    private Date dataCriacao;
    @Column(columnDefinition = "text")
    private String observacao;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrega", nullable = false)
    @NotNull
    private Date dataEntrega;
    @Column(name = "valor_frete", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal valorFrete = BigDecimal.ZERO;
    @Column(name = "valor_desconto", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal valorDesconto = BigDecimal.ZERO;
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @NotNull
    private StatusPedido status;
    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", length = 20, nullable = false)
    @NotNull
    private FormaPagamento formaPagamento;
    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    @NotNull
    private Usuario vendedor;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull
    private Cliente cliente;
    @Embedded
    private EnderecoEntrega enderecoEntrega;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValorSubtotal() {
        return this.getValorTotal().subtract(this.getValorFrete()).add(this.getValorDesconto());
    }

    public Usuario getVendedor() {
        if (Validador.isObjectValido(vendedor)) {
            return vendedor;
        }
        vendedor = new Usuario();
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        if (Validador.isObjectValido(cliente)) {
            return cliente;
        }
        cliente = new Cliente();
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EnderecoEntrega getEnderecoEntrega() {
        if (Validador.isObjectValido(enderecoEntrega)) {
            return enderecoEntrega;
        }
        enderecoEntrega = new EnderecoEntrega();
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public boolean isNovo() {
        return getId() == null;
    }

    public boolean isExistente() {
        return !isNovo();
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return Arrays.asList(FormaPagamento.values());
    }

    public void recalcularValorTotal() {
        BigDecimal total = BigDecimal.ZERO;
        total = total.add(this.getValorFrete()).subtract(this.getValorDesconto());
        for (ItemPedido item : this.getItens()) {
            if (item.getProduto() != null && item.getProduto().getId() != null) {
                total = total.add(item.getValorTotal());
            }
        }
        this.setValorTotal(total);
    }

    public void adicionarItemVazio() {
        if (this.isOrcamento()) {
            Produto produto = new Produto();
            ItemPedido item = new ItemPedido();
            //item.setQuantidade(1);
            item.setProduto(produto);
            item.setPedido(this);
            this.getItens().add(0, item);
        }
    }

    public void removerItemVazio() {
        ItemPedido primeiroItem = this.getItens().get(0);

        if (primeiroItem != null && primeiroItem.getProduto().getId() == null) {
            this.getItens().remove(0);
        }
    }

    public boolean isValorTotalNegativo() {
        return this.getValorTotal().compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isOrcamento() {
        return StatusPedido.ORCAMENTO.equals(this.getStatus());
    }

    public boolean isEmitido() {
        return StatusPedido.EMITIDO.equals(this.getStatus());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        return Objects.equals(this.id, other.id);
    }

}
