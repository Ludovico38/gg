/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.DTO.TipoVeicoloDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
public class MagazziniereServlet extends HttpServlet {

    private final static String HEAD
            = "<head> "
            + "<title>Magazzino</title> "
            + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> "
            + "<script type=\"text/javascript\" src=\"js/bootstrap.js\"></script> "
            + "<link rel=\"stylesheet\" href=\"css/bootstrap.css\" type=\"text/css\"/> "
            + "<link rel=\"icon\" href=\"img/icona_magazzino.png\"> "
            + "</head>";

    private final static String NAVBAR
            = "<nav class=\"navbar navbar-default\"> "
            + "<div class=\"container-fluid\"> "
            + "<div class=\"navbar-header\">"
            + "<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#navigazione\" aria-expanded=\"false\"> "
            + "<span class=\"sr-only\">Toggle navigation</span> "
            + "<span class=\"icon-bar\"></span> "
            + "<span class=\"icon-bar\"></span> "
            + "<span class=\"icon-bar\"></span> "
            + "</button> "
            + "<a class=\"navbar-brand\" href=\"index.html\"> "
            + "<img alt=\"Magazzino\" src=\"img/icona_magazzino.png\" width=\"20\"></a> "
            + "</div> "
            + " "
            + "<div class=\"collapse navbar-collapse\" id=\"navigazione\"> "
            + "<ul class=\"nav navbar-nav\"> "
            + "<li><a href=\"/magazzino/prodotti.html\">Prodotti</a></li> "
            + "<li><a href=\"/magazzino/scaffali.html\">Scaffali</a></li> "
            + "<li><a href=\"/magazzino/magazzinieri.html\">Magazzinieri</a></li> "
            + "<li><a href=\"#\">Veicoli</a></li> "
            + "<li><a href=\"#\">Fornitori</a></li> "
            + "<li><a href=\"#\">Entrate/Uscite</a></li> "
            + "</ul> "
            + "</div> "
            + "</div> "
            + "</nav>";
    private final static String THEAD
            = "<thead>"
            + "<tr>"
            + "<th class='col-sm-1'>"
            + "<a href='?action=new' class='btn btn-primary'>Nuovo</a>"
            + "</th>"
            + "<th class='col-sm-2'>Nome</th>"
            + "<th class='col-sm-2'>Cognome</th>"
            + "<th class='col-sm-2'>Patente</th>"
            + "</tr>"
            + "</thead>";

    @Override

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF=8");
        String action = req.getParameter("action");
        if (action == null) {
            this.listaMagazzinieri(resp);
        } else {
            switch (action) {
                case "view":
                    String ID = req.getParameter("ID");
                    this.dettaglioMagazziniere(resp, Long.parseLong(ID), true);
                    break;
                case "edit":
//                    ID = req.getParameter("ID");
//                    this.dettaglioMagazziniere(resp, Long.parseLong(ID), false);
                    break;
                case "new":
                    break;
                default:
                    this.listaMagazzinieri(resp);
            }
        }
        this.listaMagazzinieri(resp);
    }

    private void listaMagazzinieri(HttpServletResponse resp) throws IOException {
        List<MagazziniereDTO> magazziniere;
        String messaggioErrore = null;
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            magazziniere = magazzinoDAO.getListaMagazziniere();
        } catch (MagazzinoException ex) {
            magazziniere = new ArrayList<>();
            messaggioErrore = "Impossibile leggere i dati dal database";
        }
        try (PrintWriter out = resp.getWriter()) {
            this.apriHTML(out, messaggioErrore, "$$magazzinieri$$");
            this.createTabellaMagazzinieri(out, magazziniere);
            this.chiudiHTML(out);
        }
    }

    private void createTabellaMagazzinieri(PrintWriter out, List<MagazziniereDTO> magazzinieri) {
        out.println("<div class='container-fluid table-responsive'>");
        out.println("<table class='table'>");
        out.println(THEAD);
        out.println("<tbody>");
        magazzinieri.forEach(magazziniere -> {
            out.println("<tr>");
            out.println("  <td>");
            out.println("    <a href='?ID=" + magazziniere.getId() + "&action=view' class='btn btn-default' title='Visualizza Dettaglio'>");
            out.println("      <span class='glyphicon glyphicon-eye-open'></span>");
            out.println("    </a>");
            out.println("    <a href='?ID=" + magazziniere.getId() + "&action=edit' class='btn btn-default' title='Modifica Dati'>");
            out.println("      <span class='glyphicon glyphicon-pencil'></span>");
            out.println("    </a>");
            out.println("  </td>");
            out.println("  <td>" + magazziniere.getNome() + "</td>");
            out.println("  <td>" + magazziniere.getCognome() + "</td>");
            out.println("  <td>" + magazziniere.getPatente() + "</td>");
            out.println("</tr>");
        });
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
    }

    private void dettaglioMagazziniere(HttpServletResponse resp, long ID, boolean disabled) throws IOException {
        MagazziniereDTO magazziniere = null;
        TipoVeicoloDTO veicolo = null;
        String messaggioErrore = null;

//        if (ID == -1) {
//            magazziniere = new MagazziniereDTO(-1, "", "", null, "", "");
//            magazziniere.setId(-1);
//        } else {
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            magazziniere = magazzinoDAO.getMagazziniere(ID);
        } catch (MagazzinoException ex) {
            messaggioErrore = "Impossibile leggere i dati dal database";
        }
