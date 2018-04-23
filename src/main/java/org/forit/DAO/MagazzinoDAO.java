/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.forit.classi.Queries;
import org.forit.DTO.MagazziniereDTO;
import org.forit.DTO.PaymentToSupplierDTO;
import org.forit.DTO.ProdottoDTO;
import org.forit.DTO.ProductDetailsDTO;
import org.forit.DTO.ScaffaleDTO;
import org.forit.DTO.VeicoloDTO;
import org.forit.Exception.MagazzinoException;

/**
 *
 * @author forIT
 */
public class MagazzinoDAO {

    public final static String DB_URL = "jdbc:mysql://localhost:3306/magazzino?useSSL=false&user=forit&password=12345";

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<ProdottoDTO> getListaProdotti() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_PRODOTTI)) {
            List<ProdottoDTO> listaProdotti = new ArrayList<>();
            long ID;
            BigDecimal prezzo;
            String nome, provenienza;
            LocalDate scadenza;
            while (rs.next()) {
                ID = rs.getLong("ID");
                nome = rs.getString("NOME");
                prezzo = new BigDecimal(rs.getDouble("PREZZO"));
                if (rs.getDate("SCADENZA") == null) {
                    scadenza = LocalDate.now();
                } else {
                    scadenza = rs.getDate("SCADENZA").toLocalDate();
                }
                provenienza = rs.getString("PROVENIENZA");
                listaProdotti.add(new ProdottoDTO(ID, nome, prezzo, scadenza, provenienza));
            }
            return listaProdotti;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }

    public List<ScaffaleDTO> getListaScaffali() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_SCAFFALI)) {
            List<ScaffaleDTO> listaScaffali = new ArrayList<>();
            long ID;
            String descrizione;
            while (rs.next()) {
                ID = rs.getLong("ID");
                descrizione = rs.getString("DESCRIZIONE");
                listaScaffali.add(new ScaffaleDTO(ID, descrizione));
            }
            return listaScaffali;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public List<MagazziniereDTO> getListaMagazzinieri() throws MagazzinoException{
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_MAGAZZINIERI)) {
            List<MagazziniereDTO> listaMagazzinieri = new ArrayList<>();
            long ID;
            String nome,cognome,codiceFiscale,patente;
            LocalDate dataNascita;
            while (rs.next()) {
                ID = rs.getLong("ID");
                nome = rs.getString("NOME");
                cognome = rs.getString("COGNOME");
                codiceFiscale = rs.getString("CODICE_FISCALE");
                patente = rs.getString("PATENTE");
                dataNascita = rs.getDate("DATA_NASCITA").toLocalDate();
                listaMagazzinieri.add(new MagazziniereDTO(ID, nome,cognome,codiceFiscale,dataNascita,patente));
            }
            return listaMagazzinieri;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public List<VeicoloDTO> getListaVeicoli() throws MagazzinoException{
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_VEICOLI)) {
            List<VeicoloDTO> listaVeicoli = new ArrayList<>();
            long ID;
            String tipoVeicolo,patenteRichiesta;
            while (rs.next()) {
                ID = rs.getLong("ID");
                tipoVeicolo = rs.getString("DESCRIZIONE");
                patenteRichiesta = rs.getString("PATENTE_RICHIESTA");
                listaVeicoli.add(new VeicoloDTO(ID,tipoVeicolo,patenteRichiesta));
            }
            return listaVeicoli;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }

    public void insertProdotto(String nome, BigDecimal prezzo, LocalDate scadenza, String provenienza, long id_fornitore) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement st = conn.prepareStatement(Queries.INSERT_PRODOTTI)) {
            st.setString(1, nome);
            st.setBigDecimal(2, prezzo);
            st.setDate(3, Date.valueOf(scadenza));
            st.setString(4, provenienza);
            st.setLong(5, id_fornitore);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }

    public void insertProdotto(ProdottoDTO prodotto) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement st = conn.prepareStatement(Queries.INSERT_PRODOTTI)) {
            st.setString(1, prodotto.getNome());
            st.setBigDecimal(2, prodotto.getPrezzo());
            st.setDate(3, Date.valueOf(prodotto.getScadenza()));
            st.setString(4, prodotto.getProvenienza());
            st.setLong(5, prodotto.getIdFornitore());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }

    public List<PaymentToSupplierDTO> getPayments(int min, int max) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pst = conn.prepareStatement(Queries.PAYMENTS_BY_SUPPLIER)) {
            List<PaymentToSupplierDTO> listaPagamenti = new ArrayList<>();
            String nome;
            BigDecimal pagamento;
            pst.setInt(1, min);
            pst.setInt(2, max);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nome = rs.getString("NOME");
                pagamento = rs.getBigDecimal("PAGAMENTO");
                listaPagamenti.add(new PaymentToSupplierDTO(nome, pagamento));
            }
            return listaPagamenti;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }

    public List<ProductDetailsDTO> getProductDetails() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st=conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_PRODUCTS_DETAILS)) {
            List<ProductDetailsDTO> listaDettagli = new ArrayList<>();
            String nome, provenienza, categoria, nome_fornitore;
            BigDecimal prezzo_vendita, prezzo_acquisto, spesa_totale, ritorno;
            LocalDate scadenza;
            long id_scaffale;
            int quantita_acquistati, quantita_venduti;
            while (rs.next()) {
                nome = rs.getString("NOME");
                prezzo_vendita = rs.getBigDecimal("PREZZO");
                provenienza = rs.getString("PROVENIENZA");
                if (rs.getDate("SCADENZA") == null) {
                    scadenza = LocalDate.of(9999, 1, 1);
                } else {
                    scadenza = rs.getDate("SCADENZA").toLocalDate();
                }
                id_scaffale = rs.getLong("ID_SCAFFALE");
                categoria = rs.getString("CATEGORIA");
                prezzo_acquisto = rs.getBigDecimal("PREZZO_DI_ACQUISTO");
                quantita_acquistati = rs.getInt("QUANTITA_ACQUISTATI");
                spesa_totale = rs.getBigDecimal("SPESA_TOTALE");
                quantita_venduti = rs.getInt("QUANTITA_VENDUTE");
                ritorno = rs.getBigDecimal("RITORNO");
                nome_fornitore = rs.getString("NOME_FORNITORE");
                listaDettagli.add(new ProductDetailsDTO(nome, prezzo_vendita, provenienza, scadenza, id_scaffale, categoria, prezzo_acquisto, quantita_acquistati, spesa_totale, quantita_venduti, ritorno, nome_fornitore));
            }
            return listaDettagli;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
}
