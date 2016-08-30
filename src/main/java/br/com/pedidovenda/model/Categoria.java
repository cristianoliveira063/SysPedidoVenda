/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CRISTIANO
 */
@Entity
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 60)
    @NotNull
    private String descricao; 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;
    @OneToMany(mappedBy = "categoriaPai", cascade = CascadeType.ALL)
    private List<Categoria> subcategorias = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the categoriaPai
     */
    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    /**
     * @param categoriaPai the categoriaPai to set
     */
    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    /**
     * @return the subcategorias
     */
    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    /**
     * @param subcategorias the subcategorias to set
     */
    public void setSubcategorias(List<Categoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.syspedidovenda.model.Categoria[ id=" + id + " ]";
    }

}
