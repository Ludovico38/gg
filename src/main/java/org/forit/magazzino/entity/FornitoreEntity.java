package org.forit.magazzino.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "fornitore")
@NamedQueries({
    @NamedQuery(
            name = "fornitore.selectAll",
            query = "SELECT f FROM FornitoreEntity f ORDER BY f.nome"
    )
})
public class FornitoreEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    long ID = -1;
    
    @Column(name = "NOME", unique = false, nullable = false)
    String nome;
    
    @Column(name = "INDIRIZZO", unique = false, nullable = false)
    String indirizzo;
    
    @Column(name = "ID_CATEGORIA", unique = false, nullable = false)
    long idCategoria;
    
    @Column(name = "RECAPITO", unique = false, nullable = false)
    String recapito;
    
    @Column(name = "ID_ORDINE", unique = false, nullable = false)
    long idOrdine;

    public FornitoreEntity() {
    }

    public FornitoreEntity(long ID, String nome, String indirizzo, long idCategoria, String recapito, long idOrdine) {
        this.ID = ID;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.idCategoria = idCategoria;
        this.recapito = recapito;
        this.idOrdine = idOrdine;
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getRecapito() {
        return recapito;
    }

    public void setRecapito(String recapito) {
        this.recapito = recapito;
    }

    public long getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(long idOrdine) {
        this.idOrdine = idOrdine;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.indirizzo);
        hash = 67 * hash + (int) (this.idCategoria ^ (this.idCategoria >>> 32));
        hash = 67 * hash + Objects.hashCode(this.recapito);
        hash = 67 * hash + (int) (this.idOrdine ^ (this.idOrdine >>> 32));
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
        final FornitoreEntity other = (FornitoreEntity) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.idCategoria != other.idCategoria) {
            return false;
        }
        if (this.idOrdine != other.idOrdine) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.indirizzo, other.indirizzo)) {
            return false;
        }
        if (!Objects.equals(this.recapito, other.recapito)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FornitoreEntity{" + "ID=" + ID + ", nome=" + nome + ", indirizzo=" + indirizzo + ", idCategoria=" + idCategoria + ", recapito=" + recapito + ", idOrdine=" + idOrdine + '}';
    }

}