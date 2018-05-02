/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.ProdottoDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
@Path("/prodotti")
public class ProdottiRest {
    @Path("/")
    @GET
    @Produces("application/json")
    public List<ProdottoDTO> getProdotti(){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            return magazzino.getListaProdotti();
        } catch (MagazzinoException ex) {
            return new ArrayList<>();
        }
    }
    @Path("/{id}")
    @GET
    @Produces("application/json")
    public ProdottoDTO getProdotto(@PathParam("id")long id){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            return magazzino.getProdotto(id);
        } catch (MagazzinoException ex) {
            return null;
        }
    }
    @Path("/")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postProdotto(ProdottoDTO prodotto){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            magazzino.insertProdotto(prodotto);
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }
    @Path("/{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public boolean putProdotto(@PathParam("id")long id,ProdottoDTO prodotto){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            prodotto.setId(id);
            magazzino.updateProdotto(prodotto);
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }
    @Path("/{id}")
    @DELETE
    @Produces("application/json")
    public boolean deleteProdotto(@PathParam("id")long id){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            magazzino.deleteProdotto(id);
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }
}
