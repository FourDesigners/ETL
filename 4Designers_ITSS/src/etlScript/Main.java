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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author FourDesigners
 */
public class Main {
    private static final String PATH_COMUNI_FILE = "Files/comuni.csv";
    private static final String PATH_SOURCE_FILE = "Files/incidenti.csv";
    private static final String PATH_TEMP_FILE = "Files/temp.csv";
    private static final String PATH_DEST_FILE = "Files/new_incidenti.csv";
    private static final String SEPARATOR = ";";
    private static final int N_COLONNE = 55;
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws IOException {
        int duplicati = 0;
        Set<Chiave> chiavi = new HashSet<>();
        Map<String, Set<String>> comuni = new HashMap<>();
        
        fillMunicipalities(comuni, PATH_COMUNI_FILE);
        try{
            Scanner inputStream = new Scanner(new File(PATH_SOURCE_FILE));
            String riga = inputStream.nextLine();
            if(!new File(PATH_DEST_FILE).exists()) {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_DEST_FILE)));
                out.println(riga);
                out.close();
            }else {
                fillKeys(chiavi, PATH_DEST_FILE);
            }
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_TEMP_FILE)));
            while(inputStream.hasNextLine()) {
                riga = inputStream.nextLine();
                String[] campi = riga.split(SEPARATOR);
                Record record;
                if (campi.length == N_COLONNE) {
                    record = Record.create(campi);
                    record.verifica(comuni);
                    if(record.isCorrect()) {
                        if (!record.isPresent(chiavi)) {
                            chiavi.add(new Chiave(campi[0], campi[2]));
                            // Verificare righe mancanti
                            out.print(campi[0]);
                            for(int i=1; i<campi.length; i++) {
                                out.print(SEPARATOR + campi[i]);
                            }
                            out.println();
                        }else {
                            duplicati++;
                        }
                    }else {
                        System.out.println(record);
                    }
                }else {
                    record = new Record();
                    record.addError("Numero di colonne errato");
                    System.out.println(record);
                }
            }
            out.close();
            inputStream.close();
            update(PATH_TEMP_FILE, PATH_DEST_FILE);
            System.out.println("Numero righe duplicate : " + duplicati);
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
            fw.write(riga + "\n");
        }
        fw.close();
        inputStream.close();
    }

    private static void fillKeys(Set<Chiave> chiavi, String filename) {
        try {
            Scanner inputStream = new Scanner(new File(filename));
            inputStream.nextLine();
            while(inputStream.hasNextLine()) {
                String riga = inputStream.nextLine();
                String[] campi = riga.split(SEPARATOR);
                chiavi.add(new Chiave(campi[0], campi[2]));
            }
            inputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void fillMunicipalities(Map<String, Set<String>> comuni, String filename) {
        try {
            Scanner inputStream = new Scanner(new File(filename));
            while(inputStream.hasNextLine()) {
                String riga = inputStream.nextLine();
                String[] campi = riga.split(SEPARATOR);
                if(comuni.containsKey(campi[0])) {
                    comuni.get(campi[0]).add(campi[1]);
                }else {
                    comuni.put(campi[0], new HashSet<String>());
                    comuni.get(campi[0]).add(campi[1]);
                }
            }
            inputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File dei comuni non presente");
        }
    }
}
