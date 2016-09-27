/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author CRISTIANO
 */
public class ValidarDocumento implements ConstraintValidator<Documento, String> {

    @Override
    public void initialize(Documento constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Validador.isStringValida(value)) {

            return Validador.isCpfVald(value) || Validador.isCnpjVald(value);

        }
        return true;

    }

}
