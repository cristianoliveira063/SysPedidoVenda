/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.service;

import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.repository.Usuarios;
import br.com.pedidovenda.util.jpa.Transactional;
import br.com.pedidovenda.util.validation.Validador;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
public class CadastroUsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Usuarios usuarios;

    @Transactional
    public Usuario adicionar(Usuario usuario) throws NegocioException {
        Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
        if (Validador.isObjectValido(usuarioExistente) && usuarioExistente.notEquals(usuario)) {
            throw new NegocioException("Já existe um usuário com o e-mail informado.");
        }
        if (Validador.isNotObjectValido(usuario.getId())) {
            usuario.setSenha(convertMD5(usuario.getSenha()));
        }
        return usuarios.adicionar(usuario);
    }

    private String convertMD5(String senha) {
        MessageDigest mdigest;
        try {
            mdigest = MessageDigest.getInstance("MD5");
            byte[] valorMd5 = mdigest.digest(senha.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : valorMd5) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,
                        3));
            }
            System.out.println("A senha criptografada é:" + sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
   
    @Transactional
    public Usuario alterarSenha(Usuario usuario, String senhaAtual, String novaSenha) throws NegocioException {
        Usuario u = usuarios.pesquisarPorID(usuario.getId());
        if (convertMD5(senhaAtual).equals(u.getSenha())) {
            u.setSenha(convertMD5(novaSenha));
            return u;
        }       
        throw  new NegocioException("Senha não pode ser alterada.");      
    }
}
