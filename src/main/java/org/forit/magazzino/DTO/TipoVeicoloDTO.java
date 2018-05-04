/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DTO;

import java.util.Objects;

/**
 *
 * @author UTENTE
 */
public class TipoVeicoloDTO {
    private long id;
    private String descrizione;
    

    public TipoVeicoloDTO() {
    }

    public TipoVeicoloDTO(long id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.descrizione);
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
        final TipoVeicoloDTO other = (TipoVeicoloDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoVeicoloDTO{" + "id=" + id + ", descrizione=" + descrizione + '}';
    }
    
    
    
}
