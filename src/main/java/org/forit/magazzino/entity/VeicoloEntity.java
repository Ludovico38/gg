/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author UTENTE
 */
@Embeddable
public class VeicoloEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "PATENTE_RICHIESTA", unique = false, nullable = true)
    private String patenteRichiesta;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_VEICOLO")
    private TipoVeicoloEntity tipoVeicoloEntity;

    public VeicoloEntity() {
    }

    public VeicoloEntity(long id, long idTipoVeicolo, String patenteRichiesta, TipoVeicoloEntity tipoVeicoloEntity) {
        this.id = id;

        this.patenteRichiesta = patenteRichiesta;
        this.tipoVeicoloEntity = tipoVeicoloEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPatenteRichiesta() {
        return patenteRichiesta;
    }

    public void setPatenteRichiesta(String patenteRichiesta) {
        this.patenteRichiesta = patenteRichiesta;
    }

    public TipoVeicoloEntity getTipoVeicoloEntity() {
        return tipoVeicoloEntity;
    }

    public void setTipoVeicoloEntity(TipoVeicoloEntity tipoVeicoloEntity) {
        this.tipoVeicoloEntity = tipoVeicoloEntity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));

        hash = 89 * hash + Objects.hashCode(this.patenteRichiesta);
        hash = 89 * hash + Objects.hashCode(this.tipoVeicoloEntity);
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
        final VeicoloEntity other = (VeicoloEntity) obj;
        if (this.id != other.id) {
            return false;
        }

        if (!Objects.equals(this.patenteRichiesta, other.patenteRichiesta)) {
            return false;
        }
        if (!Objects.equals(this.tipoVeicoloEntity, other.tipoVeicoloEntity)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VeicoloEntity{" + "id=" + id + ", patenteRichiesta=" + patenteRichiesta + ", tipoVeicoloEntity=" + tipoVeicoloEntity + '}';
    }

}
