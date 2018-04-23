/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
public class MagazziniereServlet extends HttpServlet {

    private final static String HEAD = "<head> "
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
        List<MagazziniereDTO> magazzinieri;
        String messaggioerrore = null;
        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            magazzinieri = magazzinoDAO.getListaMagazziniere();
        } catch (MagazzinoException ex) {
            magazzinieri = new ArrayList<>();
            messaggioerrore = "Impossibile leggere i dati dal Database";
        }
        resp.setContentType("text/html;charset=UTF=8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(HEAD);
            out.println("<body>");
            out.println(NAVBAR);
            if (messaggioerrore != null) {
                out.println("<h2>" + messaggioerrore + "</h2>");
            }
            out.println("<div class='table-responsive'>");
            out.println("<table class='table'>");
            out.println(THEAD);
            magazzinieri.forEach(magazziniere -> {
                out.println("<tr>");
                out.println("<td>");
                out.println("<a href ='?ID = " + magazziniere.getId() + "&action= view' class = 'btn btn-default' title = 'Visualizza Dettaglio'>");
                out.println("<span class='glyphicon glyphicon-eye-open'></span>");
                out.println("</a>");
                out.println("<a href='?ID = " + magazziniere.getId() + "&action= edit' class='btn btn-default' title='Modifica Dati'>");
                out.println("<span class='glyphicon glyphicon-pencil'></span>");
                out.println("</a>");
                out.println("</td>");
                out.println("<td>" + magazziniere.getNome() + "</td>");
                out.println("<td>" + magazziniere.getCognome() + "</td>");
                out.println("<td>" + magazziniere.getPatente() + "</td>");
                out.println("</tr>");
            });
            out.println("<tbody>");
            out.println("</tbody>");

            out.println("</table>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
