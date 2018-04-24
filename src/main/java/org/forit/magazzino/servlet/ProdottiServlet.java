/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.ProdottoDTO;
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

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            writePage(out, action);
        } catch (MagazzinoException ex) {
            Logger.getLogger(ProdottiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writePage(PrintWriter out, String action) throws MagazzinoException {
        MagazzinoDAO magazzino = new MagazzinoDAO();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println(HTMLElements.HEAD);
        out.println(HTMLElements.NAVBAR);
        if (action == null) {
            out.println(HTMLElements.RICERCA_PRODOTTO);
            writeListaProdotti(out, magazzino);
        } else {
            switch (action) {
                case "view":
                    break;
            }
        }
        out.println(HTMLElements.FOOTER);
        out.println("</html>");
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
