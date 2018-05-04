/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author UTENTE
 */
@Entity
@Table(name = "prodotto")
@NamedQueries({
    @NamedQuery(
            name = "prodotto.selectAll",
            query = "SELECT n FROM ProdottoEntity n ORDER BY n.nome"
    )
})
public class ProdottoEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long ID;
    
    @Column(name = "NOME", unique = false, nullable = false)
    private String nome;
    
    @Column(name = "PREZZO", unique = false, nullable = false)
    private BigDecimal prezzo;
    
    @Column(name = "SCADENZA", unique = false, nullable = false)
    private LocalDate scadenza;
    
    @Column(name = "PROVENIENZA", unique = false, nullable = false)
    private String provenienza;
    
    @Column(name = "ID_FORNITORE", unique = false, nullable = false)
    private long idFornitore;

    public ProdottoEntity() {
    }

    public ProdottoEntity(long ID, String nome, BigDecimal prezzo, LocalDate scadenza, String provenienza) {
        this.ID = ID;
        this.nome = nome;
        this.prezzo = prezzo;
        this.scadenza = scadenza;
        this.provenienza = provenienza;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    public String getProvenienza() {
        return provenienza;
    }

    public void setProvenienza(String provenienza) {
        this.provenienza = provenienza;
    }

    public long getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(long idFornitore) {
        this.idFornitore = idFornitore;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.prezzo);
        hash = 53 * hash + Objects.hashCode(this.scadenza);
        hash = 53 * hash + Objects.hashCode(this.provenienza);
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
        final ProdottoEntity other = (ProdottoEntity) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.provenienza, other.provenienza)) {
            return false;
        }
        if (!Objects.equals(this.prezzo, other.prezzo)) {
            return false;
        }
        if (!Objects.equals(this.scadenza, other.scadenza)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProdottoEntity{" + "ID=" + ID + ", nome=" + nome + ", prezzo=" + prezzo + ", scadenza=" + scadenza + ", provenienza=" + provenienza + '}';
    }
}
