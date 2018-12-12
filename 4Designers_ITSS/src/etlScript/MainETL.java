/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author FourDesigners
 */
public class MainETL {
    private static final String PATH_COMUNI_FILE = "Files/comuni.csv";
    private static final String PATH_SOURCE_FILE = "Files/incidenti.csv";
    private static final String PATH_TEMP_FILE = "Files/temp.csv";
    private static final String PATH_DEST_FILE = "Files/new_incidenti.csv";
    private static final String INIT = "<html><head><title>Procedura ETL</title></head><body style=\"background-color:lavender\";>";
    private static final String CLOSE = "</body></html>";
    private static final String RESULT_FILE = "Files/risultato";
    private static final String REPORT_MESSAGE = "PROCEDURA ETL TERMINATA<br>DATA : ";
    private static final String ERROR_MESSAGE = "ERRORI : ";
    private static final String MISSINGS_MESSAGE = "RECORD MANCANTI : ";
    private static final String HEADER_ERROR = "Intestazione di " + PATH_SOURCE_FILE + " errata";
    private static final String SEPARATOR = ";";
    private static final int N_COLONNE = 55;
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws IOException {
        int duplicati = 0;
        int errati = 0;
        int accettati = 0;
        Set<Chiave> chiavi = new HashSet<>();
        Map<String, Set<String>> comuni = new HashMap<>();
        String[] newcampi = {"TOT_INC_NONMORTALI", "CONDUC_MASCHI", "VEIC_COINVOLTI_ALTRO"};
        String[] newcampi_values = {"", "", ""};
        
        Calendar cal = Calendar.getInstance();
        String giorno = Integer.toString(cal.get(Calendar.DATE));
        String mese = Integer.toString(cal.get(Calendar.MONTH)+1);
        String anno = Integer.toString(cal.get(Calendar.YEAR));
        String ora = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
        String minuto = Integer.toString(cal.get(Calendar.MINUTE));
        String data = anno + "_" + mese + "_" + giorno;
        String timestamp = anno + "/" + mese + "/" + giorno + " " + ora + ":" + minuto;
        final String PATH_RESULT_FILE = RESULT_FILE + data + ".html";
        PrintWriter outStream = new PrintWriter(new BufferedWriter(new FileWriter(PATH_RESULT_FILE)));
        outStream.write(INIT);
        //outStream.write(REPORT_MESSAGE + timestamp + "\r\n\r\n");
        outStream.write("<h1><b>" + REPORT_MESSAGE + timestamp + "</b></h1><br>");
        //outStream.write(ERROR_MESSAGE + "\r\n");
        fillMunicipalities(comuni, PATH_COMUNI_FILE);
        try{
            Scanner inputStream = new Scanner(new File(PATH_SOURCE_FILE));
            String riga = inputStream.nextLine();
            //controlla l'intestazione del file
            if(verificaIntestazione(riga.split(SEPARATOR))) {
                outStream.write("<h3>" + ERROR_MESSAGE + "</h3><br><ul>");
                //controlla che il file TEMP non esista già e poi crea la prima riga (di intestazione)
                if(!new File(PATH_DEST_FILE).exists()) {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_DEST_FILE)));
                    out.print(riga);
                    for(String s : newcampi) {
                        out.print(SEPARATOR + s);
                    }
                    out.println();
                    out.close();
                }else {
                    fillKeys(chiavi, PATH_DEST_FILE);
                }
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_TEMP_FILE)));
                while(inputStream.hasNextLine()) {
                    riga = inputStream.nextLine();
                    String[] campi = riga.split(SEPARATOR);
                    Record record;
                    if (campi.length == N_COLONNE) { //controllo coerenza con il protocollo
                        record = Record.create(campi);
                        record.verifica(comuni); //controllo consistenza del record
                        if(record.isCorrect()) {
                            if (!record.isPresent(chiavi)) { //controllo duplicati
                                accettati++;
                                chiavi.add(new Chiave(campi[0], campi[2]));
                                out.print(campi[0]);
                                for(int i=1; i<campi.length; i++) {
                                    out.print(SEPARATOR + campi[i]);
                                }
                                Integer tot_incidenti_nonmortali = Integer.parseInt(campi[3])-Integer.parseInt(campi[4]);
                                Integer conduc_maschi = Integer.parseInt(campi[9])-Integer.parseInt(campi[10]);
                                Integer veic_coinvolti_altro = Integer.parseInt(campi[7])-Integer.parseInt(campi[52])-Integer.parseInt(campi[53])-Integer.parseInt(campi[54]);
                                newcampi_values[0] = tot_incidenti_nonmortali.toString();
                                newcampi_values[1] = conduc_maschi.toString();
                                newcampi_values[2] = veic_coinvolti_altro.toString();
                                for(String s : newcampi_values) {
                                    out.print(SEPARATOR + s);
                                }
                                out.println();
                            }else { //la riga analizzata è un duplicato
                                duplicati++;
                            }
                        }else { //record inconsistente per qualche controllo non andato a buon fine
                            //outStream.write(record.toString() + "\r\n");
                            outStream.write("<li>" + record.toString() + "</li>");
                            errati++;
                        }
                    }else { //record incoerente con il protocollo
                        record = new Record();
                        record.addError("Numero di colonne errato");
                        outStream.write(record.toString() + "\r\n");
                    }
                }
                outStream.write("</ul>");
                out.close();
                inputStream.close();
                //mancanti = findMissingRecords2("Files/temp_demo.csv", "Files/new_incidenti_demo.csv", comuni); //controlla se ci sono righe mancanti
                //outStream.write("\r\n" + MISSINGS_MESSAGE + "\r\n");
                outStream.write("<h3>" + MISSINGS_MESSAGE + "</h3><ul>");
                int mancanti = findMissingRecords(comuni, PATH_TEMP_FILE, PATH_DEST_FILE, outStream);
                update(PATH_TEMP_FILE, PATH_DEST_FILE); //aggiunge le nuove righe al file dest
                //outStream.write("Numero righe accettate : " + accettati + "\r\n");
                //outStream.write("Numero righe duplicate : " + duplicati + "\r\n");
                //outStream.write("Numero righe rifiutate : " + errati + "\r\n");
                //outStream.write("Numero righe mancanti : " + mancanti + "\r\n");
                outStream.write("Numero righe accettate : " + accettati + "<br>");
                outStream.write("Numero righe duplicate : " + duplicati + "<br>");
                outStream.write("Numero righe rifiutate : " + errati + "<br>");
                outStream.write("Numero righe mancanti : " + mancanti + "<br>");
            }else {
                outStream.write(HEADER_ERROR);
            }
            outStream.write(CLOSE);
            outStream.close();
            System.out.println("PROCEDURA ETL " + timestamp + " TERMINATA");
        }catch(FileNotFoundException e) {
            System.out.println("Non è stato trovato il file");
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    private static void update(String source, String dest) throws FileNotFoundException, IOException {
        Scanner inputStream = new Scanner(new File(source));
        FileWriter fw = new FileWriter(dest, true);
        while (inputStream.hasNextLine()) {
            String riga = inputStream.nextLine();
            fw.write(riga + "\r\n");
        }
        fw.close();
        inputStream.close();
    }

    private static void fillKeys(Set<Chiave> chiavi, String filename) {
        try {
            try (Scanner inputStream = new Scanner(new File(filename))) {
                inputStream.nextLine();
                while(inputStream.hasNextLine()) {
                    String riga = inputStream.nextLine();
                    String[] campi = riga.split(SEPARATOR);
                    chiavi.add(new Chiave(campi[0], campi[2]));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainETL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void fillMunicipalities(Map<String, Set<String>> comuni, String filename) {
        try {
            try (Scanner inputStream = new Scanner(new File(filename))) {
                while(inputStream.hasNextLine()) {
                    String riga = inputStream.nextLine();
                    String[] campi = riga.split(SEPARATOR);
                    if(comuni.containsKey(campi[0])) {
                        comuni.get(campi[0]).add(campi[1]);
                    }else {
                        comuni.put(campi[0], new HashSet<>());
                        comuni.get(campi[0]).add(campi[1]);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File dei comuni non presente");
        }
    }
    
    private static int findMissingRecords(Map<String, Set<String>> comuni, String tempFile, String destFile, PrintWriter out) {
        int mancanti = 0;
        try {
            TreeMap<String, Set<String>> annoComune = new TreeMap<>();
            Scanner inputStream = new Scanner(new File(tempFile));
            while(inputStream.hasNextLine()) {
                String riga = inputStream.nextLine();
                String[] campi = riga.split(SEPARATOR);
                if(!annoComune.containsKey(campi[0])) {
                    annoComune.put(campi[0], new HashSet<>());
                    comuni.keySet().forEach((provincia) -> {
                        annoComune.get(campi[0]).addAll(comuni.get(provincia));
                    });
                }
                annoComune.get(campi[0]).remove(campi[2]);
            }
            inputStream.close();
            
            inputStream = new Scanner(new File(destFile));
            inputStream.nextLine();
            while(inputStream.hasNextLine()) {
                String riga = inputStream.nextLine();
                String[] campi = riga.split(SEPARATOR);
                if(!annoComune.containsKey(campi[0])) {
                    annoComune.put(campi[0], new HashSet<>());
                    comuni.keySet().forEach((provincia) -> {
                        annoComune.get(campi[0]).addAll(comuni.get(provincia));
                    });
                }
                annoComune.get(campi[0]).remove(campi[2]);
            }
            inputStream.close();
            
            for(String anno : annoComune.keySet()) {
                mancanti += annoComune.get(anno).size();
                //out.write("ANNO " + anno + " : " + annoComune.get(anno).size());
                //out.write("\r\n");
                out.write("<li>ANNO " + anno + " : " + annoComune.get(anno).size() + "</li>");
            }
            //out.write("\r\n");
            out.write("</ul><br>");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mancanti;
    }
    
    /*
    private static int findMissingRecords(String finalFile, String tempFile, Map<String, Set<String>> provinceComuni) {
        Scanner inputStream;
        String riga;
        String[] campi;
        PrintWriter out;
        int counter = 0;
        
        try {
            //creo la lista degli anni
            TreeSet<Integer> anniPresenti = new TreeSet<Integer>();
            //popolo la lista degli anni con quelli presenti nel file Temp
            inputStream = new Scanner(new File(tempFile));
            while(inputStream.hasNextLine()) {
                riga = inputStream.nextLine();
                campi = riga.split(SEPARATOR);
                anniPresenti.add(Integer.parseInt(campi[0]));
            }
            inputStream.close();
            //popolo la lista degli anni con quelli presenti nel file Dest
            inputStream = new Scanner(new File(finalFile));
            riga = inputStream.nextLine(); //salto la prima riga
            while(inputStream.hasNextLine()) {
                riga = inputStream.nextLine();
                campi = riga.split(SEPARATOR);
                anniPresenti.add(Integer.parseInt(campi[0]));
            }
            inputStream.close();
            Iterator<Integer> anniItr = anniPresenti.iterator();
            //creo lista di comuni senza le province, popolandola tramite l'HashMap creato nel main
            ArrayList<String> comuni = new ArrayList<String>();
            for(String provincia : provinceComuni.keySet()) {
                comuni.addAll(provinceComuni.get(provincia));
            }
            while(anniItr.hasNext()) {
                //prendo l'anno che sto analizzando
                int annoAnalizzato = anniItr.next();
                
                System.out.println("annoAnalizzato= " + annoAnalizzato);
                
                ArrayList<String> comuniCopia = new ArrayList<String>(comuni);
                //elimino da comuniCopia i comuni che ho trovato nel file temp
                inputStream = new Scanner(new File(tempFile));
                while(inputStream.hasNextLine()) {
                    riga = inputStream.nextLine();
                    campi = riga.split(SEPARATOR);
                    if(Integer.parseInt(campi[0]) == annoAnalizzato)
                        comuniCopia.remove(campi[2]);
                }
                inputStream.close();
                
                System.out.println("comuni mancanti dopo aver analizzato il file temp= " + comuniCopia.size());
                
                //elimino da comuniCopia i comuni che ho trovato nel file final
                inputStream = new Scanner(new File(finalFile));
                riga = inputStream.nextLine(); //salto la prima riga
                while(inputStream.hasNextLine()) {
                    riga = inputStream.nextLine();
                    campi = riga.split(SEPARATOR);
                    if(Integer.parseInt(campi[0]) == annoAnalizzato)
                        comuniCopia.remove(campi[2]);
                }
                inputStream.close();
                
                System.out.println("comuni mancanti dopo aver analizzato il file dest= " + comuniCopia.size());
                
                counter = counter + comuniCopia.size();
                if(!new File(PATH_DEST_FILE).exists()) 
                    out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_MISSINGS_FILE)));
                else out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_MISSINGS_FILE, true)));
                if(comuniCopia.isEmpty())
                    out.println("Sono stati inseriti tutti i dati per ogni comune.");
                else {
                    out.println("Per l'anno " + annoAnalizzato + " mancano i seguenti comuni: " + comuniCopia);
                    out.println("\n");
                    out.close();
                }
            }
        } catch (FileNotFoundException e1) {
            System.out.println("Uno dei file non è stato trovato");
        } catch (IOException e2) {
            System.out.println("Impossibile aprire il file per scrivere i record mancanti");
        }
        
        return counter;
    }
    */

    private static boolean verificaIntestazione(String[] split) {
        String[] campi = {"ANNO", "PROV", "COMUNE", "TOT_INC", "TOT_INC_MORTALI", 
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
      
        if(split.length != campi.length) {
            return false;
        }
        split[0] = split[0].substring(1);
        for(int i=0; i<campi.length; i++) {
            if(!campi[i].equals(split[i])) {
                return false;
            }
        }
        return true;
    }
}
