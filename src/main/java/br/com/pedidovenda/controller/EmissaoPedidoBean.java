/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.PedidoAlteradoEvent;
import java.io.Serializable;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author CRISTIANO
 */
@Named
@ViewScoped
public class EmissaoPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Pedido pedido;
    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

    public void emitirPedido() {
        this.pedido.removerItemVazio();

        try {
            //this.pedido = this.emissaoPedidoService.emitir(this.pedido);
            this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));

            //FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
        } finally {
            this.pedido.adicionarItemVazio();
        }
    }

}
