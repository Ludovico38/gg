package org.forit.magazzino.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.magazzino.DAO.MagazziniereDAO;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.Exception.MagazzinoException;

@Path("/magazzinieri")
public class MagazziniereRest {

    @Path("/")
    @GET()
    @Produces("application/json")
    public List<MagazziniereDTO> getMagazzinieri() {
        MagazziniereDAO magazziniereDAO = new MagazziniereDAO();
        return magazziniereDAO.getListaMagazzinieri();
    }

    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public MagazziniereDTO getMagazziniere(@PathParam("id") long ID) {
        MagazziniereDAO magazziniereDAO = new MagazziniereDAO();
        return magazziniereDAO.getMagazziniere(ID);
    }

    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postMagazziniere(MagazziniereDTO magazziniere) throws MagazzinoException {
        MagazziniereDAO magazziniereDAO = new MagazziniereDAO();
        if (magazziniere.getId() == -1) {
            magazziniereDAO.insertMagazziniere(magazziniere.getNome(), magazziniere.getCognome(), magazziniere.getCodiceFiscale(), magazziniere.getDataDiNascita(), magazziniere.getPatente(), magazziniere.getIdVeicolo());
            return true;
        } else {
            magazziniereDAO.updateMagazziniere(magazziniere);
            return false;
        }

    }

    @Path("/")
    @DELETE()
    @Consumes("application/json")
    @Produces("application/json")
    public void deleteMagazziniere(MagazziniereDTO magazziniere) {
        
            MagazziniereDAO magazziniereDAO = new MagazziniereDAO();
            magazziniereDAO.deleteMagazziniere(magazziniere);
       
    }
}
