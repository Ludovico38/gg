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
import org.forit.magazzino.DTO.FornitoreDTO;
import org.forit.magazzino.Exception.MagazzinoException;

@Path("/fornitori")
public class FornitoreRest {

    @Path("/")
    @GET()
    @Produces("application/json")
    public List<FornitoreDTO> getFornitori() {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            return magazzinoDAO.getListaFornitori();
        } catch (MagazzinoException ex) {
            return new ArrayList<>();
        }
    }

    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public FornitoreDTO getFornitore(@PathParam("id") long ID) {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            return magazzinoDAO.getFornitore(ID);
        } catch (MagazzinoException ex) {
            return null;
        }
    }

    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postFornitore(FornitoreDTO fornitore) {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            magazzinoDAO.insertFornitore(fornitore.getId(), fornitore.getIdCategoria(), fornitore.getNome(), fornitore.getIndirizzo(), fornitore.getRecapito(), fornitore.getIdOrdine());
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }

    @Path("/")
    @PUT()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean putFornitore(FornitoreDTO fornitore) {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            magazzinoDAO.updateFornitore(fornitore.getId(), fornitore.getIdCategoria(), fornitore.getNome(), fornitore.getIndirizzo(), fornitore.getRecapito(), fornitore.getIdOrdine());
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }

    @Path("/")
    @DELETE()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteFornitore(FornitoreDTO fornitore) {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            magazzinoDAO.deleteFornitore(fornitore.getNome());
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }
}
