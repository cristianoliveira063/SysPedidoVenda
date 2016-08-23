/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author CRISTIANO
 */
public class Validador {
    
    
    
    public static boolean isObjectValido(Object object) {

        return object != null;

    }

    public static boolean isArrayValido(Object[] array) {
        return (array != null) && (array.length >= 1);
    }

    public static boolean isCollectionValida(Collection collection) {
        return (collection != null) && (!collection.isEmpty());
    }
    
    

    public static boolean isMapValido(Map map) {
        return (map != null) && (!map.isEmpty());
    }

    public static boolean isStringValida(String valorString) {
        return (valorString != null) && (!valorString.trim().equals(""));
    }

    public static boolean isDataValida(Date data) {
        return (data != null) && (data.getTime() >= -2208972740000L);
    }

    public static boolean isNumericoValido(byte numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(short numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(int numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(long numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(float numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(double numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Byte numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Short numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Integer numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Long numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Float numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Double numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(BigDecimal numero) {
        return validarNumero(numero);
    }

    private static boolean validarNumero(double numero) {
        return numero != 0.0D;
    }

    private static boolean validarNumero(Number numero) {
        return (numero != null) && (numero.doubleValue() != 0.0D);
    }

    
}
