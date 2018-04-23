/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.DTO;

import java.util.Objects;

/**
 *
 * @author forIT
 */
public class VeicoloDTO {
    private long id;
    private String tipoVeiocolo,patenteRichiesta;

    public VeicoloDTO() {
    }

    public VeicoloDTO(long id, String tipoVeiocolo, String patenteRichiesta) {
        this.id = id;
        this.tipoVeiocolo = tipoVeiocolo;
        this.patenteRichiesta = patenteRichiesta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoVeiocolo() {
        return tipoVeiocolo;
    }

    public void setTipoVeiocolo(String tipoVeiocolo) {
        this.tipoVeiocolo = tipoVeiocolo;
    }

    public String getPatenteRichiesta() {
        return patenteRichiesta;
    }

    public void setPatenteRichiesta(String patenteRichiesta) {
        this.patenteRichiesta = patenteRichiesta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.tipoVeiocolo);
        hash = 53 * hash + Objects.hashCode(this.patenteRichiesta);
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
        final VeicoloDTO other = (VeicoloDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.tipoVeiocolo, other.tipoVeiocolo)) {
            return false;
        }
        if (!Objects.equals(this.patenteRichiesta, other.patenteRichiesta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VeicoloDTO{" + "id=" + id + ", tipoVeiocolo=" + tipoVeiocolo + ", patenteRichiesta=" + patenteRichiesta + '}';
    }
}
