/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DTO;

import java.util.Objects;

/**
 *
 * @author forIT
 */
public class FornitoreDTO {

    private long id, idCategoria, idOrdine;
    private String nome, indirizzo, recapito;

    public FornitoreDTO() {
    }

    public FornitoreDTO(long id, long idCategoria, String nome, String indirizzo, String recapito, long idOrdine) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.idOrdine = idOrdine;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.recapito = recapito;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public long getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(long idOrdine) {
        this.idOrdine = idOrdine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getRecapito() {
        return recapito;
    }

    public void setRecapito(String recapito) {
        this.recapito = recapito;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + (int) (this.idCategoria ^ (this.idCategoria >>> 32));
        hash = 41 * hash + (int) (this.idOrdine ^ (this.idOrdine >>> 32));
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.indirizzo);
        hash = 41 * hash + Objects.hashCode(this.recapito);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FornitoreDTO other = (FornitoreDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idCategoria != other.idCategoria) {
            return false;
        }
        if (this.idOrdine != other.idOrdine) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.indirizzo, other.indirizzo)) {
            return false;
        }
        if (!Objects.equals(this.recapito, other.recapito)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FornitoreDTO{" + "id=" + id + ", idCategoria=" + idCategoria + ", idOrdine=" + idOrdine + ", nome=" + nome + ", indirizzo=" + indirizzo + ", recapito=" + recapito + '}';
    }
}
