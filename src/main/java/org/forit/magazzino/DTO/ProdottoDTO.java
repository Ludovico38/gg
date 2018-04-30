/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

/**
 *
 * @author forIT
 */
public class ProdottoDTO {
    private long id=-1,idFornitore=-1;
    private String nome="",provenienza="";
    private LocalDate scadenza=LocalDate.of(1, Month.JANUARY, 1);
    private BigDecimal prezzo=new BigDecimal(0);
    
    public ProdottoDTO() {
    }
    
    public ProdottoDTO(long id) {
        this.id=id;
    }
    
    public ProdottoDTO(long id, String nome, BigDecimal prezzo, LocalDate scadenza, String provenienza) {
        this.id = id;
        this.nome = nome;
        this.provenienza = provenienza;
        this.scadenza = scadenza;
        this.prezzo = prezzo;
    }
    
    public ProdottoDTO(String nome, BigDecimal prezzo, LocalDate scadenza, String provenienza, long idFornitore) {
        this.idFornitore = idFornitore;
        this.nome = nome;
        this.provenienza = provenienza;
        this.scadenza = scadenza;
        this.prezzo = prezzo;
    }

    public ProdottoDTO(long id, String nome, BigDecimal prezzo, LocalDate scadenza, String provenienza, long idFornitore) {
        this.id = id;
        this.idFornitore = idFornitore;
        this.nome = nome;
        this.provenienza = provenienza;
        this.scadenza = scadenza;
        this.prezzo = prezzo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(long idFornitore) {
        this.idFornitore = idFornitore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProvenienza() {
        return provenienza;
    }

    public void setProvenienza(String provenienza) {
        this.provenienza = provenienza;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
    
    public String getScadenzaAsString(){
        return scadenza.toString();
    }
    
    public void setScadenzaAsString(String scadenza){
        this.scadenza=LocalDate.parse(scadenza);
    }
    
    public String getPrezzoAsString(){
        return prezzo.toPlainString();
    }
    
    public void setPrezzoAsString(String prezzo){
        this.prezzo= new BigDecimal(prezzo);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + (int) (this.idFornitore ^ (this.idFornitore >>> 32));
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.provenienza);
        hash = 53 * hash + Objects.hashCode(this.scadenza);
        hash = 53 * hash + Objects.hashCode(this.prezzo);
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
        final ProdottoDTO other = (ProdottoDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idFornitore != other.idFornitore) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.provenienza, other.provenienza)) {
            return false;
        }
        if (!Objects.equals(this.scadenza, other.scadenza)) {
            return false;
        }
        if (!Objects.equals(this.prezzo, other.prezzo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProdottoDTO{" + "id=" + id + ", idFornitore=" + idFornitore + ", nome=" + nome + ", provenienza=" + provenienza + ", scadenza=" + scadenza + ", prezzo=" + prezzo + '}';
    }
    
    
}
