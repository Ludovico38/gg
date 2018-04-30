/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.beans;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.FornitoreDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
@ManagedBean
@ViewScoped
public class FornitoreBean {

    Map<Long, FornitoreDTO> listaFornitori = new LinkedHashMap<>();
    FornitoreDTO fornitore = new FornitoreDTO();

    @PostConstruct
    private void init() {
        loadFornitori();
    }

    private void loadFornitori() {
        MagazzinoDAO magazzino = new MagazzinoDAO();
        try {
            listaFornitori = magazzino.getListaFornitori();
        } catch (MagazzinoException ex) {
            listaFornitori = new LinkedHashMap<>();
            System.out.println("ERRORE CARICAMENTO FORNITORI" + ex);
        }
    }

    public void loadFornitore(long id) {
        MagazzinoDAO magazzino = new MagazzinoDAO();
        if (id == -1) {
            fornitore = new FornitoreDTO();
        } else {
            try {
                fornitore = magazzino.getFornitore(id);
            } catch (MagazzinoException ex) {
                System.out.println("ERRORE CARICAMENTO FORNITORE" + ex);
            }
        }
    }

    public Map<Long, FornitoreDTO> getListaFornitori() {
        return listaFornitori;
    }

    public void setListaFornitori(Map<Long, FornitoreDTO> listaFornitori) {
        this.listaFornitori = listaFornitori;
    }
    
    public Set<Long> getIdFornitori(){
        return listaFornitori.keySet();
    }

    public FornitoreDTO getFornitore() {
        return fornitore;
    }

    public void setFornitore(FornitoreDTO fornitore) {
        this.fornitore = fornitore;
    }

    public String getNomeFornitoreById(long id) {
        if (id != -1) {
            return listaFornitori.get(id).getNome();
        }else{
            return "";
        }
    }
}
