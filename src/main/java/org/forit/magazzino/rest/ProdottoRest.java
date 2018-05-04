/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.magazzino.DAO.ProdottoDAO;
import org.forit.magazzino.DTO.ProdottoDTO;

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
        ProdottoDAO prodotto = new ProdottoDAO();
        return prodotto.getListaProdotti();
    }
    
    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public ProdottoDTO getProdotto(@PathParam("id") long ID){
        ProdottoDAO prodotto = new ProdottoDAO();
        return prodotto.getProdotto(ID);
    }
    
    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postProdotto(ProdottoDTO prodotto){
        ProdottoDAO p = new ProdottoDAO();
        p.insertProdotto(prodotto.getNome(), prodotto.getPrezzo(), prodotto.getScadenza(), prodotto.getProvenienza(), prodotto.getIdFornitore());
        return true;
    }
}
