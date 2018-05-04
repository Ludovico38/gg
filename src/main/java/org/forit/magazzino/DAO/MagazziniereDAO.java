/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DAO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.Exception.MagazzinoException;
import org.forit.magazzino.entity.MagazziniereEntity;

/**
 *
 * @author UTENTE
 */
public class MagazziniereDAO {

    public List<MagazziniereDTO> getListaMagazzinieri() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<MagazziniereEntity> query = em.createNamedQuery("magazziniere.selectAll", MagazziniereEntity.class);
        List<MagazziniereEntity> list = query.getResultList();
        List<MagazziniereDTO> magazzinieri = list.stream().map(entity -> {
            MagazziniereDTO magazziniere = new MagazziniereDTO(entity.getId(), entity.getNome(), entity.getCognome(),
                    entity.getCodiceFiscale(),
                    entity.getDataDiNascita(),
                    entity.getPatente(),
                    entity.getIdVeicolo());

            return magazziniere;
        }).collect(Collectors.toList());
        em.close();
        emf.close();

        return magazzinieri;
    }

    public MagazziniereDTO getMagazziniere(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        MagazziniereEntity entity = em.find(MagazziniereEntity.class, ID);
        MagazziniereDTO magazziniere = new MagazziniereDTO(entity.getId(), entity.getNome(), entity.getCognome(),
                entity.getCodiceFiscale(),
                entity.getDataDiNascita(),
                entity.getPatente(),
                entity.getIdVeicolo());

        em.close();
        emf.close();

        return magazziniere;
    }

    public void insertMagazziniere(String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita, String patente, long idVeicolo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            MagazziniereEntity m = new MagazziniereEntity();
            m.setNome(nome);
            m.setCognome(cognome);
            m.setCodiceFiscale(codiceFiscale);
            m.setDataDiNascita(dataDiNascita);
            m.setPatente(patente);
            m.setIdVeicolo(idVeicolo);
            em.persist(m);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println("Errore" + ex.getMessage());
            transaction.rollback();

        } finally {
            em.close();
            emf.close();
        }
    }

    public void updateMagazziniere(MagazziniereDTO magazziniere) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            MagazziniereEntity entity = em.find(MagazziniereEntity.class, magazziniere.getId());
            entity.setNome(magazziniere.getNome());
            entity.setCognome(magazziniere.getCognome());
            entity.setCodiceFiscale(magazziniere.getCodiceFiscale());
            entity.setDataDiNascita(magazziniere.getDataDiNascita());
            entity.setPatente(magazziniere.getPatente());
            entity.setIdVeicolo(magazziniere.getIdVeicolo());
            em.merge(entity);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println("Errore" + ex.getMessage());
            transaction.rollback();

        } finally {
            em.close();
            emf.close();
        }
    }

    public void deleteMagazziniere(MagazziniereDTO m) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            MagazziniereEntity magazziniere = em.find(MagazziniereEntity.class, m.getId());
            em.remove(magazziniere);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
