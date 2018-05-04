package org.forit.magazzino.DAO;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.magazzino.DTO.FornitoreDTO;
import org.forit.magazzino.Exception.MagazzinoException;
import org.forit.magazzino.entity.FornitoreEntity;

public class FornitoreDAO {

    public List<FornitoreDTO> getListaFornitori() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<FornitoreEntity> query = em.createNamedQuery("fornitore.selectAll", FornitoreEntity.class);
        List<FornitoreEntity> list = query.getResultList();
        List<FornitoreDTO> fornitori = list.stream().map(entity -> {
            FornitoreDTO fornitore = new FornitoreDTO(entity.getID(), entity.getIdCategoria(), entity.getNome(), entity.getIndirizzo(), entity.getRecapito(), entity.getIdOrdine());

            return fornitore;
        }).collect(Collectors.toList());
        em.close();
        emf.close();

        return fornitori;
    }

    public FornitoreDTO getFornitore(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        FornitoreEntity entity = em.find(FornitoreEntity.class, ID);
        FornitoreDTO fornitore = new FornitoreDTO(entity.getID(), entity.getIdCategoria(), entity.getNome(), entity.getIndirizzo(), entity.getRecapito(), entity.getIdOrdine());

        em.close();
        emf.close();

        return fornitore;
    }

    public void insertFornitore(long id, long idCategoria, String nome, String indirizzo, String recapito, long idOrdine) throws MagazzinoException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            FornitoreEntity fornitore = new FornitoreEntity();
            fornitore.getID();
            fornitore.getIdCategoria();
            fornitore.getNome();
            fornitore.getIndirizzo();
            fornitore.getRecapito();
            fornitore.getIdOrdine();
            em.persist(fornitore);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new MagazzinoException(ex);
        } finally {
            em.close();
            emf.close();
        }
    }

    public void updateFornitore(long id, long idCategoria, String nome, String indirizzo, String recapito, long idOrdine) throws MagazzinoException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("magazzino_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            FornitoreEntity fornitore = em.find(FornitoreEntity.class, id);
            //to do
            em.merge(fornitore);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new MagazzinoException(ex);
        } finally {
            em.close();
            emf.close();
        }
    }
}
