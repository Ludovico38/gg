/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.entity;

import java.io.Serializable;
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
@Table(name = "magazziniere")
@NamedQueries({
    @NamedQuery(
            name = "magazziniere.selectAll",
            query = "SELECT m FROM MagazziniereEntity m ORDER BY m.cognome"
    )
})
public class MagazziniereEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(table = "magazziniere", name = "NOME", unique = false, nullable = false)
    private String nome;

    @Column(table = "magazziniere", name = "COGNOME", unique = false, nullable = false)
    private String cognome;

    @Column(table = "magazziniere", name = "CODICE_FISCALE", unique = false, nullable = false)
    private String codiceFiscale;

    @Column(table = "magazziniere", name = "DATA_NASCITA", unique = false, nullable = false)
    private LocalDate dataDiNascita;

    @Column(table = "magazziniere", name = "PATENTE", unique = false, nullable = true)
    private String patente;

    @Column(table = "magazziniere", name = "ID_VEICOLO", unique = false, nullable = false)
    private long idVeicolo;

    public MagazziniereEntity() {
    }

    public MagazziniereEntity(long id, String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita, String patente, long idVeicolo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita;
        this.patente = patente;
        this.idVeicolo = idVeicolo;
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

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public long getIdVeicolo() {
        return idVeicolo;
    }

    public void setIdVeicolo(long idVeicolo) {
        this.idVeicolo = idVeicolo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.cognome);
        hash = 23 * hash + Objects.hashCode(this.codiceFiscale);
        hash = 23 * hash + Objects.hashCode(this.dataDiNascita);
        hash = 23 * hash + Objects.hashCode(this.patente);
        hash = 23 * hash + (int) (this.idVeicolo ^ (this.idVeicolo >>> 32));
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
        final MagazziniereEntity other = (MagazziniereEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idVeicolo != other.idVeicolo) {
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
        if (!Objects.equals(this.dataDiNascita, other.dataDiNascita)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MagazziniereEntity{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale + ", dataDiNascita=" + dataDiNascita + ", patente=" + patente + ", idVeicolo=" + idVeicolo + '}';
    }

}
