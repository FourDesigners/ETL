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

    static int duplicati = 0;
    static int errati = 0;
    static int accettati = 0;
    static int totali = 0;
    static Timestamp mTimestamp = new Timestamp();
    static String data = mTimestamp.getData();
    static String timestamp = mTimestamp.getTimestamp();

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        
        print("\n\n                INIZIO PROCEDURA ETL\n\n");
        String PATH_LOG_FILE = PATH_RESULT_FILE_RISULTATI + data + ".html";
        proceduraETL(PATH_RESULT_FILE + ".csv", PATH_TEMP_FILE, PATH_SOURCE_FILE, PATH_LOG_FILE, true);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("PROCEDURA ETL " + timestamp + " TERMINATA");
        print("Tempo impiegato: " + timeElapsed + " ms\n");

    }

    public static void proceduraETL(String fileDW, String fileTmp, String fileSorgente, String fileLog, Boolean stampaLog) throws IOException, InterruptedException {

        PrintWriter streamLogFile;

        try (PrintWriter tempPrinter = new PrintWriter(new BufferedWriter(new FileWriter(fileLog)))) {
            streamLogFile = tempPrinter;

            if (stampaLog) {
                streamLogFile.write(INTESTAZIONE_REPORT);
                //outStream.write(REPORT_MESSAGE + timestamp + "\r\n\r\n");
                streamLogFile.write("<h1><b>" + REPORT_MESSAGE + timestamp + "</b></h1><br>");
                //outStream.write(ERROR_MESSAGE + "\r\n");
            }
            Thread.sleep(1000);
            print("File di destinazione procedure: " + fileDW + "\n"
                    + "File sorgente dati: \t\t" + fileSorgente + "\n");
            if (stampaLog) {
                System.out.println("File di log: \t\t\t" + fileLog);
            }
            Thread.sleep(1000);

            //Riempie il set di comuni dal file dei comuni della regione
            print("\nCaricamento file comuni: ............");
            Controlli.fillMunicipalities(PATH_COMUNI_FILE);
            print("OK\n");

            //Apre il file proveniente dalla procedura OLTP
            Scanner stramSourceFile = new Scanner(new File(fileSorgente));
            print("Caricamento file sorgente dati: .....");
            String rigaSourceFile = stramSourceFile.nextLine();
            print("OK\n");

            print("Controllo intestazione: .............");
            //controlla l'intestazione del file, lancia eccezione WrongHeaderExceprion se intestazione errata
            Controlli.verificaIntestazione(rigaSourceFile.split(SEPARATOR));
            print("OK\n");

            if (stampaLog) {
                streamLogFile.write("<h3>" + ERROR_MESSAGE + "</h3><br><ul>");
            }
            //controlla che il file Datawharehouse non esista già 
            print("Apertura file destinazione: .........");
            if (!new File(fileDW).exists()) {
                //Se non esiste, riempie l'intestazione con i campi scelti nel protocollo
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileDW)));
                out.print(rigaSourceFile);
                for (String s : NEW_CAMPI) {
                    out.print(SEPARATOR + s);
                }
                out.println();
                out.close();
            } else {
                //se esiste, riempie il set di chiavi esistente che servirà per i controlli
                Controlli.fillKeys(fileDW);
            }
            print("OK\n");

            print("Apertura file di appoggio: ..........");
            PrintWriter writerTempFile = new PrintWriter(new BufferedWriter(new FileWriter(fileTmp)));
            print("OK\n");
            print(SEPARATOR_LINE);
            Thread.sleep(1000);

            print("CONTROLLO RECORD\n");
            while (stramSourceFile.hasNextLine()) {
                rigaSourceFile = stramSourceFile.nextLine();
                String[] campiRigaAnalizzata = rigaSourceFile.split(SEPARATOR);
                Record record;
                totali++;
                if (totali % 1000 == 0) {
                    print(".");
                    if (totali % 30000 == 0) {
                        print("\n");
                    }
                }
                if (campiRigaAnalizzata.length == N_COLONNE) { //controllo coerenza con il protocollo
                    record = Record.create(campiRigaAnalizzata);

                    record.verificaRecord(); //controllo consistenza del record
                    if (record.isCorrect()) {
                        if (!Controlli.recordIsPresent(record)) { //controllo duplicati
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
                        streamLogFile.write("<li>" + record.toString() + "</li>");
                        errati++;
                    }
                } else { //record incoerente con il protocollo

                    //todo gestione riga incoerente protocol
                    record = new Record();
                    record.addError("Numero di colonne errato");
                    streamLogFile.write(record.toString() + "\r\n");
                }
            }

            //scansione delle righe del file terminata, chiusura degli stream
            streamLogFile.write("</ul>");
            writerTempFile.close();
            stramSourceFile.close();

            int mancanti = Controlli.findMissingRecords(fileTmp, fileDW, streamLogFile);
            print("\n");
            Thread.sleep(1000);
            print("Record analizzati: " + totali + "\n");
            print("Record accettati: " + accettati + "\n");
            print("Record non accettati perche' duplicati: " + duplicati + "\n");
            print("Record non accettati perche' errati: " + errati + "\n");
            Thread.sleep(1000);

            if (stampaLog) {
                streamLogFile.write("<h3>" + MISSINGS_MESSAGE + "</h3><ul>");
                streamLogFile.write("Numero righe accettate : " + accettati + "<br>");
                streamLogFile.write("Numero righe duplicate : " + duplicati + "<br>");
                streamLogFile.write("Numero righe rifiutate : " + errati + "<br>");
                streamLogFile.write("Numero righe mancanti : " + mancanti + "<br>");
            }

            //mancanti = findMissingRecords2("Files/temp_demo.csv", "Files/new_incidenti_demo.csv", comuni); //controlla se ci sono righe mancanti
            //outStream.write("\r\n" + MISSINGS_MESSAGE + "\r\n");
            print("Caricamento nuovi record nel file destinazione.......");
            update(fileTmp, fileDW); //aggiunge le nuove righe al file dest
            print("OK\n");
            //outStream.write("Numero righe accettate : " + accettati + "\r\n");
            //outStream.write("Numero righe duplicate : " + duplicati + "\r\n");
            //outStream.write("Numero righe rifiutate : " + errati + "\r\n");
            //outStream.write("Numero righe mancanti : " + mancanti + "\r\n");

            streamLogFile.write(CLOSE);
            streamLogFile.close();
            print(SEPARATOR_LINE);
            Thread.sleep(1000);

        } catch (FileNotFoundException e) {
            System.out.println("Non è stato trovato il file");

            //Todo gestione throw impossibile aprire file datawharehouse
            //Todo gestire throw file comuni non presente
        } catch (WrongHeaderException ex) {
            System.out.print("FALLITO");
            System.out.println(ex);
        } catch (IOException e) {
            System.out.println("Io exception");
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

    public static void print(String s) {
        System.out.print(s);
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
