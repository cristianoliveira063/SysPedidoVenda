/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.service;

import br.com.pedidovenda.model.ItemPedido;
import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.repository.Pedidos;
import br.com.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
public class EstoqueService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Pedidos pedidos;

    @Transactional
    public void baixarItensEstoque(Pedido pedido) throws NegocioException {
        pedido = this.pedidos.pesquisarPorID(pedido.getId());
        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().baixarEstoque(item.getQuantidade());
        }
    }
}
