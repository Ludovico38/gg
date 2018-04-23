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
public class ScaffaleDTO {
    private long id;
    private String descrizione;

    public ScaffaleDTO() {
    }

    public ScaffaleDTO(long id, String descrizione) {
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
        int hash = 5;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.descrizione);
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
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScaffaleDTO{" + "id=" + id + ", descrizione=" + descrizione + '}';
    }
    
    
}
