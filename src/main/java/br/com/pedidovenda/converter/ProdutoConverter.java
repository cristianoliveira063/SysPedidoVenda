/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Produtos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

    @Inject
    private Produtos produtos;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Produto retorno = null;

        if (value != null) {
            Long id = new Long(value);
            retorno = produtos.pesquisarPorID(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Produto produto = (Produto) value;
            return produto.getId() == null ? null : produto.getId().toString();
        }
        return "";
    }
}
