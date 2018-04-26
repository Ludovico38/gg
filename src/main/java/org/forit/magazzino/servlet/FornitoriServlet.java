package org.forit.magazzino.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.FornitoreDTO;
import org.forit.magazzino.Exception.MagazzinoException;

public class FornitoriServlet extends MagazzinoServlet {

    private final static String THEAD
            = "<thead>"
            + "    <tr>"
            + "        <th class='col-sm-1'>"
            + "            <a href='?action=new' class='btn btn-primary'>Nuovo</a>"
            + "        </th>"
            + "        <th class='col-sm-2'>Nome</th>"
            + "        <th class='col-sm-2'>Indirizzo</th>"
            + "        <th class='col-sm-2'>Categoria</th>"
            + "        <th class='col-sm-2'>Recapito</th>"
            + "        <th class='col-sm-3'>ID Ordine</th>"
            + "    </tr>"
            + "</thead>";

    private final static String THEAD_ORDINI = "<thead>\n"
            + "                                <tr>\n"
            + "                                    <th class='col-sm-4'>Prodotto</th>\n"
            + "                                    <th class='col-sm-2'>Quantità</th>\n"
            + "                                    <th class='col-sm-2'>Prezzo di acquisto</th>\n"
            + "                                </tr>\n"
            + "                            </thead>";

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
        String indirizzo = req.getParameter("indirizzo");
        Long idCategoria = Long.parseLong(req.getParameter("categoria"));
        String recapito = req.getParameter("recapito");
        Long idOrdine = Long.parseLong(req.getParameter("ID_ordine"));

//        try {
//            FornitoreDTO fornitore = new FornitoreDTO(ID, idCategoria, nome, indirizzo, recapito, idOrdine);
//            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
//
//            if (ID == -1) {
//                magazzinoDAO.insertFornitore(fornitore);
//            } else {
//                magazzinoDAO.updateFornitore(fornitore);
//            }
//            this.doGet(req, resp);
//        } catch (MagazzinoException ex) {
//            System.out.println("Errore: " + ex.getMessage());
//
//            if (ID == -1) {
//                resp.sendRedirect("clienti?ID=&action=new");
//            } else {
//                resp.sendRedirect("clienti?ID=" + ID + "&action=edit");
//            }
//
//        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        if (action == null) {
            this.listaFornitori(resp);
        } else {

            switch (action) {
                case "view":
                    String ID = req.getParameter("ID");
                    this.dettaglioFornitore(resp, Long.parseLong(ID), true);
                    break;
                case "edit":
                    ID = req.getParameter("ID");
                    this.dettaglioFornitore(resp, Long.parseLong(ID), false);
                    break;
                case "new":
                    this.dettaglioFornitore(resp, -1, false);
                    break;
                default:
                    this.listaFornitori(resp);
            }
        }
        this.listaFornitori(resp);
    }

    private void listaFornitori(HttpServletResponse resp) throws IOException {
        List<FornitoreDTO> fornitori;
        String messaggioErrore = null;

        try {
            MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
            fornitori = magazzinoDAO.getListaFornitori();
        } catch (MagazzinoException ex) {
            fornitori = new ArrayList<>();
            messaggioErrore = "Impossibile leggere i dati dal database";
        }

        try (PrintWriter out = resp.getWriter()) {
            this.apriHTML(out, messaggioErrore, "$$clienti$$");
            this.createTabellaFornitori(out, fornitori);
            this.chiudiHTML(out);
        }
    }

    private void createTabellaFornitori(PrintWriter out, List<FornitoreDTO> fornitori) {
        out.println("<div class='container-fluid table-responsive'>");
        out.println("<table class='table'>");
        out.println(THEAD);
        out.println("<tbody>");
        fornitori.forEach(fornitore -> {
            out.println("<tr>");
            out.println("  <td>");
            out.println("    <a href='?ID=" + fornitore.getId()+ "&action=view' class='btn btn-default' title='Visualizza Dettaglio'>");
            out.println("      <span class='glyphicon glyphicon-eye-open'></span>");
            out.println("    </a>");
            out.println("    <a href='?ID=" + fornitore.getId()+ "&action=edit' class='btn btn-default' title='Modifica Dati'>");
            out.println("      <span class='glyphicon glyphicon-pencil'></span>");
            out.println("    </a>");
            out.println("  </td>");
            out.println("  <td>" + fornitore.getNome() + "</td>");
            out.println("  <td>" + fornitore.getIndirizzo()+ "</td>");
            out.println("  <td>" + fornitore.getIdCategoria() + "</td>");
            out.println("  <td>" + fornitore.getRecapito()+ "</td>");
            out.println("  <td>" + fornitore.getIdOrdine()+ "</td>");
            out.println("</tr>");
        });
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
    }

    private void dettaglioFornitore(HttpServletResponse resp, long ID, boolean disabled) throws IOException {
        FornitoreDTO fornitore = null;
        String messaggioErrore = null;

//        if (ID == -1) {
//            fornitore = new FornitoreDTO(-1, -1, "", "", "", -1);
//            fornitore.setId(-1);
//            try {
//                MagazzinoDAO magazzinoDAO = new MagazzinoDAO();
//                fornitore = magazzinoDAO.getFornitore(fornitore);
//            } catch (MagazzinoException ex) {
//                messaggioErrore = "Impossibile leggere i dati dal database";
//            }
//
//            try (PrintWriter out = resp.getWriter()) {
//                this.apriHTML(out, messaggioErrore, "$$fornitori$$");
//
//                if (fornitore != null) {
//                    this.creaDettaglioFornitore(out, fornitore, disabled);
//                }
//                this.chiudiHTML(out);
//            }
//        }
    }

    private void creaDettaglioFornitore(PrintWriter out, FornitoreDTO fornitore, boolean disabled) {
        out.println("<form action='clienti' method='POST' class='container-fluid'>");
        out.println("<div class='panel panel-default'>");
        out.println("<div class='panel-heading'>");
        out.println("<div class='panel-title'>Dettaglio Fornitore</div>");
        out.println("</div>");
        out.println("<div class='panel-body'>");
        out.println("<input type='hidden' name='ID' value='" + fornitore.getId()+ "'/>");
        this.creaDatiAnagrafici(out, fornitore, disabled);
        this.creaTabellaOrdini(out, fornitore);
        out.println("</div>");
        out.println("<div class='panel-footer text-right'>");
        if (!disabled) {
            out.println("<input type='submit' class='btn btn-primary' value='Salva Modifiche' />");
        }
        out.println("</div>");
        out.println("</div>");
        out.println("</form>");
    }

    private void creaDatiAnagrafici(PrintWriter out, FornitoreDTO fornitore, boolean disabled) {
        out.println("<div class='row'>");
        out.println(" <div class='col-sm-6'>");
        out.println("<label>Nome</label>");
        out.println(" <input type='text' name='nome' class='form-control' value='" + fornitore.getNome() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println(" </div>");
        out.println(" <div class='col-sm-6'>");
        out.println(" <label>Cognome</label>");
        out.println(" <input type='text' name='indirizzo' class='form-control' value='" + fornitore.getIndirizzo()+ "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println(" </div> ");
        out.println(" </div>");
        out.println(" <div class='row'>");
        out.println(" <div class='col-sm-6'>");
        out.println(" <label>Data di Nascita</label>");
        out.println("<input type='date' name='idCategoria' class='form-control' value='" + fornitore.getIdCategoria()+ "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("<div class='col-sm-6'>");
        out.println(" <label>Codice Fiscale</label>");
        out.println(" <input type='text' name='recapito' class='form-control' value='" + fornitore.getRecapito()+ "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println(" </div>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-12'>");
        out.println(" <label>Mail</label>");
        out.println("<input type='email' name='idOrdine' class='form-control' value='" + fornitore.getIdOrdine()+ "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div> ");
        out.println("</div>");

    }

    private void creaTabellaOrdini(PrintWriter out, FornitoreDTO fornitore) {
        out.println("<div class='row'>");
        out.println("<div class='col-sm-12'>");
        out.println("<h3>Abbonamenti</h3>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='table-responsive'>");
        out.println("<table class='table table-striped'>");
        out.println(THEAD_ORDINI);
        out.println("<tbody>");
//        fornitore.getOrdini().forEach(ordine -> {
//            out.println("<tr>");
//            out.println("<td>" + ordine.getDescrizione() + "</td>");
//            out.println("<td>" + ordine.getDurata() + " mesi</td>");
//            out.println("<td>" + ordine.getCosto() + " €</td>");
//            out.println("<td>" + ordine.getMetodoPagamento() + "</td>");
//            out.println("<td>" + ordine.getDataSottoscrizione().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</td>");
//            out.println("</tr>");
//        });
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");

    }
}
