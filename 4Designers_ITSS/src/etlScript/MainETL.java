/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

import eccezioni.WrongHeaderException;
import four.designers.etl.Timestamp;
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
public class MainETL implements Constants {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws IOException {
        int duplicati = 0;
        int errati = 0;
        int accettati = 0;
       

        Timestamp mTimestamp = new Timestamp();
        String data = mTimestamp.getData();
        String timestamp = mTimestamp.getTimestamp();

        final String PATH_RESULT_FILE = RESULT_FILE + data + ".html";
        PrintWriter streamResultFile = new PrintWriter(new BufferedWriter(new FileWriter(PATH_RESULT_FILE)));
        streamResultFile.write(INIT);
        //outStream.write(REPORT_MESSAGE + timestamp + "\r\n\r\n");
        streamResultFile.write("<h1><b>" + REPORT_MESSAGE + timestamp + "</b></h1><br>");
        //outStream.write(ERROR_MESSAGE + "\r\n");
        

        try {
            //Riempie il set di comuni dal file dei comuni della regione
            Controlli.fillMunicipalities(PATH_COMUNI_FILE);
            
            //Apre il file proveniente dalla procedura OLTP
            Scanner stramSourceFile = new Scanner(new File(PATH_SOURCE_FILE));
            String rigaSourceFile = stramSourceFile.nextLine();

            System.out.print("Controllo intestazione: ");
            //controlla l'intestazione del file, lancia eccezione WrongHeaderExceprion se intestazione errata
            Controlli.verificaIntestazione(rigaSourceFile.split(SEPARATOR));
            System.out.print("OK");

            streamResultFile.write("<h3>" + ERROR_MESSAGE + "</h3><br><ul>");
            //controlla che il file TEMP non esista già 
            if (!new File(PATH_DATAWHAREHOUSE_CSV).exists()) {
                //Se non esiste, riempie l'intestazione con i campi scelti nel protocollo
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_DATAWHAREHOUSE_CSV)));
                out.print(rigaSourceFile);
                for (String s : NEW_CAMPI) {
                    out.print(SEPARATOR + s);
                }
                out.println();
                out.close();
            } else {
                //se esiste, riempie il set di chiavi esistente che servirà per i controlli
                Controlli.fillKeys(PATH_DATAWHAREHOUSE_CSV);
            }

            PrintWriter writerTempFile = new PrintWriter(new BufferedWriter(new FileWriter(PATH_TEMP_FILE)));
            while (stramSourceFile.hasNextLine()) {
                rigaSourceFile = stramSourceFile.nextLine();
                String[] campiRigaAnalizzata = rigaSourceFile.split(SEPARATOR);
                Record record;
                if (campiRigaAnalizzata.length == N_COLONNE) { //controllo coerenza con il protocollo
                    record = Record.create(campiRigaAnalizzata);
                    
                    record.verificaRecord(); //controllo consistenza del record
                    if (record.isCorrect()) {
                        if (Controlli.recordIsPresent(record)) { //controllo duplicati
                            accettati++;
                            Controlli.addChiave(new Chiave(campiRigaAnalizzata[0], campiRigaAnalizzata[2]));
                            
                            //scrive la riga appena analizzata nel file temporaneo
                            writerTempFile.print(campiRigaAnalizzata[0]);
                            for (int i = 1; i < campiRigaAnalizzata.length; i++) {
                                writerTempFile.print(SEPARATOR + campiRigaAnalizzata[i]);
                            }                            
                            String[] newCampi = record.setNewCampi();                            
                            for (String s : newCampi) {
                                writerTempFile.print(SEPARATOR + s);
                            }
                            writerTempFile.println();
                        } else { //la riga analizzata è un duplicato
                            duplicati++;
                        }
                    } else { //record inconsistente per qualche controllo non andato a buon fine
                        //outStream.write(record.toString() + "\r\n");
                        streamResultFile.write("<li>" + record.toString() + "</li>");
                        errati++;
                    }
                } else { //record incoerente con il protocollo
                    
                    //todo gestione riga incoerente protocol
                    record = new Record();
                    record.addError("Numero di colonne errato");
                    streamResultFile.write(record.toString() + "\r\n");
                }
            }
            
            //scansione delle righe del file terminata, chiusura degli stream
            streamResultFile.write("</ul>");
            writerTempFile.close();
            stramSourceFile.close();
            
            
            //mancanti = findMissingRecords2("Files/temp_demo.csv", "Files/new_incidenti_demo.csv", comuni); //controlla se ci sono righe mancanti
            //outStream.write("\r\n" + MISSINGS_MESSAGE + "\r\n");
            streamResultFile.write("<h3>" + MISSINGS_MESSAGE + "</h3><ul>");
            int mancanti = Controlli.findMissingRecords(PATH_TEMP_FILE, PATH_DATAWHAREHOUSE_CSV, streamResultFile);
            update(PATH_TEMP_FILE, PATH_DATAWHAREHOUSE_CSV); //aggiunge le nuove righe al file dest
            //outStream.write("Numero righe accettate : " + accettati + "\r\n");
            //outStream.write("Numero righe duplicate : " + duplicati + "\r\n");
            //outStream.write("Numero righe rifiutate : " + errati + "\r\n");
            //outStream.write("Numero righe mancanti : " + mancanti + "\r\n");
            streamResultFile.write("Numero righe accettate : " + accettati + "<br>");
            streamResultFile.write("Numero righe duplicate : " + duplicati + "<br>");
            streamResultFile.write("Numero righe rifiutate : " + errati + "<br>");
            streamResultFile.write("Numero righe mancanti : " + mancanti + "<br>");

            streamResultFile.write(CLOSE);
            streamResultFile.close();
            System.out.println("PROCEDURA ETL " + timestamp + " TERMINATA");
        } catch (FileNotFoundException e) {
            System.out.println("Non è stato trovato il file");
            
            //Todo gestione throw impossibile aprire file datawharehouse
            //Todo gestire throw file comuni non presente
        } catch (WrongHeaderException ex) {
            System.out.print("FALLITO");
            System.out.println(ex);
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
}
