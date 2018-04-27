package org.forit.magazzino.jsf;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.FornitoreDTO;
import org.forit.magazzino.Exception.MagazzinoException;

@ManagedBean(name = "fBean")
@ViewScoped
public class FornitoreBean {

    private List<FornitoreDTO> fornitori = new ArrayList<>();
    private FornitoreDTO fornitore = new FornitoreDTO(-1, -1, "", "", "", -1);
    private boolean disabled;

    @PostConstruct
    public void init() {
        this.loadFornitori();
    }

    public List<FornitoreDTO> getFornitori() {
        return fornitori;
    }

    public FornitoreDTO getFornitore() {
        return fornitore;
    }

    private void loadFornitori() {
        MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
        try {
            fornitori = magazzinoDAO.getListaFornitori();
        } catch (MagazzinoException ex) {
            fornitori = new ArrayList<>();
        }
    }

    public void loadFornitore(long ID, boolean disabled) {

        this.disabled = disabled;
        if (ID == -1) {
            fornitore = new FornitoreDTO(-1, -1, "", "", "", -1);
        } else {
            try {
                MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
                fornitore = magazzinoDAO.getFornitore(ID);
            } catch (MagazzinoException ex) {
                fornitore = new FornitoreDTO(-1, -1, "", "", "", -1);
            }
        }
    }

    public void saveFornitore() {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            if (fornitore.getId() == -1) {
                magazzinoDAO.insertFornitore(fornitore.getId(), fornitore.getIdCategoria(), fornitore.getNome(), fornitore.getIndirizzo(), fornitore.getRecapito(), fornitore.getIdOrdine());
            } else {
                magazzinoDAO.updateFornitore(fornitore.getId(), fornitore.getIdCategoria(), fornitore.getNome(), fornitore.getIndirizzo(), fornitore.getRecapito(), fornitore.getIdOrdine());
            }
            this.loadFornitori();
        } catch (MagazzinoException ex) {
        }
    }

    public boolean getDisabled() {
        return disabled;
    }
}