//        }
        try (PrintWriter out = resp.getWriter()) {
            this.apriHTML(out, messaggioErrore, "$$magazzinieri$$");
            if (magazziniere != null) {
                this.creaDettaglioMagazziniere(out, magazziniere, veicolo, disabled);
            }
            this.chiudiHTML(out);
        }
    }

    private void creaDettaglioMagazziniere(PrintWriter out, MagazziniereDTO magazziniere, TipoVeicoloDTO veicolo, boolean disabled) {

        out.println("<form action = 'clienti' method ='POST' class='container-fluid'>");
        out.println("<div class='panel panel-default'>");
        out.println("<div class='panel-heading'>");
        out.println("<div class='panel-title'>Dettaglio Magazziniere</div>");
        out.println("</div>");
        out.println("<div class='panel-body'>");
        out.println("<input type='hidden' name = 'ID' value = '" + magazziniere.getId() + "'/>");
        this.creaDatiAnagrafici(out, magazziniere, veicolo, disabled);

        out.println("</div>");
        out.println("<div class='panel-footer text-right'>");
        if (!disabled) {
            out.println("<input type='submit' class='btn btn-primary' value='Salva Modifiche'/>");
        }
        out.println("</div>");
        out.println("</div>");
        out.println("</form>");
    }

    private void creaDatiAnagrafici(PrintWriter out, MagazziniereDTO magazziniere, TipoVeicoloDTO veicolo, boolean disabled) {
        out.println("<div class='row'>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Nome</label>");
        out.println("<input type='text' name='nome' class='form-control' value ='" + magazziniere.getNome() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Cognome</label>");
        out.println("<input type='text' name='cognome' class='form-control' value ='" + magazziniere.getCognome() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Codice Fiscale</label>");
        out.println("<input type='text' name='codiceFiscale' class='form-control' value ='" + magazziniere.getCodiceFiscale() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Data di Nascita</label>");
        out.println("<input type='date' name='dataNascita' class='form-control' value ='" + magazziniere.getDataDiNascita() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Patente</label>");
        out.println("<input type='text' name='patente' class='form-control' value ='" + magazziniere.getPatente() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("</div>");

        out.println("<div class='col-sm-6'>");
        out.println("<label>Veicolo</label>");
        out.println("<input type='text' name='veicolo' class='form-control' value ='" + veicolo.getDescrizione() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");

    }
    
    protected void apriHTML(PrintWriter out, String messaggioErrore, String active) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println(HEAD);
        out.println("<body>");
        out.println(NAVBAR.replace(active, "active"));

        if (messaggioErrore != null) {
            out.println("<h2>" + messaggioErrore + "</h2>");
        }
    }

    protected void chiudiHTML(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
}
