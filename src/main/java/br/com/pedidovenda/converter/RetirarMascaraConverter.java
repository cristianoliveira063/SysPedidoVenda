/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.converter;

import br.com.pedidovenda.util.validator.StringUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author CRISTIANO
 */
@FacesConverter(value = "retirarMascaraConverter")
public class RetirarMascaraConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return StringUtil.retiraMascara(value);
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if (value != null) {
            return value.toString();
        }
        return "";
    }
    
}
