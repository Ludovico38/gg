/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.magazzino.utilities;

/**
 *
 * @author forIT
 */
public class Queries {

    public static final String GET_PRODOTTI = "SELECT * FROM prodotto";
    public static final String GET_CATEGORIE = "SELECT * FROM categoria";
    public static final String GET_MAGAZZINIERI="SELECT m.ID,m.NOME,m.COGNOME,m.CODICE_FISCALE,m.DATA_NASCITA,m.PATENTE "
            + "FROM magazziniere as m;";
    public static final String GET_SCAFFALI = "SELECT * FROM scaffale";
    public static final String GET_SCAFFALE = "SELECT * FROM scaffale WHERE ID=?";
    public static final String GET_VEICOLI ="SELECT v.ID,tv.DESCRIZIONE,v.PATENTE_RICHIESTA "
            + "FROM veicolo as v,tipo_veicolo as tv "
            + "WHERE v.ID_TIPO_VEICOLO=tv.ID;";
    public static final String GET_FORNITORI="SELECT * FROM magazzino.fornitore";
    public static final String GET_FORNITORE="SELECT * FROM magazzino.fornitore WHERE ID=?";
    public static final String GET_PRODOTTI_WITH_NAME = "SELECT * FROM prodotto WHERE nome=?";
    public static final String GET_PRODOTTO_WITH_ID = "SELECT * FROM prodotto WHERE ID=?";
    public static final String INSERT_PRODOTTI = "INSERT INTO prodotto (NOME,PREZZO,SCADENZA,PROVENIENZA,ID_FORNITORE) values(?,?,?,?,?)";
    public static final String UPDATE_PRODOTTI = "UPDATE prodotto SET NOME=?,PREZZO=?,SCADENZA=?,PROVENIENZA=?,ID_FORNITORE=? WHERE ID=?";
    public static final String PAYMENTS_BY_SUPPLIER = "Select f.NOME,sum(oxp.PREZZO_DI_ACQUISTO*oxp.QUANTITA) as PAGAMENTO"
            + " from ordine_x_prodotto as oxp, fornitore as f"
            + " where f.ID_ORDINE=oxp.ID AND (oxp.PREZZO_DI_ACQUISTO*oxp.QUANTITA>? AND oxp.PREZZO_DI_ACQUISTO*oxp.QUANTITA<?)"
            + " group by f.nome"
            + " order by f.nome";
    public static final String GET_PRODUCTS_DETAILS = "Select p.ID,p.NOME ,p.PREZZO,p.PROVENIENZA ,p.SCADENZA ,s.ID AS ID_SCAFFALE ,c.DESCRIZIONE AS CATEGORIA ,oxp.PREZZO_DI_ACQUISTO"
            + " ,sum(oxp.QUANTITA)as QUANTITA_ACQUISTATI , oxp.PREZZO_DI_ACQUISTO*sum(oxp.QUANTITA) as SPESA_TOTALE ,v.QUANTITA as QUANTITA_VENDUTE"
            + " ,p.PREZZO*v.QUANTITA as RITORNO ,f.NOME AS NOME_FORNITORE"
            + " from prodotto as p ,scaffale_x_prodotto as sxp ,scaffale as s ,ordine_x_prodotto as oxp ,ordine as o ,fornitore as f"
            + " ,categoria as c ,vendite as v"
            + " where p.ID_FORNITORE=f.ID AND f.ID_ORDINE=o.ID AND o.ID_ORDINE_X_PRODOTTO=oxp.ID AND oxp.ID_PRODOTTO=p.ID AND p.ID=sxp.ID_PRODOTTO "
            + " AND sxp.ID_SCAFFALE=s.ID AND s.ID_CATEGORIA=c.ID AND v.ID_PRODOTTO=p.ID"
            + " group by p.nome";
}
