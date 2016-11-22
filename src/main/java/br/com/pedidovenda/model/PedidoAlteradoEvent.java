/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.model;

/**
 *
 * @author CRISTIANO
 */
public class PedidoAlteradoEvent {

    private final Pedido pedido;

    public PedidoAlteradoEvent(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

}
