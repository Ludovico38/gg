/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DTO;

/**
 *
 * @author forIT
 */
public class ScaffaleDTO {
    private long id, idCategoria,idMagazziniere,idCrossScaffaleProdotto;

    public ScaffaleDTO() {
    }

    public ScaffaleDTO(long id, long idCategoria, long idMagazziniere, long idCrossScaffaleProdotto) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.idMagazziniere = idMagazziniere;
        this.idCrossScaffaleProdotto = idCrossScaffaleProdotto;
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

    public long getIdMagazziniere() {
        return idMagazziniere;
    }

    public void setIdMagazziniere(long idMagazziniere) {
        this.idMagazziniere = idMagazziniere;
    }

    public long getIdCrossScaffaleProdotto() {
        return idCrossScaffaleProdotto;
    }

    public void setIdCrossScaffaleProdotto(long idCrossScaffaleProdotto) {
        this.idCrossScaffaleProdotto = idCrossScaffaleProdotto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + (int) (this.idCategoria ^ (this.idCategoria >>> 32));
        hash = 37 * hash + (int) (this.idMagazziniere ^ (this.idMagazziniere >>> 32));
        hash = 37 * hash + (int) (this.idCrossScaffaleProdotto ^ (this.idCrossScaffaleProdotto >>> 32));
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
        final ScaffaleDTO other = (ScaffaleDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idCategoria != other.idCategoria) {
            return false;
        }
        if (this.idMagazziniere != other.idMagazziniere) {
            return false;
        }
        if (this.idCrossScaffaleProdotto != other.idCrossScaffaleProdotto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScaffaleDTO{" + "id=" + id + ", idCategoria=" + idCategoria + ", idMagazziniere=" + idMagazziniere + ", idCrossScaffaleProdotto=" + idCrossScaffaleProdotto + '}';
    }
    
}
