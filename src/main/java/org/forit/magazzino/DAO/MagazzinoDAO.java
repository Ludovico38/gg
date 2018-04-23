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

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static final String listaMagazzinieri
            = "SELECT tv.ID, m.NOME,m.COGNOME,m.PATENTE,m.ID_VEICOLO "
            + "FROM veicolo v,tipo_veicolo tv, magazziniere m "
            + "WHERE tv.ID = v.ID_TIPO_VEICOLO AND m.ID_VEICOLO = v.ID "
            + "ORDER BY m.COGNOME";
    private static final String inserisciMagazziniere
            = "INSERT INTO magazziniere (NOME,COGNOME,CODICE_FISCALE,DATA_NASCITA,PATENTE,ID_VEICOLO)"
            + "VALUES (?,?,?,?,?,?)";
    private static final String modificaMagazziniere
            = "UPDATE magazziniere "
            + "SET NOME = ?, PATENTE = ?, COGNOME = ?, CODICE_FISCALE = ?, DATA_DI_NASCITA = ?, ID_PATENTE = ? "
            + "WHERE ID = ?";
    private static final String modificaMagazziniere2
            = "UPDATE magazziniere "
            + "SET NOME = ?, PATENTE = ?, COGNOME = ?, CODICE_FISCALE = ?, DATA_DI_NASCITA = ?, ID_PATENTE = ? "
            + "WHERE CODICE_FISCALE = ?";

    public List<MagazziniereDTO> getListaMagazziniere() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(listaMagazzinieri)) {

            List<MagazziniereDTO> listaMagazziniere = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cognome = rs.getString("COGNOME");
                String patente = rs.getString("PATENTE");
                long idveicolo = rs.getLong("ID_VEICOLO");

                MagazziniereDTO Magazziniere = new MagazziniereDTO(id, nome, cognome, patente, idveicolo);
                listaMagazziniere.add(Magazziniere);
            }
            return listaMagazziniere;
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore" + ex.getMessage());
            throw new MagazzinoException(ex);
        }
    }

    public void insertMagazzinieri(String nome, String cognome, String codiceFiscale, LocalDate datanascita, String patente, long idveicolo) throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(inserisciMagazziniere, Statement.RETURN_GENERATED_KEYS)) {

                ps1.setString(1, nome);
                ps1.setString(2, cognome);
                ps1.setString(3, codiceFiscale);
                ps1.setDate(4, Date.valueOf(datanascita));
                ps1.setString(5, patente);
                ps1.setLong(6, idveicolo);
                ps1.executeUpdate();

                ResultSet generatedKey = ps1.getGeneratedKeys();
                generatedKey.next();
                //long Id = generatedKey.getLong(1);

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore" + ex.getMessage());

        }
    }

    public void updateMagazziniere() throws MagazzinoException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps2 = conn.prepareStatement(modificaMagazziniere, Statement.RETURN_GENERATED_KEYS)) {

            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore" + ex.getMessage());
        }
    }
}
