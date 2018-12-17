/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

import eccezioni.WrongHeaderException;
import static etlScript.Constants.SEPARATOR;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


/**
 *
 * @author Pan
 */
public class Controlli {

    static Set<Chiave> chiavi = new HashSet<>();
    static Map<String, Set<String>> comuni = new HashMap<>();

    static boolean verificaIntestazione(String[] split) throws WrongHeaderException {

        if (split.length != Constants.CAMPI_OLTP.length) {
            throw new WrongHeaderException();
        }
        int count = 0;
        while(!split[0].equals(Constants.CAMPI_OLTP[0]) && count<3){
            split[0] = split[0].substring(1);
            count++;
        }
        if(count == 3) {
            throw new WrongHeaderException();
        }
        
        for (int i = 0; i < Constants.CAMPI_OLTP.length; i++) {
            if (!Constants.CAMPI_OLTP[i].equals(split[i])) {
                throw new WrongHeaderException(i);
            }
        }
        return true;
    }

    public static void fillKeys(String datawharehouseFile) throws FileNotFoundException {

        try (Scanner inputStream = new Scanner(new File(datawharehouseFile))) {
            inputStream.nextLine();
            while (inputStream.hasNextLine()) {
                String riga = inputStream.nextLine();
                String[] campi = riga.split(SEPARATOR);
                chiavi.add(new Chiave(campi[0], campi[2]));

            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Impossibile aprire il file " + datawharehouseFile);
        }

    }

    static void addChiave(Chiave chiave) {
        chiavi.add(chiave);
    }

    static void fillMunicipalities(String fileComuni) throws FileNotFoundException {

        try (Scanner inputStream = new Scanner(new File(fileComuni))) {
            while (inputStream.hasNextLine()) {
                String rigaComune = inputStream.nextLine();
                String[] campiComune = rigaComune.split(SEPARATOR);
                if (comuni.containsKey(campiComune[0])) {
                    comuni.get(campiComune[0]).add(campiComune[1]);
                } else {
                    comuni.put(campiComune[0], new HashSet<>());
                    comuni.get(campiComune[0]).add(campiComune[1]);
                }
            }

        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("File Comuni.csv non presente");
        }
    }

    static Map<String, Set<String>> getComuni() {
        return comuni;
    }

    public static boolean recordIsPresent(Record record) {
        return chiavi.contains(new Chiave(record.getAnnoIncidente(), record.getComune()));
    }

    static int findMissingRecords(String pathTempFile, String pathDatawharehouseCsv, PrintWriter outPrintWriter) {
        int mancanti = 0;
        try {
            TreeMap<String, Set<String>> annoComune = new TreeMap<>();
            Scanner inputStreamTempFile = new Scanner(new File(pathTempFile));

            while (inputStreamTempFile.hasNextLine()) { //legge le righe del file
                String riga = inputStreamTempFile.nextLine();
                String[] campiRiga = riga.split(SEPARATOR);

                if (!annoComune.containsKey(campiRiga[0])) { //se l'elenco  annoComune non contiene l'anno della riga

                    annoComune.put(campiRiga[0], new HashSet<>());// inserisce l'anno della riga nell'elenco

                    comuni.keySet().forEach((provincia) -> {
                        annoComune.get(campiRiga[0]).addAll(comuni.get(provincia)); //inserisce per quell'anno tutti i comuni nel treemap
                    });
                }
                annoComune.get(campiRiga[0]).remove(campiRiga[2]); //rimuove il comune dal treemap, per il rispettivo anno
            }
            inputStreamTempFile.close();

            // apre lo stream del file wharehouse.csv
            inputStreamTempFile = new Scanner(new File(pathDatawharehouseCsv));
            inputStreamTempFile.nextLine(); //salta l'intestazione
            while (inputStreamTempFile.hasNextLine()) { //finchè il file ha una nuova riga
                String riga = inputStreamTempFile.nextLine();
                String[] campiRiga = riga.split(SEPARATOR);
                if (!annoComune.containsKey(campiRiga[0])) {//se l'elenco  annoComune non contiene l'anno della riga
                    annoComune.put(campiRiga[0], new HashSet<>());// inserisce l'anno della riga nell'elenco
                    comuni.keySet().forEach((provincia) -> {//inserisce per quell'anno tutti i comuni nel treemap
                        annoComune.get(campiRiga[0]).addAll(comuni.get(provincia));
                    });
                }
                annoComune.get(campiRiga[0]).remove(campiRiga[2]);//rimuove il comune dal treemap, per il rispettivo anno
            }
            inputStreamTempFile.close();

            for (String anno : annoComune.keySet()) {
                mancanti += annoComune.get(anno).size(); //conta i comuni mancanti per quell'anno
                //out.write("ANNO " + anno + " : " + annoComune.get(anno).size());
                //out.write("\r\n");
                outPrintWriter.write("<li>ANNO " + anno + " : " + annoComune.get(anno).size() + "</li>");
            }
            //out.write("\r\n");
            outPrintWriter.write("</ul><br>");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mancanti;
    }
}
