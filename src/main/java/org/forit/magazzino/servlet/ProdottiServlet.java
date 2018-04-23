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
import org.forit.DAO.MagazzinoDAO;
import org.forit.DTO.ProdottoDTO;
import org.forit.Exception.MagazzinoException;
import org.forit.classi.HTMLElements;

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
        MagazzinoDAO magazzino = new MagazzinoDAO();
        List<ProdottoDTO> listaprodotti = null;
        boolean errore = false;
        try {
            listaprodotti = magazzino.getListaProdotti();
        } catch (MagazzinoException ex) {
            errore = true;
        }
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(HTMLElements.HEAD);
            out.println("<body>");
            out.println(HTMLElements.NAVBAR);
            out.println(HTMLElements.RICERCA_PRODOTTO);
            if (!errore) {
                out.println(HTMLElements.getTabellaProdotti(magazzino.getListaProdotti()));
            } else {
                out.println("<h2>IMPOSSIBILE CARICARE I PRODOTTI</h2>");
            }
            out.println(HTMLElements.FOOTER);
            out.println("</body>");
            out.println("</html>");
        } catch (MagazzinoException ex) {
            Logger.getLogger(ProdottiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
