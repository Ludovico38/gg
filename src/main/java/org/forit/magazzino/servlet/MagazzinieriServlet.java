/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.forit.magazzino.DAO.MagazzinoDAO;
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.Exception.MagazzinoException;
import org.forit.magazzino.utilities.HTMLElements;

/**
 *
 * @author forIT
 */
public class MagazzinieriServlet extends HttpServlet {

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
        List<MagazziniereDTO> listaMagazzinieri=null;
        boolean error = false;
        try {
            listaMagazzinieri = magazzino.getListaMagazzinieri();
        } catch (MagazzinoException ex) {
            error = true;
        }
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(HTMLElements.HEAD);
            out.println("<body>");
            out.println(HTMLElements.NAVBAR);
            out.println(HTMLElements.RICERCA_MAGAZZINIERE);
            if (!error) {
                out.println(HTMLElements.getTabellaMagazzinieri(listaMagazzinieri));
            } else {
                out.println("<h2>IMPOSSIBILE CARICARE GLI SCAFFALI</h2>");
            }
            out.println(HTMLElements.FOOTER);
            out.println("</body>");
            out.println("</html>");
        }
    }
}
