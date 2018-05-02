/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class ProdottoRest {
    
    @Path("/")
    @GET()
    @Produces("application/json")
    public List<ProdottoDTO> getProdotti(){
        try{
            MagazzinoDAO mDAO = new MagazzinoDAO();
            return mDAO.getListaProdotti();
        } catch(MagazzinoException ex){
            return new ArrayList<>();
        }
    }
    
    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public ProdottoDTO getProdotto(@PathParam("id") long ID){
        try{
            MagazzinoDAO mDAO = new MagazzinoDAO();
            return mDAO.getProdotto(ID);
        } catch(MagazzinoException ex){
            return null;
        }
    }
    
    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postProdotto(ProdottoDTO prodotto){
        try{
            MagazzinoDAO mDAO = new MagazzinoDAO();
            mDAO.insertProdotto(prodotto);
            return true;
        } catch(MagazzinoException ex){
            return false;
        }
    }
}
