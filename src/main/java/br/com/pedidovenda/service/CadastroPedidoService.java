/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.service;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.repository.Clientes;
import br.com.pedidovenda.repository.Pedidos;
import br.com.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
public class CadastroPedidoService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Clientes clientes;
    @Inject
    private Pedidos pedidos;

    public List<Cliente> pesquisarPorNome(String nome) {
        return clientes.pesquisar("nome", nome);
    }

    @Transactional
    public Pedido salvar(Pedido pedido) throws NegocioException {
        if (pedido.isNovo()) {
            pedido.setDataCriacao(new Date());
            pedido.setStatus(StatusPedido.ORCAMENTO);
        }
        if (pedido.getItens().isEmpty()) {
            throw new NegocioException("O pedido deve possuir pelo menos um item.");
        }
        if (pedido.isValorTotalNegativo()) {
            throw new NegocioException("Valor total do pedido não pode ser negativo.");
        }
        pedido.recalcularValorTotal();
        pedido = this.pedidos.adicionar(pedido);
        return pedido;
    }
}
