///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.forit.magazzino.utilities;
//
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import org.forit.magazzino.DTO.MagazziniereDTO;
//import org.forit.magazzino.DTO.ProdottoDTO;
//import org.forit.magazzino.DTO.ProductDetailsDTO;
//import org.forit.magazzino.DTO.ScaffaleDTO;
//import org.forit.magazzino.DTO.VeicoloDTO;
//
///**
// *
// * @author forIT
// */
//public class HTMLElements {
//
//    public static final String HEAD
//            = "    <head>"
//            + "        <title>Prodotti</title>"
//            + "        <meta charset='UTF-8'>"
//            + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
//            + "        <script type='text/javascript' src='js/bootstrap.js'></script>"
//            + "        <link rel='stylesheet' href='css/bootstrap.css' type='text/css'/>"
//            + "        <link rel='icon' href='img/icona_magazzino.png'>"
//            + "    </head>";
//
//    public static final String NAVBAR
//            = "    <body>"
//            + "        <nav class='navbar navbar-default'>"
//            + "            <div class='container-fluid'>"
//            + "                <div class='navbar-header'>"
//            + "                    <button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#navigazione' aria-expanded='false'>"
//            + "                        <span class='sr-only'>Toggle navigation</span>"
//            + "                        <span class='icon-bar'></span>"
//            + "                        <span class='icon-bar'></span>"
//            + "                        <span class='icon-bar'></span>"
//            + "                    </button>"
//            + "                    <a class='navbar-brand' href='index.html'>"
//            + "                        <img alt='Magazzino' src='img/icona_magazzino.png' width='20'></a>"
//            + "                </div>"
//            + ""
//            + "                <div class='collapse navbar-collapse' id='navigazione'>"
//            + "                    <ul class='nav navbar-nav'>"
//            + "                        <li><a href='prodotti'>Prodotti</a></li>"
//            + "                        <li><a href='scaffali'>Scaffali</a></li>"
//            + "                        <li><a href='magazzinieri'>Magazzinieri</a></li>"
//            + "                        <li><a href='veicoli'>Veicoli</a></li>"
//            + "                        <li><a href='fornitori'>Fornitori</a></li>"
//            + "                        <li><a href='#'>Entrate/Uscite</a></li>"
//            + "                    </ul>                                        "
//            + "                </div>"
//            + "            </div>"
//            + "        </nav>";
//
//    public static final String FOOTER
//            = "        <footer class='panel-footer'>"
//            + "            <div class='text-right'>"
//            + "                <span class='text-muted'>Team: Alciati, Arrichiello, Lombardi, Rosmarino</span>"
//            + "            </div>"
//            + "        </footer>"
//            + "    </body>";
//
//    private static final String DETTAGLIO_PRODOTTO
//            = "        <div class='panel panel-body'>"
//            + "            <div class='row'>"
//            + "                <div class='col-sm-6'>"
//            + "                    <input type='hidden' name='id_prodotto' value='id_prodotto_value'/>"
//            + "                    <label class='label label-primary col-sm-2'>Nome prodotto</label>"
//            + "                    <input disabled='' class='input-sm col-sm-8' type='text' value='nome_prodotto_value' name='nome_prodotto'/>"
//            + "                </div>"
//            + "                <div class='col-sm-6'>"
//            + "                    <label class='label label-primary col-sm-2'>Prezzo</label>"
//            + "                    <input disabled='' class='input-sm col-sm-8' type='text' value='prezzo_prodotto_value' name='prezzo_prodotto'/>"
//            + "                </div>"
//            + "            </div>"
//            + "            <div class='row'>"
//            + "                <div class='col-sm-6'>"
//            + "                    <label class='label label-primary col-sm-2'>Provenienza</label>"
//            + "                    <input disabled='' class='input-sm col-sm-8' type='text' value='provenienza_prodotto_value' name='provenienza_prodotto'/>"
//            + "                </div>"
//            + "                <div class='col-sm-6'>"
//            + "                    <label class='label label-primary col-sm-2'>Scadenza</label>"
//            + "                    <input disabled='' class='input-sm col-sm-8' type='text' value='scadenza_prodotto_value' name='scadenza_prodotto'/>"
//            + "                </div>"
//            + "            </div>"
//            + "            <div class='row'>"
//            + "                <div class='col-sm-12'>"
//            + "                    <label class='label label-primary col-sm-2'>Fornitore</label>"
//            + "                    <input disabled='' class='input-sm col-sm-8' type='text' value='fornitore_prodotto_value' name='fornitore_prodotto'/>"
//            + "                </div>"
//            + "            </div>"
//            + "        </div>";
//
//    private static final String STRUTTURA_TABELLA_DETTAGLIO_PRODOTTO
//            = "        <h3>Detagli Prodotto</h3>"
//            + "        <div class='row'>"
//            + "            <div class='col-sm-12'>"
//            + "                <table class='table table-striped table-responsive'>"
//            + "                    <thead>"
//            + "                        <tr>"
//            + "                            <th>Numero Scaffale</th>"
//            + "                            <th>Categoria</th>"
//            + "                            <th>Prezzo di Acquisto</th>"
//            + "                            <th>Quantita acquistati</th>"
//            + "                            <th>Spesa totale</th>"
//            + "                            <th>Quantita vendute</th>"
//            + "                            <th>Ritorno</th>"
//            + "                        </tr>"
//            + "                    </thead>"
//            + "                    <tbody>"
//            + "                    <!--SOSTITUISCI_QUI_GLI_ELEMENTI-->"
//            + "                    </tbody>"
//            + "                </table>"
//            + "            </div>"
//            + "        </div>";
//
//    private static final String STRUTTURA_TABELLA_PRODOTTO
//            = "        <div class='row'>"
//            + "            <div class='col-sm-8'>"
//            + "                <table class='table table-responsive'>"
//            + "                    <thead>"
//            + "                        <tr>"
//            + "                            <th>"
//            + "                                <a href='?action=new' class='btn btn-primary'>Nuovo</a>"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Nome"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Prezzo"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Provenienza"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Scadenza"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Fornitore"
//            + "                            </th>"
//            + "                        </tr>"
//            + "                    </thead>"
//            + "                    <tbody>"
//            + "                    <!--SOSTITUISCI_QUI_GLI_ELEMENTI-->"
//            + "                    </tbody>"
//            + "                </table>"
//            + "            </div>"
//            + "        </div>";
//
//    public static final String RICERCA_SCAFFALI
//            = "        <div class='row'>"
//            + "            <div class='col-sm-12'>"
//            + "                <h3>------------------------------------------------------------------Inserisci un prodotto in uno scaffale------------------------------------------------------------------</h3>"
//            + "            </div>"
//            + "        </div>"
//            + "        <div class='row'>"
//            + "            <div class='col-sm-2'>"
//            + "                <label class='label label-primary'>Prodotto:</label> <select class='input-sm' name='id_prodotto'>"
//            + "                    <option value='id1'>Prodotto #1</option>"
//            + "                    <option value='id2'>Prodotto #2</option>"
//            + "                    <option value='id3'>Prodotto #3</option>"
//            + "                    <option value='id4'>Prodotto #4</option>"
//            + "                </select>"
//            + "            </div>"
//            + "            <div class='col-sm-2'>"
//            + "                <label class='label label-primary'>Numero scaffale:</label> <select class='input-sm' name='id_scaffale'>"
//            + "                    <option value='1'>#1</option>"
//            + "                    <option value='2'>#2</option>"
//            + "                    <option value='3'>#3</option>"
//            + "                    <option value='4'>#4</option>"
//            + "                </select>"
//            + "            </div>"
//            + "            <div class='col-sm-2'>"
//            + "                <label class='label label-primary'>Quantit&aacute; prodotti:</label> <input class='input-sm' type='number' name='quantita' min='1'>"
//            + "            </div>"
//            + "            <div class='col-sm-1'>"
//            + "                <input class='btn btn-primary' type='submit'>"
//            + "            </div>"
//            + "        </div>";
//
//    private static final String STRUTTURA_TABELLA_SCAFFALE
//            = "        <div class='row'>"
//            + "            <div class='col-sm-8'>"
//            + "                <table class='table'>"
//            + "                    <thead>"
//            + "                        <tr>"
//            + "                            <th>"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Categoria"
//            + "                            </th>"
//            + "                        </tr>"
//            + "                    </thead>"
//            + "                    <tbody>"
//            + "                    <!--SOSTITUISCI_QUI_GLI_ELEMENTI-->"
//            + "                    </tbody>"
//            + "                </table>"
//            + "            </div>"
//            + "        </div>";
//
//    public static final String RICERCA_MAGAZZINIERE
//            = "        <div class='row'>"
//            + "            <div class='col-sm-12'>"
//            + "                <h3>------------------------------------------------------------------Cerca un magazziniere------------------------------------------------------------------</h3>"
//            + "            </div>"
//            + "        </div>"
//            + "        <div class='row'>"
//            + "            <div class='col-sm-2'>"
//            + "                <label class='label label-primary'>Codice fiscale:</label> <input class='input-sm' type='text' name='codice_fiscale'>"
//            + "            </div>"
//            + "            <div class='col-sm-2'>"
//            + "                <label class='label label-primary'>Patente:</label> <input class='input-sm' type='text' name='patente'>"
//            + "            </div>"
//            + "            <div class='col-sm-1'>"
//            + "                <input class='btn btn-primary' type='submit'>"
//            + "            </div>"
//            + "        </div>";
//
//    private static final String STRUTTURA_TABELLA_MAGAZZINIERI
//            = "        <div class='row'>"
//            + "            <div class='col-sm-8'>"
//            + "                <table class='table'>"
//            + "                    <thead>"
//            + "                        <tr>"
//            + "                            <th>"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Nome"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Cognome"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Data di nascita"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Codice fiscale"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Patente"
//            + "                            </th>"
//            + "                        </tr>"
//            + "                    </thead>"
//            + "                    <tbody>"
//            + "                    <!--SOSTITUISCI_QUI_GLI_ELEMENTI-->"
//            + "                    </tbody>"
//            + "                </table>"
//            + "            </div>"
//            + "        </div>";
//
//    public static final String RICERCA_VEICOLI
//            = "        <div class='row'>"
//            + "            <div class='col-sm-12'>"
//            + "                <h3>------------------------------------------------------------------Cerca un veicolo------------------------------------------------------------------</h3>"
//            + "            </div>"
//            + "        </div>"
//            + "        <div class='row'>"
//            + "            <div class='col-sm-2'>"
//            + "                <label class='label label-primary'>Tipo Veicolo:</label> <input class='input-sm' type='text' name='tipo_veicolo'>"
//            + "            </div>"
//            + "            <div class='col-sm-2'>"
//            + "                <label class='label label-primary'>Patente Richiesta:</label> <input class='input-sm' type='text' name='patente_richiesta'>"
//            + "            </div>"
//            + "            <div class='col-sm-1'>"
//            + "                <input class='btn btn-primary' type='submit'>"
//            + "            </div>"
//            + "        </div>";
//
//    private static final String STRUTTURA_TABELLA_VEICOLI
//            = "        <div class='row'>"
//            + "            <div class='col-sm-8'>"
//            + "                <table class='table'>"
//            + "                    <thead>"
//            + "                        <tr>"
//            + "                            <th>"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Tipo Veicolo"
//            + "                            </th>"
//            + "                            <th>"
//            + "                                Patente Richiesta"
//            + "                            </th>"
//            + "                        </tr>"
//            + "                    </thead>"
//            + "                    <tbody>"
//            + "                    <!--SOSTITUISCI_QUI_GLI_ELEMENTI-->"
//            + "                    </tbody>"
//            + "                </table>"
//            + "            </div>"
//            + "        </div>";
//
//    public static String getDettagliProdotto(ProductDetailsDTO dettaglioProdotto, boolean disabled) {
//        String tabella = STRUTTURA_TABELLA_DETTAGLIO_PRODOTTO, pannello = getPannelloProdotto(dettaglioProdotto,disabled);
//        String codiceTabella = "";
//        codiceTabella += "<tr>"
//                + "<td>" + dettaglioProdotto.getId_scaffale() + "</td>"
//                + "<td>" + dettaglioProdotto.getCategoria() + "</td>"
//                + "<td>" + dettaglioProdotto.getPrezzo_acquisto() + "</td>"
//                + "<td>" + dettaglioProdotto.getQuantita_acquistati() + "</td>"
//                + "<td>" + dettaglioProdotto.getSpesa_totale() + "</td>"
//                + "<td>" + dettaglioProdotto.getQuantita_venduti() + "</td>"
//                + "<td>" + dettaglioProdotto.getRitorno() + "</td>"
//                + "</tr>";
//        tabella = tabella.replace("<!--SOSTITUISCI_QUI_GLI_ELEMENTI-->", codiceTabella);
//        return pannello + " " + tabella;
//    }
//
//    public static String getPannelloProdotto(ProductDetailsDTO dettaglioProdotto,boolean disabled) {
//        String pannello = DETTAGLIO_PRODOTTO;
//        if (dettaglioProdotto.getId() != -1) {
//            pannello = pannello.replace("nome_prodotto_value", dettaglioProdotto.getNome());
//            pannello = pannello.replace("prezzo_prodotto_value", String.valueOf(dettaglioProdotto.getPrezzo_vendita()));
//            pannello = pannello.replace("provenienza_prodotto_value", dettaglioProdotto.getProvenienza());
//            pannello = pannello.replace("scadenza_prodotto_value", dettaglioProdotto.getScadenza().toString());
//            pannello = pannello.replace("fornitore_prodotto_value", dettaglioProdotto.getNome_fornitore());
//        } else{
//            pannello = pannello.replace("nome_prodotto_value", "");
//            pannello = pannello.replace("prezzo_prodotto_value", "");
//            pannello = pannello.replace("provenienza_prodotto_value", "");
//            pannello = pannello.replace("scadenza_prodotto_value", "");
//            pannello = pannello.replace("fornitore_prodotto_value", "");
//        }
//        if (!disabled) {
//            pannello = pannello.replace("id_prodotto_value", String.valueOf(dettaglioProdotto.getId()));
//            pannello = pannello.replaceAll("disabled='' ", "");
//            pannello = "<form action='prodotti' method='POST'>"
//                    + pannello
//                    + "<div class='row'>"
//                    + "<input class='btn btn-primary col-sm-1' type='submit'>"
//                    + "</div>"
//                    + "</form>";
//        }
//        return pannello;
//    }
//
//    public static String getTabellaVeicoli(List<VeicoloDTO> listaVeicoli) {
//        String tabella = STRUTTURA_TABELLA_SCAFFALE;
//        String codiceElementi = "";
//        codiceElementi = listaVeicoli.stream().map((veicolo) -> "<tr>"
//                + "<td>"
//                + "<a href='?ID=" + veicolo.getId() + "&action=view' ><span class='glyphicon glyphicon-eye-open'></span></a>"
//                + "<a href='?ID=" + veicolo.getId() + "&action=edit' ><span class='glyphicon glyphicon-pencil'></span></a>"
//                + "</td>"
//                + "<td>" + veicolo.getTipoVeicolo() + "</td>"
//                + "<td>" + veicolo.getPatenteRichiesta() + "</td>").reduce(codiceElementi, String::concat);
//        tabella = tabella.replace("<!--SOSTITUISCI_QUI_GLI_ELEMENTI-->", codiceElementi);
//        return tabella;
//    }
//
//    public static String getTabellaMagazzinieri(List<MagazziniereDTO> listaMagazzinieri) {
//        String tabella = STRUTTURA_TABELLA_MAGAZZINIERI;
//        String codiceElementi = "";
//        codiceElementi = listaMagazzinieri.stream().map((magazziniere) -> "<tr>"
//                + "<td>"
//                + "<a href='?ID=" + magazziniere.getId() + "&action=view' ><span class='glyphicon glyphicon-eye-open'></span></a>"
//                + "<a href='?ID=" + magazziniere.getId() + "&action=edit' ><span class='glyphicon glyphicon-pencil'></span></a>"
//                + "</td>"
//                + "<td>" + magazziniere.getNome() + "</td>"
//                + "<td>" + magazziniere.getCognome() + "</td>"
//                + "<td>" + magazziniere.getDataNascita().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</td>"
//                + "<td>" + magazziniere.getCodiceFiscale() + "</td>"
//                + "<td>" + magazziniere.getPatente() + "</td>"
//                + "</tr>").reduce(codiceElementi, String::concat);
//        tabella = tabella.replace("<!--SOSTITUISCI_QUI_GLI_ELEMENTI-->", codiceElementi);
//        return tabella;
//    }
//
//    public static String getTabellaScaffali(List<ScaffaleDTO> listaScaffali) {
//        String tabella = STRUTTURA_TABELLA_SCAFFALE;
//        String codiceElementi = "";
//        codiceElementi = listaScaffali.stream().map((scaffale) -> "<tr>"
//                + "<td>"
//                + "<a href='?ID=" + scaffale.getId() + "&action=view' ><span class='glyphicon glyphicon-eye-open'></span></a>"
//                + "<a href='?ID=" + scaffale.getId() + "&action=edit' ><span class='glyphicon glyphicon-pencil'></span></a>"
//                + "</td>"
//                + "<td>" + scaffale.getDescrizione() + "</td>").reduce(codiceElementi, String::concat);
//        tabella = tabella.replace("<!--SOSTITUISCI_QUI_GLI_ELEMENTI-->", codiceElementi);
//        return tabella;
//    }
//
//    public static String getTabellaProdotti(List<ProdottoDTO> listaProdotti) {
//        String tabella = STRUTTURA_TABELLA_PRODOTTO;
//        String codiceElementi = "";
//        codiceElementi = listaProdotti.stream().map((prodotto) -> "<tr>"
//                + "<td>"
//                + "<a href='?ID=" + prodotto.getId() + "&action=view' ><span class='glyphicon glyphicon-eye-open'></span></a>"
//                + "<a href='?ID=" + prodotto.getId() + "&action=edit' ><span class='glyphicon glyphicon-pencil'></span></a>"
//                + "</td>"
//                + "<td>" + prodotto.getNome() + "</td>"
//                + "<td>" + prodotto.getPrezzo() + "</td>"
//                + "<td>" + prodotto.getProvenienza() + "</td>"
//                + "<td>" + prodotto.getScadenza().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</td>"
//                + "<td>" + prodotto.getIdFornitore() + "</td>"
//                + "</tr>").reduce(codiceElementi, String::concat);
//        tabella = tabella.replace("<!--SOSTITUISCI_QUI_GLI_ELEMENTI-->", codiceElementi);
//        return tabella;
//    }
//}
