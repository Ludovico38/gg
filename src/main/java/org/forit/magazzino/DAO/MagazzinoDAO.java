/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.DAO;

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
import org.forit.magazzino.DTO.MagazziniereDTO;
import org.forit.magazzino.Exception.MagazzinoException;

/**
 *
 * @author UTENTE
 */
public class MagazzinoDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/magazzino?user=forit&password=12345";

    private static final String listaMagazzinieri
            = "SELECT m.* "
            + "FROM magazziniere m";
    private static final String inserisciMagazziniere
            = "INSERT INTO magazziniere (NOME,COGNOME,CODICE_FISCALE,DATA_NASCITA,PATENTE,ID_VEICOLO)"
            + "VALUES (?,?,?,?,?,?)";
    private static final String modificaMagazziniere
            = "UPDATE magazziniere "
            + "SET NOME = ?, COGNOME = ?, CODICE_FISCALE = ?, DATA_NASCITA = ?, PATENTE = ?, ID_VEICOLO = ? "
            + "WHERE ID = ?";
//    private static final String modificaMagazziniere2
//            = "UPDATE magazziniere "
//            + "SET NOME = ?, PATENTE = ?, COGNOME = ?, CODICE_FISCALE = ?, DATA_DI_NASCITA = ?, ID_PATENTE = ? "
//            + "WHERE CODICE_FISCALE = ?";
    private static final String magazziniere
            = "SELECT m.* , v.ID_TIPO_VEICOLO, tv.DESCRIZIONE "
            + "FROM magazziniere m, veicolo v, tipo_veicolo tv "
            + "WHERE m.ID = ? and v.ID_TIPO_VEICOLO = tv.ID";
    public static final String DELETE_MAGAZZINIERE
            = "DELETE FROM magazziniere "
            + "WHERE id = ?";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public List<MagazziniereDTO> getListaMagazziniere() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(listaMagazzinieri)) {

            List<MagazziniereDTO> listaMagazziniere = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cognome = rs.getString("COGNOME");
                String codiceFiscale = rs.getString("CODICE_FISCALE");
                LocalDate dataDiNascita = rs.getDate("DATA_NASCITA").toLocalDate();
                String patente = rs.getString("PATENTE");
                long idveicolo = rs.getLong("ID_VEICOLO");

                MagazziniereDTO Magazziniere = new MagazziniereDTO(id, nome, cognome, codiceFiscale, dataDiNascita, patente, idveicolo);
                listaMagazziniere.add(Magazziniere);
            }
            return listaMagazziniere;
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore" + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }

    public void insertMagazzinieri(long ID, String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita, String patente, long idveicolo) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(inserisciMagazziniere, Statement.RETURN_GENERATED_KEYS)) {

                ps1.setString(1, nome);
                ps1.setString(2, cognome);
                ps1.setString(3, codiceFiscale);
                ps1.setDate(4, Date.valueOf(dataDiNascita));
                ps1.setString(5, patente);
                ps1.setLong(6, idveicolo);
                ps1.executeUpdate();

                ResultSet generatedKey = ps1.getGeneratedKeys();
                generatedKey.next();
                long Id = generatedKey.getLong(1);

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore" + ex.getMessage());

        }
    }

    public void updateMagazziniere(long id, String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita, String patente, long idVeicolo) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(modificaMagazziniere)) {

            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, codiceFiscale);
            ps.setDate(4, Date.valueOf(dataDiNascita));
            ps.setString(5, patente);
            ps.setLong(6, idVeicolo);
            ps.setLong(7, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new MagazzinoException(ex);
        }
    }

    public MagazziniereDTO getMagazziniere(long id) throws MagazzinoException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps1 = conn.prepareStatement(magazziniere)) {
            ps1.setLong(1, id);
            ResultSet rs = ps1.executeQuery();
            rs.next();

            id = rs.getLong("ID");
            String nome = rs.getString("NOME");
            String cognome = rs.getString("COGNOME");
            String codicefiscale = rs.getString("CODICE_FISCALE");
            LocalDate dataDiNascita = rs.getDate("DATA_NASCITA").toLocalDate();
            String patente = rs.getString("PATENTE");
            Long idveicolo = rs.getLong("ID_VEICOLO");

            MagazziniereDTO magazziniere = new MagazziniereDTO(id, nome, cognome, codicefiscale, dataDiNascita, patente, idveicolo);

            return magazziniere;
        } catch (SQLException ex) {
            throw new MagazzinoException(ex);
        }
    }

    public void deleteMagazziniere(long id) throws MagazzinoException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(DELETE_MAGAZZINIERE);) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore: " + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }

}
