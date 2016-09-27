/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.util.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 *
 * @author CRISTIANO
 */
@Min(11)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = {ValidarDocumento.class})
public @interface Documento {

    @OverridesAttribute(constraint = Pattern.class, name = "message")
    String message() default "{com.pedidovenda.constraints.documento.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
