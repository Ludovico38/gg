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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.ProdottoDTO;
import org.forit.magazzino.DTO.ProductDetailsDTO;
import org.forit.magazzino.Exception.MagazzinoException;
import org.forit.magazzino.utilities.HTMLElements;

/**
 *
 * @author forIT
 */
public class ProdottiServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdottoDTO prodotto;
        MagazzinoDAO magazzino=new MagazzinoDAO();
        long id=Long.parseLong(req.getParameter("id_prodotto"));
        String nome=req.getParameter("nome_prodotto"),provenienza=req.getParameter("provenienza_prodotto");
        LocalDate data=LocalDate.parse(req.getParameter("scadenza_prodotto"));
        BigDecimal prezzo=new BigDecimal(req.getParameter("prezzo_prodotto"));
        prodotto= new ProdottoDTO(id, nome, prezzo, data, provenienza);
        if(id==-1){
            try {
                magazzino.insertProdotto(prodotto);
            } catch (MagazzinoException ex) {
                System.out.println("ERRORE INSERIMENTO PRODOTTO");
            }
        }else{
            try {
                magazzino.updateProdotto(prodotto);
            } catch (MagazzinoException ex) {
                System.out.println("ERRORE AGGIORNAMENTO PRODOTTO");
            }
        }
        resp.sendRedirect("prodotti");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            writePage(out, req);
        } catch (MagazzinoException ex) {
            Logger.getLogger(ProdottiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writePage(PrintWriter out, HttpServletRequest req) throws MagazzinoException {
        MagazzinoDAO magazzino = new MagazzinoDAO();
        String action = req.getParameter("action");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println(HTMLElements.HEAD);
        out.println(HTMLElements.NAVBAR);
        if (action == null) {
            writeListaProdotti(out, magazzino);
        } else {
            long id = -1;
            boolean disabled = false;
            if (!action.equals("new")) {
                id = Long.parseLong(req.getParameter("ID"));
            }
            if (action.equals("view")) {
                disabled = true;
            }
            writeDettagli(out, magazzino, id, disabled);
        }
        out.println(HTMLElements.FOOTER);
        out.println("</html>");
    }

    public static void writeDettagli(PrintWriter out, MagazzinoDAO magazzino, long id_prodotto, boolean disabled) throws MagazzinoException {
        if (id_prodotto == -1) {
            out.println(HTMLElements.getPannelloProdotto(new ProductDetailsDTO(id_prodotto),disabled));
            return;
        }
        ProductDetailsDTO dettaglio = magazzino.getProductDetail(id_prodotto);
        if (dettaglio != null) {
            out.println(HTMLElements.getDettagliProdotto(dettaglio, disabled));
        } else {
            out.println("<h3>ERRORE</h3>");
        }
    }

    public static void writeListaProdotti(PrintWriter out, MagazzinoDAO magazzino) throws MagazzinoException {
        List<ProdottoDTO> listaprodotti = null;
        boolean errore = false;
        try {
            listaprodotti = magazzino.getListaProdotti();
        } catch (MagazzinoException ex) {
            errore = true;
        }
        if (!errore) {
            out.println(HTMLElements.getTabellaProdotti(magazzino.getListaProdotti()));
        } else {
            out.println("<h2>IMPOSSIBILE CARICARE I PRODOTTI</h2>");
        }
    }

}
