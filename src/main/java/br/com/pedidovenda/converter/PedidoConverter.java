/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.repository.Pedidos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

    @Inject
    private Pedidos pedidos;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Pedido retorno = null;

        if (value != null) {
            Long id = new Long(value);
            retorno = pedidos.pesquisarPorID(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Pedido pedido = (Pedido) value;
            return pedido.getId() == null ? null : pedido.getId().toString();
        }
        return "";
    }
}
