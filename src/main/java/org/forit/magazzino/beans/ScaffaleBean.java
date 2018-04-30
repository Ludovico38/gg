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
import javax.faces.bean.ViewScoped;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.ScaffaleDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
@ManagedBean
@ViewScoped
public class ScaffaleBean {
    List<ScaffaleDTO> listaScaffali=new ArrayList<>();
    ScaffaleDTO scaffale;
    boolean disabled;
    
    @PostConstruct
    private void init(){
        loadScaffali();
    }
    
    private void loadScaffali(){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            listaScaffali=magazzino.getListaScaffali();
        } catch (MagazzinoException ex) {
            listaScaffali=new ArrayList<>();
            System.out.println("ERRORE:"+ex);
        }
    }
    
    public void loadScaffale(long id){
        disabled=false;
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            scaffale=magazzino.getScaffale(id);
        } catch (MagazzinoException ex) {
            scaffale=new ScaffaleDTO();
            System.out.println("ERRORE:"+ex);
        }
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public ScaffaleDTO getScaffale() {
        return scaffale;
    }

    public void setScaffale(ScaffaleDTO scaffale) {
        this.scaffale = scaffale;
    }

    public List<ScaffaleDTO> getListaScaffali() {
        return listaScaffali;
    }

    public void setListaScaffali(List<ScaffaleDTO> listaScaffali) {
        this.listaScaffali = listaScaffali;
    }
}
