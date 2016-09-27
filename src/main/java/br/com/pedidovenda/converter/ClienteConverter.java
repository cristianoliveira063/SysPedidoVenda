/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.repository.Clientes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

    @Inject
    private Clientes clientes;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cliente retorno = null;

        if (value != null) {
            Long id = new Long(value);
            retorno = clientes.pesquisarPorID(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Cliente cliente = (Cliente) value;
            return cliente.getId() == null ? null : cliente.getId().toString();
        }
        return "";
    }
}
