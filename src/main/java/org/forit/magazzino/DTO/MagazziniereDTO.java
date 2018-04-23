/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DTO;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author forIT
 */
public class MagazziniereDTO {
    private long id;
    private String nome,cognome,codiceFiscale,patente;
    private LocalDate dataNascita;
    private long id_veicolo;

    public MagazziniereDTO() {
    }

    public MagazziniereDTO(long id, String nome, String cognome, String codiceFiscale, LocalDate dataNascita, String patente, long id_veicolo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataNascita = dataNascita;
        this.patente = patente;
        this.id_veicolo = id_veicolo;
    }
    
    public MagazziniereDTO(long id, String nome, String cognome, String codiceFiscale, LocalDate dataNascita, String patente) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataNascita = dataNascita;
        this.patente = patente;
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public long getId_veicolo() {
        return id_veicolo;
    }

    public void setId_veicolo(long id_veicolo) {
        this.id_veicolo = id_veicolo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.nome);
        hash = 13 * hash + Objects.hashCode(this.cognome);
        hash = 13 * hash + Objects.hashCode(this.codiceFiscale);
        hash = 13 * hash + Objects.hashCode(this.dataNascita);
        hash = 13 * hash + Objects.hashCode(this.patente);
        hash = 13 * hash + (int) (this.id_veicolo ^ (this.id_veicolo >>> 32));
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
        final MagazziniereDTO other = (MagazziniereDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_veicolo != other.id_veicolo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.codiceFiscale, other.codiceFiscale)) {
            return false;
        }
        if (!Objects.equals(this.patente, other.patente)) {
            return false;
        }
        if (!Objects.equals(this.dataNascita, other.dataNascita)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MagazziniereDTO{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale + ", dataNascita=" + dataNascita + ", patente=" + patente + ", id_veicolo=" + id_veicolo + '}';
    }
    
    
}