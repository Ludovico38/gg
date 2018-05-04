/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author UTENTE
 */
@Entity
@Table(name = "tipo_veicolo")
public class TipoVeicoloEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "DESCRIZIONE", unique = false, nullable = false)
    private String descrizione;

//    @ElementCollection()
//    @CollectionTable(name = "tipo_veicolo", joinColumns = @JoinColumn(name = "ID"))
//    private List<VeicoloEntity> veicoli = new ArrayList<>();
    public TipoVeicoloEntity() {
    }

    public TipoVeicoloEntity(long id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }

    public long getid() {
        return id;
    }

    public void setid(long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

//    public List<VeicoloEntity> getVeicoli() {
//        return veicoli;
//    }
//
//    public void setVeicoli(List<VeicoloEntity> veicoli) {
//        this.veicoli = veicoli;
//    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.descrizione);
//        hash = 47 * hash + Objects.hashCode(this.veicoli);
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
        final TipoVeicoloEntity other = (TipoVeicoloEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
//        if (!Objects.equals(this.veicoli, other.veicoli)) {
//            return false;
//        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoVeicoloEntity{" + "id=" + id + ", descrizione=" + descrizione + '}';
    }

}
