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
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.Exception.MagazzinoException;

@Path("/magazzinieri")
public class MagazziniereRest {

    @Path("/")
    @GET()
    @Produces("application/json")
    public List<MagazziniereDTO> getMagazzinieri() {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            return magazzinoDAO.getListaMagazzinieri();
        } catch (MagazzinoException ex) {
            return new ArrayList<>();
        }
    }

    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public MagazziniereDTO getMagazziniere(@PathParam("id") long ID) {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            return magazzinoDAO.getMagazziniere(ID);
        } catch (MagazzinoException ex) {
            return null;
        }
    }

    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postMagazziniere(MagazziniereDTO magazziniere) {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            magazzinoDAO.insertMagazziniere(magazziniere.getId(), magazziniere.getNome(), magazziniere.getCognome(), magazziniere.getCodiceFiscale(), magazziniere.getDataNascita(), magazziniere.getPatente(), magazziniere.getId_veicolo());
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }

    @Path("/")
    @PUT()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean putMagazziniere(MagazziniereDTO magazziniere) {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            magazzinoDAO.updateMagazziniere(magazziniere.getId(), magazziniere.getNome(), magazziniere.getCognome(), magazziniere.getCodiceFiscale(), magazziniere.getDataNascita(), magazziniere.getPatente(), magazziniere.getId_veicolo());
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }

    @Path("/")
    @DELETE()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteMagazziniere(MagazziniereDTO magazziniere) {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            magazzinoDAO.deleteMagazziniere(magazziniere.getId());
            return true;
        } catch (MagazzinoException ex) {
            return false;
        }
    }
}
