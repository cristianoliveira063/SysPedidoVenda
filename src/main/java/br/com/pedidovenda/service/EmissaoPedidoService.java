/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.service;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.repository.Pedidos;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
public class EmissaoPedidoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroPedidoService cadastroPedidoService;
    @Inject
    private EstoqueService estoqueService;
    @Inject
    private Pedidos pedidos;

    public Pedido emitir(Pedido pedido) throws NegocioException {
        pedido = this.cadastroPedidoService.salvar(pedido);

        if (pedido.isNaoEmissivel()) {
            throw new NegocioException("Pedido não pode ser emitido com status "
                    + pedido.getStatus().getDescricao() + ".");
        }

        this.estoqueService.baixarItensEstoque(pedido);

        pedido.setStatus(StatusPedido.EMITIDO);

        pedido = this.pedidos.adicionar(pedido);

        return pedido;
    }

}
