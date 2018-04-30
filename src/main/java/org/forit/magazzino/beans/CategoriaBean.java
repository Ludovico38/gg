/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.beans;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.CategoriaDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
@ManagedBean
@ViewScoped
public class CategoriaBean {
    private Map<Long,String> mappaCategorie;
    private CategoriaDTO categoria;
    
    @PostConstruct
    private void init(){
        loadCategorie();
    }
    
    private void loadCategorie(){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            mappaCategorie=magazzino.getListaCategorie();
        } catch (MagazzinoException ex) {
            mappaCategorie=new LinkedHashMap<>();
            System.out.println("ERRORE:"+ex);
        }
    }
    
    public String getCategoriaById(long id){
        return mappaCategorie.get(id);
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public Map<Long, String> getMappaCategorie() {
        return mappaCategorie;
    }

    public void setMappaCategorie(Map<Long, String> mappaCategorie) {
        this.mappaCategorie = mappaCategorie;
    }
    
}
