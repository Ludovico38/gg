/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.ProdottoDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
@ManagedBean
@ViewScoped
public class ProdottoBean {

    private ProdottoDTO prodotto = new ProdottoDTO();
    private List<ProdottoDTO> listaProdotti = new ArrayList<>();
    private boolean disabled;

    @ManagedProperty(value = "#{fornitoreBean}")
    private FornitoreBean fornitoreBean;

    @PostConstruct
    private void init() {
        loadProdotti();
    }

    public FornitoreBean getFornitoreBean() {
        return fornitoreBean;
    }

    public void setFornitoreBean(FornitoreBean fornitoreBean) {
        this.fornitoreBean = fornitoreBean;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<ProdottoDTO> getListaProdotti() {
        return listaProdotti;
    }

    public ProdottoDTO getProdotto() {
        return prodotto;
    }

    public void loadProdotto(long id, boolean disabled) {
        this.disabled = disabled;
        MagazzinoDAO magazzino = new MagazzinoDAO();
        if (id == -1) {
            prodotto = new ProdottoDTO(-1);
            return;
        }
        try {
            prodotto = magazzino.getProdotto(id);
        } catch (MagazzinoException ex) {
            prodotto = new ProdottoDTO(-1);
            System.out.println("ERRORE" + ex);
        }
    }

    private void loadProdotti() {
        MagazzinoDAO magazzino = new MagazzinoDAO();
        try {
            listaProdotti = magazzino.getListaProdotti();
        } catch (MagazzinoException ex) {
            System.out.println("ERRORE" + ex);
            listaProdotti = new ArrayList<>();
        }
    }

    public void saveProdotto() {
        MagazzinoDAO magazzino = new MagazzinoDAO();
        try {
            if (prodotto.getId() == -1) {
                magazzino.insertProdotto(prodotto);
            } else {
                magazzino.updateProdotto(prodotto);
            }
            this.loadProdotti();
        } catch (MagazzinoException ex) {
            System.out.println("Impossibile caricare dati");
        }
    }

    public String getNomeFornitore(long id) {
        if (id != -1) {
            return fornitoreBean.getNomeFornitoreById(id);
        } else {
            return "";
        }
    }
}
