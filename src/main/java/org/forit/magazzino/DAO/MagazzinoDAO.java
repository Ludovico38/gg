/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DAO;

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
import org.forit.magazzino.DTO.FornitoreDTO;
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.DTO.PaymentToSupplierDTO;
import org.forit.magazzino.DTO.ProdottoDTO;
import org.forit.magazzino.DTO.ProductDetailsDTO;
import org.forit.magazzino.DTO.ScaffaleDTO;
import org.forit.magazzino.Exception.MagazzinoException;
import org.forit.magazzino.classes.Queries;
import static org.forit.magazzino.classes.Queries.INSERT_FORNITORE;
import static org.forit.magazzino.classes.Queries.INSERT_MAGAZZINIERE;
import static org.forit.magazzino.classes.Queries.UPDATE_FORNITORE;
import static org.forit.magazzino.classes.Queries.UPDATE_MAGAZZINIERE;

/**
 *
 * @author forIT
 */
public class MagazzinoDAO {

    public final static String DB_URL = "jdbc:mysql://localhost:3306/magazzino?useSSL=false&user=ForIT&password=12345";

    public final static String LISTA_FORNITORI = "SELECT * FROM fornitore";
    private static final String FORNITORE
            = "SELECT f.* "
            + "FROM fornitore f "
            + "WHERE f.ID=? ";

    private static final String MAGAZZINIERE
            = "SELECT m.* "
            + "FROM magazziniere m "
            + "WHERE m.ID=? ";

    
    static {
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
                prezzo = rs.getBigDecimal("PREZZO");
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

    public ProdottoDTO getProdotto(long id) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pst = conn.prepareStatement(Queries.GET_PRODOTTO_WITH_ID)) {
            long idProdotto;
            BigDecimal prezzo;
            String nome, provenienza;
            LocalDate scadenza;
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            idProdotto = rs.getLong("ID");
            nome = rs.getString("NOME");
            prezzo = rs.getBigDecimal("PREZZO");
            provenienza = rs.getString("PROVENIENZA");
            scadenza = rs.getDate("SCADENZA").toLocalDate();
            return new ProdottoDTO(idProdotto, nome, prezzo, scadenza, provenienza, idProdotto);
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
                Statement st = conn.createStatement();
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

    public void updateProdotto(ProdottoDTO prodotto) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement st = conn.prepareStatement(Queries.UPDATE_PRODOTTO)) {
            st.setString(1, prodotto.getNome());
            st.setBigDecimal(2, prodotto.getPrezzo());
            st.setDate(3, Date.valueOf(prodotto.getScadenza()));
            st.setString(4, prodotto.getProvenienza());
            st.setLong(5, prodotto.getIdFornitore());
            st.setLong(6, prodotto.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }

    }

    public List<FornitoreDTO> getListaFornitori() throws MagazzinoException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement st = conn.prepareStatement(LISTA_FORNITORI);
                ResultSet rs = st.executeQuery()) {

            List<FornitoreDTO> listaFornitori = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("ID");
                long idCategoria = rs.getLong("ID_CATEGORIA");
                long idOrdine = rs.getLong("ID_ORDINE");
                String nome = rs.getString("NOME");
                String indirizzo = rs.getString("INDIRIZZO");
                String recapito = rs.getString("RECAPITO");

                FornitoreDTO fornitore = new FornitoreDTO(id, idCategoria, nome, indirizzo, recapito, idOrdine);
                listaFornitori.add(fornitore);
            }
            return listaFornitori;
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }

    public FornitoreDTO getFornitore(long ID) throws MagazzinoException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement st = conn.prepareStatement(FORNITORE);) {

            st.setLong(1, ID);
            ResultSet rs = st.executeQuery();
            rs.next();
            long id = rs.getLong("ID");
            long idCategoria = rs.getLong("ID_categoria");
            String nome = rs.getString("NOME");
            String indirizzo = rs.getString("INDIRIZZO");
            String recapito = rs.getString("RECAPITO");
            long idOrdine = rs.getLong("ID_ORDINE");

            FornitoreDTO fornitore = new FornitoreDTO(id, idCategoria, nome, indirizzo, recapito, idOrdine);

            return fornitore;
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }

    public void insertFornitore(long id, long idCategoria, String nome, String indirizzo, String recapito, long idOrdine) throws MagazzinoException {

        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(INSERT_FORNITORE);) {

            ps.setString(1, nome);
            ps.setString(2, indirizzo);
            ps.setLong(3, idCategoria);
            ps.setString(4, recapito);
            ps.setLong(5, idOrdine);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }

    public void updateFornitore(long id, long idCategoria, String nome, String indirizzo, String recapito, long idOrdine) throws MagazzinoException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(UPDATE_FORNITORE);) {

            ps.setString(1, nome);
            ps.setString(2, indirizzo);
            ps.setLong(3, idCategoria);
            ps.setString(4, recapito);
            ps.setLong(5, idOrdine);
            ps.setLong(6, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }

    public MagazziniereDTO getMagazziniere(long ID) throws MagazzinoException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement st = conn.prepareStatement(MAGAZZINIERE);) {

            st.setLong(1, ID);
            ResultSet rs = st.executeQuery();
            rs.next();
            long id = rs.getLong("ID");
            String nome = rs.getString("NOME");
            String cognome = rs.getString("COGNOME");
            String codiceFiscale = rs.getString("CODICE_FISCALE");
            LocalDate dataNascita = rs.getDate("DATA_NASCITA").toLocalDate();
            String patente = rs.getString("PATENTE");
            long idVeicolo = rs.getLong("ID_VEICOLO");

            MagazziniereDTO magazziniere = new MagazziniereDTO(id, nome, cognome, codiceFiscale, dataNascita, patente, idVeicolo);

            return magazziniere;
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }

    public void insertMagazziniere(long id, String nome, String cognome, String codiceFiscale, LocalDate dataNascita, String patente, long idVeicolo) throws MagazzinoException {

        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(INSERT_MAGAZZINIERE);) {

            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, codiceFiscale);
            ps.setDate(4, Date.valueOf(dataNascita));
            ps.setString(5, patente);
            ps.setLong(6, idVeicolo);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }

    public void updateMagazziniere(long id, String nome, String cognome, String codiceFiscale, LocalDate dataNascita, String patente, long idVeicolo) throws MagazzinoException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(UPDATE_MAGAZZINIERE);) {

            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, codiceFiscale);
            ps.setDate(4, Date.valueOf(dataNascita));
            ps.setString(5, patente);
            ps.setLong(6, idVeicolo);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }
    
    public List<MagazziniereDTO> getListaMagazzinieri() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_MAGAZZINIERI)) {
            List<MagazziniereDTO> listaMagazzinieri = new ArrayList<>();
            long ID;
            String nome, cognome, codiceFiscale, patente;
            LocalDate dataNascita;
            while (rs.next()) {
                ID = rs.getLong("ID");
                nome = rs.getString("NOME");
                cognome = rs.getString("COGNOME");
                codiceFiscale = rs.getString("CODICE_FISCALE");
                patente = rs.getString("PATENTE");
                dataNascita = rs.getDate("DATA_NASCITA").toLocalDate();
                listaMagazzinieri.add(new MagazziniereDTO(ID, nome, cognome, codiceFiscale, dataNascita, patente));
            }
            return listaMagazzinieri;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
}
