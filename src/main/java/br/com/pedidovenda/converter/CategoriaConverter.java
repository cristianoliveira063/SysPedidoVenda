/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.repository.Categorias;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    @Inject
    private Categorias categorias;

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Categoria retorno = null;
        if (value != null) {
            Long id = new Long(value);
            retorno = categorias.pesquisarPorID(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {    
        if (value != null) {
            return ((Categoria) value).getId().toString();
        }

        return "";
    }

}
