package org.forit.magazzino.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.Exception.MagazzinoException;

@ManagedBean(name = "mBean")
@ViewScoped
public class MagazziniereBean {

    private List<MagazziniereDTO> magazzinieri = new ArrayList<>();
    private MagazziniereDTO magazziniere = new MagazziniereDTO(-1, "", "", "", LocalDate.now(), "", -1);
    private boolean disabled;

    @PostConstruct
    public void init() {
        this.loadMagazzinieri();
    }

    public List<MagazziniereDTO> getMagazzinieri() {
        return magazzinieri;
    }

    public MagazziniereDTO getMagazziniere() {
        return magazziniere;
    }

    private void loadMagazzinieri() {
        MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
        try {
            magazzinieri = magazzinoDAO.getListaMagazzinieri();
        } catch (MagazzinoException ex) {
            magazzinieri = new ArrayList<>();
        }
    }

    public void loadMagazziniere(long ID, boolean disabled) {

        this.disabled = disabled;
        if (ID == -1) {
            magazziniere = new MagazziniereDTO(-1, "", "", "", LocalDate.now(), "", -1);
        } else {
            try {
                MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
                magazziniere = magazzinoDAO.getMagazziniere(ID);
            } catch (MagazzinoException ex) {
                magazziniere = new MagazziniereDTO(-1, "", "", "", LocalDate.now(), "", -1);
            }
        }
    }

    public void saveMagazziniere() {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            if (magazziniere.getId() == -1) {
                magazzinoDAO.insertMagazziniere(magazziniere.getId(), magazziniere.getNome(), magazziniere.getCognome(), magazziniere.getCodiceFiscale(), magazziniere.getDataNascita(), magazziniere.getPatente(), magazziniere.getId_veicolo());
            } else {
                magazzinoDAO.updateMagazziniere(magazziniere.getId(), magazziniere.getNome(), magazziniere.getCognome(), magazziniere.getCodiceFiscale(), magazziniere.getDataNascita(), magazziniere.getPatente(), magazziniere.getId_veicolo());
            }
            this.loadMagazzinieri();
        } catch (MagazzinoException ex) {
        }
    }

    public boolean getDisabled() {
        return disabled;
    }
    
    
}