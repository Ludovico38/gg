/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.ScaffaleDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
@Path("/scaffali")
public class ScaffaliRest {
    @Path("/")
    @GET
    @Produces("application/json")
    public List<ScaffaleDTO> getScaffali(){
        MagazzinoDAO magazzino= new MagazzinoDAO();
        try {
            return magazzino.getListaScaffali();
        } catch (MagazzinoException ex) {
            return new ArrayList<>();
        }
    }
    @Path("/{id}")
    @GET
    @Produces("application/json")
    public ScaffaleDTO getScaffale(@PathParam("id")long id){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            return magazzino.getScaffale(id);
        } catch (MagazzinoException ex) {
            return null;
        }
    }
    @Path("/")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postScaffale(ScaffaleDTO scaffale){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            magazzino.insertScaffale(scaffale);
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }
    @Path("/{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public boolean putScaffale(@PathParam("id")long id,ScaffaleDTO scaffale){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            scaffale.setId(id);
            magazzino.updateScaffale(scaffale);
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }
    @Path("/{id}")
    @DELETE
    @Produces("application/json")
    public boolean deleteScaffale(@PathParam("id")long id){
        MagazzinoDAO magazzino=new MagazzinoDAO();
        try {
            magazzino.deleteScaffale(id);
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }
}
