/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.ProdottoDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
@ManagedBean(name = "pBean")
@ViewScoped
public class ProdottiBean {

    private List<ProdottoDTO> prodotti = new ArrayList<>();
    private ProdottoDTO prodotto = new ProdottoDTO(-1, "", null, LocalDate.now(), "");
    private boolean disabled;

    @PostConstruct
    public void init() {
        this.loadProdotti();
    }

    public boolean getDisabled() {
        return disabled;
    }

    public List<ProdottoDTO> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<ProdottoDTO> prodotti) {
        this.prodotti = prodotti;
    }

    public ProdottoDTO getProdotto() {
        return prodotto;
    }

    public void setProdotto(ProdottoDTO prodotto) {
        this.prodotto = prodotto;
    }

    private void loadProdotti() {
        MagazzinoDAO magazzinoDAO = new MagazzinoDAO();

        try {
            prodotti = magazzinoDAO.getListaProdotti();
        } catch (MagazzinoException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void loadProdotto(long ID, boolean disabled) {
        this.disabled = disabled;
        if (ID == -1) {
            prodotto = new ProdottoDTO(-1, "", null, LocalDate.now(), "");
        } else {
            try {
                MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
                prodotto = magazzinoDAO.getProdotto(ID);
            } catch (MagazzinoException ex) {
                System.out.println("Error: " + ex.getMessage());
                prodotto = new ProdottoDTO();
            }
        }
    }
    

    public void saveProdotto() {
        try {
            MagazzinoDAO mDAO = new MagazzinoDAO();
            if (prodotto.getId() == -1) {
                mDAO.insertProdotto(prodotto);
            } else {
                mDAO.updateProdotto(prodotto);
            }
            this.loadProdotti();
        } catch (MagazzinoException ex) {
            System.out.println("Errore: " + ex.getMessage());
        }
    }
}
