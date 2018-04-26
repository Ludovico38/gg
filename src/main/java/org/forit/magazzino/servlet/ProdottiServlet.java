/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.ProdottoDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
public class ProdottiServlet extends MagazzinoServlet {

    private final static String HEAD = "<head>\n"
            + "        <title>Prodotti</title>\n"
            + "        <meta charset=\"UTF-8\">\n"
            + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "        <script type=\"text/javascript\" src=\"js/bootstrap.js\"></script>\n"
            + "        <link rel=\"stylesheet\" href=\"css/bootstrap.css\" type=\"text/css\"/>\n"
            + "        <link rel=\"icon\" href=\"img/icona_magazzino.png\">\n"
            + "    </head>";

    private final static String NAVBAR = "<nav class=\"navbar navbar-default\">\n"
            + "            <div class=\"container-fluid\">\n"
            + "                <div class=\"navbar-header\">\n"
            + "                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#navigazione\" aria-expanded=\"false\">\n"
            + "                        <span class=\"sr-only\">Toggle navigation</span>\n"
            + "                        <span class=\"icon-bar\"></span>\n"
            + "                        <span class=\"icon-bar\"></span>\n"
            + "                        <span class=\"icon-bar\"></span>\n"
            + "                    </button>\n"
            + "                    <a class=\"navbar-brand\" href=\"index.html\">\n"
            + "                        <img alt=\"Magazzino\" src=\"img/icona_magazzino.png\" width=\"20\"></a>\n"
            + "                </div>\n"
            + "\n"
            + "                <div class=\"collapse navbar-collapse\" id=\"navigazione\">\n"
            + "                    <ul class=\"nav navbar-nav\">\n"
            + "                        <li><a href=\"/magazzino/prodotti.html\">Prodotti</a></li>\n"
            + "                        <li><a href=\"/magazzino/scaffali.html\">Scaffali</a></li>\n"
            + "                        <li><a href=\"/magazzino/magazzinieri.html\">Magazzinieri</a></li>\n"
            + "                        <li><a href=\"#\">Veicoli</a></li>\n"
            + "                        <li><a href=\"#\">Fornitori</a></li>\n"
            + "                        <li><a href=\"#\">Entrate/Uscite</a></li>\n"
            + "                    </ul>                                        \n"
            + "                </div>\n"
            + "            </div>\n"
            + "        </nav> ";

    private final static String THEAD = "<thead>\n"
            + "                    <tr>\n"
            + "                        <th class='col-sm-1'>\n"
            + "                            <a href='?action=new' class='btn btn-primary'>Nuovo</a>\n"
            + "                        </th>\n"
            + "                        <th class='col-sm-2'>Nome</th>\n"
            + "                        <th class='col-sm-2'>Prezzo</th>\n"
            + "                        <th class='col-sm-2'>Scadenza</th>\n"
            + "                        <th class='col-sm-2'>Provenienza</th>\n"
            + "                    </tr>\n"
            + "                </thead>";

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long ID = Long.parseLong(req.getParameter("ID"));
        String nome = req.getParameter("nome");
        BigDecimal prezzo = new BigDecimal(req.getParameter("prezzo"));
        LocalDate scadenza = LocalDate.parse(req.getParameter("scadenza"));
        String provenienza = req.getParameter("provenienza");

        try {
            ProdottoDTO prodotto = new ProdottoDTO(ID, nome, prezzo, scadenza, provenienza);
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();

            magazzinoDAO.insertProdotto(prodotto);
            this.doGet(req, resp);
        } catch (MagazzinoException ex) {
            System.out.println("Errore");
            resp.sendRedirect("prodotti?action=new");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getContentType();
        String action = req.getParameter("action");
        if (action == null) {
            this.listaProdotti(resp);
        } else {
            switch (action) {
                case "new":
                    this.inserisciProdotto(resp.getWriter(), new ProdottoDTO(-1, "", new BigDecimal(0), null, ""));
                    break;
                default:
                    this.listaProdotti(resp);
            }
        }
    }
    
    private void listaProdotti(HttpServletResponse resp) throws IOException {
        List<ProdottoDTO> prodotti;
        String messaggioErrore = null;
        
        try {
            MagazzinoDAO MagazzinoDAO = new MagazzinoDAO();
            prodotti = MagazzinoDAO.getListaProdotti();
        } catch (MagazzinoException ex) {
            prodotti = new ArrayList<>();
            messaggioErrore = "Errore: Impossibile leggere i dati dal DB";
        }
        
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(HEAD);
            out.println("<body>");
            out.println(NAVBAR);
            
            if (messaggioErrore != null) {
                out.println("<h2>" + messaggioErrore + "</h2>");
            }
            
            out.println("<div class='table-responsive'>");
            out.println("<table class='table'>");
            out.println(THEAD);
            out.println("<tbody>");
            prodotti.forEach(prodotto -> {
                out.println("<tr>");
                
                out.println("<td>" + prodotto.getId() + "</td>");
                out.println("<td>" + prodotto.getNome() + "</td>");
                out.println("<td>" + prodotto.getPrezzo() + "</td>");
                out.println("<td>" + prodotto.getScadenza() + "</td>");
                out.println("<td>" + prodotto.getProvenienza() + "</td>");
                
                out.println("</tr>");
                
            });
            out.println("</tbody>");
            
            out.println("</table>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void inserisciProdotto(PrintWriter out, ProdottoDTO prodotto) {
        
        out.println(HEAD);
        out.println(NAVBAR);
        
        out.println("<form action='prodotti' method='POST' class='container-fluid'>");
        out.println("<div class='panel panel-default'>");
        out.println("<div class='panel-heading'>");
        out.println("<div class='panel-title'>Inserimento Prodotto</div>");
        out.println("</div>");
        out.println("<div class='panel-body'>");
        
        out.println("<div class='row'>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Nome</label>");
        out.println("<input type='text' name='nome' class='form-control' value='" + prodotto.getNome() + "'/>");
        out.println("</div>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Prezzo</label>");
        out.println("<input type='number' name='prezzo' class='form-control' value='" + prodotto.getPrezzo() + "'/>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Scadenza</label>");
        out.println("<input type='date' name='scadenza' class='form-control' value='" + prodotto.getScadenza() + "'/>");
        out.println("</div>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Provenienza</label>");
        out.println("<input type='text' name='provenienza' class='form-control' value='" + prodotto.getProvenienza() + "'/>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("<input type='hidden' name='ID' value='" + prodotto.getId() + "'/>");
        
        out.println("<footer class='footer'>");
        out.println("<div class='container'>");
        out.println("<input type='submit' class='btn btn-primary col-sm-1 col-sm-offset-12' value='Salva'/>");
        out.println("</div>");
        out.println("</footer>");
        out.println("</form>");
    }

}
