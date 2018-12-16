/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

/**
 *
 * @author Pan
 */
public interface Constants {
    static final String PATH_COMUNI_FILE = "Files/comuni.csv";
    static final String PATH_SOURCE_FILE = "Files/incidenti.csv";
    static final String PATH_TEMP_FILE = "Files/temp.csv";
    static final String PATH_DATAWHAREHOUSE_CSV = "Files/datawharehouse.csv";
    
    static final String INIT = "<html><head><title>Procedura ETL</title></head><body style=\"background-color:lavender\";>";
    static final String CLOSE = "</body></html>";
    static final String RESULT_FILE = "Files/risultato";
    static final String REPORT_MESSAGE = "PROCEDURA ETL TERMINATA<br>DATA : ";
    static final String ERROR_MESSAGE = "ERRORI : ";
    static final String MISSINGS_MESSAGE = "RECORD MANCANTI : ";
    static final String HEADER_ERROR = "Intestazione di " + PATH_SOURCE_FILE + " errata";
    static final String SEPARATOR = ";";
    static final int N_COLONNE = 55;
    
    String[] CAMPI_OLTP = {"ANNO", "PROV", "COMUNE", "TOT_INC", "TOT_INC_MORTALI", 
                          "TOT_FERITI", "TOT_MORTI", "TOT_VEIC_COINVOLTI", "TOT_VEIC_CONDUC_IGNOTO", 
                          "TOT_CONDUC", "CONDUC_FEMMINE", "CONDUC_FERITI", "CONDUC_MORTI_ENTRO24h", "CONDUC_IGNOTI", 
                          "CONDUC_ETA_0-14_INC", "CONDUC_ETA_15-19_INC", "CONDUC_ETA_20-64_INC", "CONDUC_ETA_65+_INC", 
                          "CONDUC_ETA_0-14_FERITI", "CONDUC_ETA_15-19_FERITI", "CONDUC_ETÀ_20-64_FERITI", 
                          "CONDUC_ETA_65+_FERITI", "CONDUC_ETA_0-14_MORTI", "CONDUC_ETA_15-19_MORTI", 
                          "CONDUC_ETA_20-64_MORTI", "CONDUC_ETA_65+_MORTI", "PASSEGGERI_FERITI", "PASSEGGERI_MORTI", 
                          "PEDONI_FERITI", "PEDONI_MORTI", "STRADE_URBANE_INC", "STRADE_URBANE_FERITI", 
                          "STRADE_URBANE_MORTI", "STRADE_EXTRAURB_INC", "STRADE_EXTRAURB_FERITI", "STRADE_EXTRAURB_MORTI", 
                          "AUTOSTR_INC", "AUTOSTR_FERITI", "AUTOSTR_MORTI", "INC_TRA_VEIC_IN_MARCIA", 
                          "INC_TRA_VEIC_E_PEDONE", "INC_TRA_VEIC_ISOLATI", "WEEKEND_INC", "FERIALI_INC", "NOTTE_INC", 
                          "GIORNO_INC", "ORE_07-09_INC", "ORE_17-19_INC", "SERENO_INC", "NEBBIA_INC", "PIOGGIA-NEVE_INC", 
                          "VEIC_COINVOLTI_AUTOVET_(PRIV_E_PUB)", "VEIC_COINVOLTI_AUTOCAR_E_SIMILI", 
                          "VEIC_COINVOLTI_MOTOCICLI", "VEIC_COINVOLTI_VELOCIPEDI"};
    
    String[] NEW_CAMPI = {"TOT_INC_NONMORTALI", "CONDUC_MASCHI", "VEIC_COINVOLTI_ALTRO"};
}
