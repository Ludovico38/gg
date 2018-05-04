/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.magazzino.DTO.ProdottoDTO;
import org.forit.magazzino.Exception.MagazzinoException;
import org.forit.magazzino.entity.ProdottoEntity;

/**
 *
 * @author UTENTE
 */
public class ProdottoDAO {

    public List<ProdottoDTO> getListaProdotti() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<ProdottoEntity> query = em.createNamedQuery("prodotto.selectAll", ProdottoEntity.class);
        List<ProdottoEntity> list = query.getResultList();
        List<ProdottoDTO> prodotti = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ProdottoEntity entity = list.get(i);
            ProdottoDTO prodotto = new ProdottoDTO();
            prodotto.setId(entity.getID());
            prodotto.setNome(entity.getNome());
            prodotto.setPrezzo(entity.getPrezzo());
            prodotto.setScadenza(entity.getScadenza());
            prodotto.setProvenienza(entity.getProvenienza());

            prodotti.add(prodotto);
        }
        em.close();
        emf.close();

        return prodotti;
    }

    public ProdottoDTO getProdotto(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        ProdottoEntity entity = em.find(ProdottoEntity.class, ID);
        ProdottoDTO p = new ProdottoDTO();
        p.setId(entity.getID());
        p.setNome(entity.getNome());
        p.setPrezzo(entity.getPrezzo());
        p.setScadenza(entity.getScadenza());
        p.setProvenienza(entity.getProvenienza());

        em.close();
        emf.close();

        return p;
    }

    public void insertProdotto(String nome, BigDecimal prezzo, LocalDate scadenza, String provenienza, long idFornitore) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            ProdottoEntity p = new ProdottoEntity();
            p.setNome(nome);
            p.setPrezzo(prezzo);
            p.setScadenza(scadenza);
            p.setProvenienza(provenienza);
            p.setIdFornitore(idFornitore);
            em.persist(p);

            transaction.commit();
        } catch (Exception ex) {
            System.out.println("Errore " + ex.getMessage());
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    public void updateProdotto(ProdottoDTO prodotto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            ProdottoEntity entity = em.find(ProdottoEntity.class, prodotto.getId());
            entity.setNome(prodotto.getNome());
            entity.setPrezzo(prodotto.getPrezzo());
            entity.setScadenza(prodotto.getScadenza());
            entity.setProvenienza(prodotto.getProvenienza());
            entity.setIdFornitore(prodotto.getIdFornitore());
            em.merge(entity);

            transaction.commit();
        } catch (Exception ex) {
            System.out.println("Errore " + ex.getMessage());
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public void deleteProdotto(ProdottoDTO p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            ProdottoEntity prodotto = em.find(ProdottoEntity.class, p.getId());
            em.remove(prodotto);
            
            transaction.commit();
        } catch (Exception ex) {
            System.out.println("Errore " + ex.getMessage());
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
