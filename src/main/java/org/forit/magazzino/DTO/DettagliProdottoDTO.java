/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author forIT
 */
public class DettagliProdottoDTO {
    private long id;
    private String nome;
    private BigDecimal prezzo_vendita;
    private String provenienza;
    private LocalDate scadenza;
    private long id_scaffale;
    private String categoria;
    private BigDecimal prezzo_acquisto;
    private int quantita_acquistati;
    private BigDecimal spesa_totale;
    private int quantita_venduti;
    private BigDecimal ritorno;
    private String nome_fornitore;

    public DettagliProdottoDTO() {
    }

    public DettagliProdottoDTO(String nome, BigDecimal prezzo_vendita, String provenienza, LocalDate scadenza, long id_scaffale, String categoria, BigDecimal prezzo_acquisto, int quantita_acquistati, BigDecimal spesa_totale, int quantita_venduti, BigDecimal ritorno, String nome_fornitore) {
        this.nome = nome;
        this.prezzo_vendita = prezzo_vendita;
        this.provenienza = provenienza;
        this.scadenza = scadenza;
        this.id_scaffale = id_scaffale;
        this.categoria = categoria;
        this.prezzo_acquisto = prezzo_acquisto;
        this.quantita_acquistati = quantita_acquistati;
        this.spesa_totale = spesa_totale;
        this.quantita_venduti = quantita_venduti;
        this.ritorno = ritorno;
        this.nome_fornitore = nome_fornitore;
    }

    public DettagliProdottoDTO(long id,String nome, BigDecimal prezzo_vendita, String provenienza, LocalDate scadenza, long id_scaffale, String categoria, BigDecimal prezzo_acquisto, int quantita_acquistati, BigDecimal spesa_totale, int quantita_venduti, BigDecimal ritorno, String nome_fornitore) {
        this.id=id;
        this.nome = nome;
        this.prezzo_vendita = prezzo_vendita;
        this.provenienza = provenienza;
        this.scadenza = scadenza;
        this.id_scaffale = id_scaffale;
        this.categoria = categoria;
        this.prezzo_acquisto = prezzo_acquisto;
        this.quantita_acquistati = quantita_acquistati;
        this.spesa_totale = spesa_totale;
        this.quantita_venduti = quantita_venduti;
        this.ritorno = ritorno;
        this.nome_fornitore = nome_fornitore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrezzo_vendita() {
        return prezzo_vendita;
    }

    public void setPrezzo_vendita(BigDecimal prezzo_vendita) {
        this.prezzo_vendita = prezzo_vendita;
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

    public long getId_scaffale() {
        return id_scaffale;
    }

    public void setId_scaffale(long id_scaffale) {
        this.id_scaffale = id_scaffale;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrezzo_acquisto() {
        return prezzo_acquisto;
    }

    public void setPrezzo_acquisto(BigDecimal prezzo_acquisto) {
        this.prezzo_acquisto = prezzo_acquisto;
    }

    public int getQuantita_acquistati() {
        return quantita_acquistati;
    }

    public void setQuantita_acquistati(int quantita_acquistati) {
        this.quantita_acquistati = quantita_acquistati;
    }

    public BigDecimal getSpesa_totale() {
        return spesa_totale;
    }

    public void setSpesa_totale(BigDecimal spesa_totale) {
        this.spesa_totale = spesa_totale;
    }

    public int getQuantita_venduti() {
        return quantita_venduti;
    }

    public void setQuantita_venduti(int quantita_venduti) {
        this.quantita_venduti = quantita_venduti;
    }

    public BigDecimal getRitorno() {
        return ritorno;
    }

    public void setRitorno(BigDecimal ritorno) {
        this.ritorno = ritorno;
    }

    public String getNome_fornitore() {
        return nome_fornitore;
    }

    public void setNome_fornitore(String nome_fornitore) {
        this.nome_fornitore = nome_fornitore;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + Objects.hashCode(this.prezzo_vendita);
        hash = 37 * hash + Objects.hashCode(this.provenienza);
        hash = 37 * hash + Objects.hashCode(this.scadenza);
        hash = 37 * hash + (int) (this.id_scaffale ^ (this.id_scaffale >>> 32));
        hash = 37 * hash + Objects.hashCode(this.categoria);
        hash = 37 * hash + Objects.hashCode(this.prezzo_acquisto);
        hash = 37 * hash + this.quantita_acquistati;
        hash = 37 * hash + Objects.hashCode(this.spesa_totale);
        hash = 37 * hash + this.quantita_venduti;
        hash = 37 * hash + Objects.hashCode(this.ritorno);
        hash = 37 * hash + Objects.hashCode(this.nome_fornitore);
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
        final DettagliProdottoDTO other = (DettagliProdottoDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_scaffale != other.id_scaffale) {
            return false;
        }
        if (this.quantita_acquistati != other.quantita_acquistati) {
            return false;
        }
        if (this.quantita_venduti != other.quantita_venduti) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.provenienza, other.provenienza)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.nome_fornitore, other.nome_fornitore)) {
            return false;
        }
        if (!Objects.equals(this.prezzo_vendita, other.prezzo_vendita)) {
            return false;
        }
        if (!Objects.equals(this.scadenza, other.scadenza)) {
            return false;
        }
        if (!Objects.equals(this.prezzo_acquisto, other.prezzo_acquisto)) {
            return false;
        }
        if (!Objects.equals(this.spesa_totale, other.spesa_totale)) {
            return false;
        }
        if (!Objects.equals(this.ritorno, other.ritorno)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DettagliProdottoDTO{" + "id=" + id + ", nome=" + nome + ", prezzo_vendita=" + prezzo_vendita + ", provenienza=" + provenienza + ", scadenza=" + scadenza + ", id_scaffale=" + id_scaffale + ", categoria=" + categoria + ", prezzo_acquisto=" + prezzo_acquisto + ", quantita_acquistati=" + quantita_acquistati + ", spesa_totale=" + spesa_totale + ", quantita_venduti=" + quantita_venduti + ", ritorno=" + ritorno + ", nome_fornitore=" + nome_fornitore + '}';
    }
    
    
}