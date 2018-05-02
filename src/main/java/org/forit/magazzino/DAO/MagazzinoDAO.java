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
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.forit.magazzino.DTO.CategoriaDTO;
import org.forit.magazzino.DTO.FornitoreDTO;
import org.forit.magazzino.utilities.Queries;
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.DTO.PaymentToSupplierDTO;
import org.forit.magazzino.DTO.ProdottoDTO;
import org.forit.magazzino.DTO.ProductDetailsDTO;
import org.forit.magazzino.DTO.ScaffaleDTO;
import org.forit.magazzino.DTO.VeicoloDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author forIT
 */
public class MagazzinoDAO {
    
    public final static String DB_URL = "jdbc:mysql://localhost:3306/magazzino?useSSL=false&user=forit&password=12345";
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public ProdottoDTO getProdotto(long id) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pst = conn.prepareStatement(Queries.GET_PRODOTTO_WITH_ID)) {
            LocalDate scadenza;
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            long idFornitore = rs.getLong("ID_FORNITORE");
            String nome = rs.getString("NOME");
            BigDecimal prezzo = rs.getBigDecimal("PREZZO");
            String provenienza = rs.getString("PROVENIENZA");
            Date dataScadenza = rs.getDate("SCADENZA");
            if (dataScadenza == null) {
                scadenza = LocalDate.of(9999, Month.JANUARY, 1);
            } else {
                scadenza = dataScadenza.toLocalDate();
            }
            return new ProdottoDTO(id, nome, prezzo, scadenza, provenienza, idFornitore);
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public List<ProdottoDTO> getListaProdotti() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_PRODOTTI)) {
            List<ProdottoDTO> listaProdotti = new ArrayList<>();
            LocalDate scadenza;
            while (rs.next()) {
                long ID = rs.getLong("ID");
                String nome = rs.getString("NOME");
                BigDecimal prezzo = new BigDecimal(rs.getDouble("PREZZO"));
                long idFornitore = rs.getLong("ID_FORNITORE");
                if (rs.getDate("SCADENZA") == null) {
                    scadenza = LocalDate.now();
                } else {
                    scadenza = rs.getDate("SCADENZA").toLocalDate();
                }
                String provenienza = rs.getString("PROVENIENZA");
                listaProdotti.add(new ProdottoDTO(ID, nome, prezzo, scadenza, provenienza, idFornitore));
            }
            return listaProdotti;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public Map<Long, String> getListaCategorie() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_CATEGORIE)) {
            Map<Long, String> listaCategorie = new LinkedHashMap<>();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String nome = rs.getString("DESCRIZIONE");
                listaCategorie.put(id, nome);
            }
            return listaCategorie;
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
            while (rs.next()) {
                long id = rs.getLong("ID"),
                        idCategoria = rs.getLong("ID_CATEGORIA"),
                        idCrossScaffaleProdotto = rs.getLong("ID_SCAFFALE_X_PRODOTTO"),
                        idMagazziniere = rs.getLong("ID_MAGAZZINIERE");
                listaScaffali.add(new ScaffaleDTO(id, idCategoria, idMagazziniere, idCrossScaffaleProdotto));
            }
            return listaScaffali;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public ScaffaleDTO getScaffale(long id) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pst = conn.prepareStatement(Queries.GET_SCAFFALE)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            long idCategoria = rs.getLong("ID_CATEGORIA"),
                    idCrossScaffaleProdotto = rs.getLong("ID_SCAFFALE_X_PRODOTTO"),
                    idMagazziniere = rs.getLong("ID_MAGAZZINIERE");
            return new ScaffaleDTO(id, idCategoria, idMagazziniere, idCrossScaffaleProdotto);
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
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
    
    public List<VeicoloDTO> getListaVeicoli() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_VEICOLI)) {
            List<VeicoloDTO> listaVeicoli = new ArrayList<>();
            long ID;
            String tipoVeicolo, patenteRichiesta;
            while (rs.next()) {
                ID = rs.getLong("ID");
                tipoVeicolo = rs.getString("DESCRIZIONE");
                patenteRichiesta = rs.getString("PATENTE_RICHIESTA");
                listaVeicoli.add(new VeicoloDTO(ID, tipoVeicolo, patenteRichiesta));
            }
            return listaVeicoli;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public Map<Long, FornitoreDTO> getListaFornitori() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(Queries.GET_FORNITORI)) {
            Map<Long, FornitoreDTO> listaFornitori = new LinkedHashMap<>();
            long id, idCategoria, idOrdine;
            String nome, indirizzo, recapito;
            while (rs.next()) {
                id = rs.getLong("ID");
                idCategoria = rs.getLong("ID_CATEGORIA");
                idOrdine = rs.getLong("ID_ORDINE");
                nome = rs.getString("NOME");
                indirizzo = rs.getString("INDIRIZZO");
                recapito = rs.getString("RECAPITO");
                listaFornitori.put(id, new FornitoreDTO(id, idCategoria, idOrdine, nome, indirizzo, recapito));
            }
            return listaFornitori;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public FornitoreDTO getFornitore(long id) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pst = conn.prepareStatement(Queries.GET_FORNITORE)) {
            FornitoreDTO fornitore;
            long idCategoria, idOrdine;
            String nome, indirizzo, recapito;
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            idCategoria = rs.getLong("ID_CATEGORIA");
            idOrdine = rs.getLong("ID_ORDINE");
            nome = rs.getString("NOME");
            indirizzo = rs.getString("INDIRIZZO");
            recapito = rs.getString("RECAPITO");
            fornitore = new FornitoreDTO(id, idCategoria, idOrdine, nome, indirizzo, recapito);
            return fornitore;
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
    
    public void insertScaffale(ScaffaleDTO scaffale) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pst = conn.prepareStatement(Queries.INSERT_SCAFFALE)) {
            pst.setLong(1, scaffale.getIdCategoria());
            pst.setLong(2, scaffale.getIdCrossScaffaleProdotto());
            pst.setLong(3, scaffale.getIdMagazziniere());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public void updateProdotto(ProdottoDTO prodotto) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement st = conn.prepareStatement(Queries.UPDATE_PRODOTTI)) {
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
    
    public void updateScaffale(ScaffaleDTO scaffale) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pst = conn.prepareStatement(Queries.INSERT_SCAFFALE)) {
            pst.setLong(1, scaffale.getIdCategoria());
            pst.setLong(2, scaffale.getIdCrossScaffaleProdotto());
            pst.setLong(3, scaffale.getIdMagazziniere());
            pst.setLong(4, scaffale.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public void deleteProdotto(long id) throws MagazzinoException{
        try(Connection conn=DriverManager.getConnection(DB_URL);
                PreparedStatement pst=conn.prepareStatement(Queries.DELETE_PRODOTTO)){
            pst.setLong(1, id);
            pst.executeUpdate();
        }catch(SQLException ex){
            throw new MagazzinoException(ex);
        }
    }
    
    public void deleteScaffale(long id) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pst = conn.prepareStatement(Queries.INSERT_SCAFFALE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
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
            long id_scaffale, id_prodotto;
            int quantita_acquistati, quantita_venduti;
            while (rs.next()) {
                id_prodotto = rs.getLong("ID");
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
                listaDettagli.add(new ProductDetailsDTO(id_prodotto, nome, prezzo_vendita, provenienza, scadenza, id_scaffale, categoria, prezzo_acquisto, quantita_acquistati, spesa_totale, quantita_venduti, ritorno, nome_fornitore));
            }
            return listaDettagli;
        } catch (SQLException ex) {
            System.out.println("ERRORE:" + ex);
            throw new MagazzinoException(ex);
        }
    }
    
    public ProductDetailsDTO getProductDetail(long id_prodotto) throws MagazzinoException {
        for (ProductDetailsDTO prodotto : getProductDetails()) {
            if (prodotto.getId() == id_prodotto) {
                return prodotto;
            }
        }
        return null;
    }
}
