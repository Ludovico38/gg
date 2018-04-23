package org.forit.magazzino.servlet;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author UTENTE
 */
public class MagazzinoServlet extends HttpServlet {

    protected final static String HEAD
            = "<head>"
            + "    <title>Netflix</title>"
            + "    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
            + "    <script type='text/javascript' src='js/jquery-3.3.1.js'></script>"
            + "    <script type='text/javascript' src='js/bootstrap.js'></script>"
            + "    <link rel='stylesheet' href='css/bootstrap.css' type='text/css'/>"
            + "    <link rel='icon' href='img/icona_magazzino.ico'>"
            + "</head>";

    protected final static String NAVBAR
            = "<nav class='navbar navbar-default'>"
            + "    <div class='container-fluid'>"
            + "        <div class='navbar-header'>"
            + "            <button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#navigazione'>"
            + "                <span class='icon-bar'></span>"
            + "                <span class='icon-bar'></span>"
            + "                <span class='icon-bar'></span>"
            + "            </button>"
            + "            <a class='navbar-brand' href='index.html'>"
            + "                <img alt='Magazzino' src='img/icona_magazzino' width='20'>"
            + "            </a>"
            + "        </div>"
            + "        <div class='collapse navbar-collapse' id='navigazione'>"
            + "            <ul class='nav navbar-nav'>"
            + "                <li class='$$clienti$$'><a href='prodotti'>Prodotti</a></li>"
            + "                <li class='$$attori$$'><a href='scaffali'>Scaffali</a></li>"
            + "                <li class='$$registi$$'><a href='magazzinieri'>Magazzinieri</a></li>"
            + "                <li class='$$film$$'><a href='veicoli'>Veicoli</a></li>"
            + "                <li class='$$premi$$'><a href='fornitori'>Fornitori</a></li>"
            + "                <li class='$$abbonamenti$$'><a href='entrateuscite'>Entrate/Uscite</a></li>"
            + "            </ul>"
            + "        </div>"
            + "    </div>"
            + "</nav>";

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
