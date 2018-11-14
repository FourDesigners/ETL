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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author FourDesigners
 */
public class Main {
    private static final String PATH_SOURCE_FILE = "incidenti.csv";
    private static final String PATH_DEST_FILE = "new_incidenti.csv";
    private static final String SEPARATOR = ";";
    private static final int N_COLONNE = 55;
    
    public static void main(String[] args) throws IOException {
        int duplicati = 0;
        try{
            Scanner inputStream = new Scanner(new File(PATH_SOURCE_FILE));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_DEST_FILE)));
            String riga = inputStream.nextLine();
            out.println(riga);
            while(inputStream.hasNextLine()) {
                riga = inputStream.nextLine();
                String[] campi = riga.split(SEPARATOR);
                Set<String> errori = verifica(campi);
                if(errori.isEmpty()) {
                    if (!isDuplicate(campi)) {
                        // Verificare righe mancanti
                        out.print(campi[0]);
                        for(int i=1; i<campi.length; i++) {
                            out.print(SEPARATOR + campi[i]);
                        }
                        out.println();
                    }else {
                        duplicati++;
                    }
                }
            }
            out.close();
            inputStream.close();
        }catch(FileNotFoundException e) {
            System.out.println("Non è stato trovato il file " + PATH_SOURCE_FILE);
            e.printStackTrace();
        }
    }

    private static boolean verificaAnnoIncidente() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaProvincia() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaCODCOM() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaComune() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaVeicoliSenzaConducente() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiTotali() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaVeicoliConConducente() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiTotali() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiUomini() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeriti() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDonne() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiMortali() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiStradeExtra() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMortiStradeUrbane() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaFeritiStradeUrbane() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiStradeUrbane() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaPedoniMorti() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaPedoniFeriti() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaPasseggeriMorti() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaPasseggeriFeriti() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiDa65_piu() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiDa20_64() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiDa15_19() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiDa0_14() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeritiDa65_piu() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeritiDa20_64() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiInGiornata() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiIgnoti() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDa0_14() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDa15_19() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDa20_64() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaComuneFeritiTotali() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMortiTotali() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDa65_piu() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeritiDa0_14() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeritiDa15_19() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaFeritiStradeExtra() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiDa7_9() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaFeritiAutostrada() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMortiAutostrada() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiWeekend() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiIsolati() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiDa17_19() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiPioggia_Neve() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaAutocarri() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaAutovetture() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiMarcia() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiVeicoloPedone() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMortiStradeExtra() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiFeriali() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMotocicli() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiSereno() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiAutostrada() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiNotte() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiGiorno() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiNebbia() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaVelocipedi() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static Set verifica(String[] campi) {
        Set errori = new HashSet<String>();
        boolean corretto = true;
        
        if (campi.length != N_COLONNE) {
            errori.add("Numero di colonne errato");
        }
        int cont = 0;
        String annoIncidente = campi[cont];
        corretto = verificaAnnoIncidente();
        if (!corretto) {
            errori.add("Anno incidente errato");
        }

        cont++;
        String prov = campi[cont];
        corretto = verificaProvincia();
        if (!corretto) {
            errori.add("Provincia errata");
        }
        cont++;
        String comune = campi[cont];
        corretto = verificaComune();
        if (!corretto) {
            errori.add("Comune errato");
        }
        cont++;
        String incidentiTotali = campi[cont];
        corretto = verificaIncidentiTotali();
        if (!corretto) {
            errori.add("Numero di incidenti totali errato");
        }
        cont++;
        String incidentiMortali = campi[cont];
        corretto = verificaIncidentiMortali();
        if (!corretto) {
            errori.add("Numero di incidenti mortali errato");
        }
        cont++;
        String feritiTotali = campi[cont];
        corretto = verificaComuneFeritiTotali();
        if (!corretto) {
            errori.add("Numero di feriti totali errato");
        }
        cont++;
        String mortiTotali = campi[cont];
        corretto = verificaMortiTotali();
        if (!corretto) {
            errori.add("Numero di morti totali errato");
        }
        cont++;
        String veicoliConConducente = campi[cont];
        corretto = verificaVeicoliConConducente();
        if (!corretto) {
            errori.add("Numero di veicoli con conducente errato");
        }
        cont++;
        String veicoliSenzaConducente = campi[cont];
        corretto = verificaVeicoliSenzaConducente();
        if (!corretto) {
            errori.add("Numero di veicoli senza conducente errato");
        }
        cont++;
        String conducentiTotali = campi[cont];
        corretto = verificaConducentiTotali();
        if (!corretto) {
            errori.add("Numero di conducenti totali errato");
        }
        cont++;
        String conducentiDonne = campi[cont];
        corretto = verificaConducentiDonne();
        if (!corretto) {
            errori.add("Numero di conducenti donne errato");
        }
        
        cont++;
        String conducentiFeriti = campi[cont];
        corretto = verificaConducentiFeriti();
        if (!corretto) {
            errori.add("Numero di conducenti feriti errato");
        }
        cont++;
        String conducentiMortiInGiornata = campi[cont];
        corretto = verificaConducentiMortiInGiornata();
        if (!corretto) {
            errori.add("Numero di conducenti morti entro 24h errato");
        }
        cont++;
        String conducentiIgnoti = campi[cont];
        corretto = verificaConducentiIgnoti();
        if (!corretto) {
            errori.add("Numero di conducenti ignoti errato");
        }
        cont++;
        String conducentiDa0_14 = campi[cont];
        corretto = verificaConducentiDa0_14();
        if (!corretto) {
            errori.add("Numero di conducenti da 0 a 14 anni errato");
        }
        cont++;
        String conducentiDa15_19 = campi[cont];
        corretto = verificaConducentiDa15_19();
        if (!corretto) {
            errori.add("Numero di conducenti dai 15 ai 19 anni errato");
        }
        cont++;
        String conducentiDa20_64 = campi[cont];
        corretto = verificaConducentiDa20_64();
        if (!corretto) {
            errori.add("Numero di conducenti dai 20 ai 64 anni errato");
        }
        cont++;
        String conducentiDa65_piu = campi[cont];
        corretto = verificaConducentiDa65_piu();
        if (!corretto) {
            errori.add("Numero di conducenti dai 65 anni in piu' errato");
        }
        cont++;
        String conducentiFeritiDa0_14 = campi[cont];
        corretto = verificaConducentiFeritiDa0_14();
        if (!corretto) {
            errori.add("Numero di feriti da 0 a 14 anni errato");
        }
        cont++;
        String conducentiFeritiDa15_19 = campi[cont];
        corretto = verificaConducentiFeritiDa15_19();
        if (!corretto) {
            errori.add("Numero di conducenti feriti dai 15 ai 19 anni errato");
        }
        cont++;
        String conducentiFeritiDa20_64 = campi[cont];
        corretto = verificaConducentiFeritiDa20_64();
        if (!corretto) {
            errori.add("Numero di conducenti feriti dai 20 ai 64 anni errato");
        }
        cont++;
        String conducentiFeritiDa65_piu = campi[cont];
        corretto = verificaConducentiFeritiDa65_piu();
        if (!corretto) {
            errori.add("Numero di conducenti feriti dai 65 anni in piu' errato");
        }
        cont++;
        String conducentiMortiDa0_14 = campi[cont];
        corretto = verificaConducentiMortiDa0_14();
        if (!corretto) {
            errori.add("Numero di conducenti morti da 0 a 14 anni errato");
        }
        cont++;
        String conducentiMortiDa15_19 = campi[cont];
        corretto = verificaConducentiMortiDa15_19();
        if (!corretto) {
            errori.add("Numero di conducenti morti dai 15 ai 19 anni errato");
        }
        cont++;
        String conducentiMortiDa20_64 = campi[cont];
        corretto = verificaConducentiMortiDa20_64();
        if (!corretto) {
            errori.add("Numero di conducenti morti dai 20 ai 64 anni errato");
        }
        cont++;
        String conducentiMortiDa65_piu = campi[cont];
        corretto = verificaConducentiMortiDa65_piu();
        if (!corretto) {
            errori.add("Numero di conducenti morti dai 65 anni in piu' errato");
        }
        cont++;
        String passeggeriFeriti = campi[cont];
        corretto = verificaPasseggeriFeriti();
        if (!corretto) {
            errori.add("Numero di passeggeri feriti errato");
        }
        cont++;
        String passeggeriMorti = campi[cont];
        corretto = verificaPasseggeriMorti();
        if (!corretto) {
            errori.add("Numero di passeggeri morti errato");
        }
        cont++;
        String pedoniFeriti = campi[cont];
        corretto = verificaPedoniFeriti();
        if (!corretto) {
            errori.add("Numero di pedoni feriti errato");
        }
        cont++;
        String pedoniMorti = campi[cont];
        corretto = verificaPedoniMorti();
        if (!corretto) {
            errori.add("Numero di pedoni morti errato");
        }
        cont++;
        String incidentiStradeUrbane = campi[cont];
        corretto = verificaIncidentiStradeUrbane();
        if (!corretto) {
            errori.add("Numero di incidenti nelle urbane errato");
        }
        cont++;
        String feritiStradeUrbane = campi[cont];
        corretto = verificaFeritiStradeUrbane();
        if (!corretto) {
            errori.add("Numero di feriti nelle urbane errato");
        }
        cont++;
        String mortiStradeUrbane = campi[cont];
        corretto = verificaMortiStradeUrbane();
        if (!corretto) {
            errori.add("Numero di morti nelle urbane errato");
        }
        cont++;
        String incidentiStradeExtra = campi[cont];
        corretto = verificaIncidentiStradeExtra();
        if (!corretto) {
            errori.add("Numero di incidenti nelle extraurbane errato");
        }
        cont++;
        String feritiStradeExtra = campi[cont];
        corretto = verificaFeritiStradeExtra();
        if (!corretto) {
            errori.add("Numero di feriti nelle extraurbane errato");
        }
        cont++;
        String mortiStradeExtra = campi[cont];
        corretto = verificaMortiStradeExtra();
        if (!corretto) {
            errori.add("Numero di morti nelle extraurbane errato");
        }
        cont++;
        String incidentiAutostrada = campi[cont];
        corretto = verificaIncidentiAutostrada();
        if (!corretto) {
            errori.add("Numero di incidenti in autostrada errato");
        }
        cont++;
        String feritiAutostrada = campi[cont];
        corretto = verificaFeritiAutostrada();
        if (!corretto) {
            errori.add("Numero di feriti in autostrada errato");
        }
        cont++;
        String mortiAutostrada = campi[cont];
        corretto = verificaMortiAutostrada();
        if (!corretto) {
            errori.add("Numero di morti in autostrada errato");
        }
        cont++;
        String incidentiMarcia = campi[cont];
        corretto = verificaIncidentiMarcia();
        if (!corretto) {
            errori.add("Numero di incidenti in marcia errato");
        }
        cont++;
        String incidentiVeicoloPedone = campi[cont];
        corretto = verificaIncidentiVeicoloPedone();
        if (!corretto) {
            errori.add("Numero di di incidenti veicolo-pedone errato");
        }
        cont++;
        String incidentiIsolati = campi[cont];
        corretto = verificaIncidentiIsolati();
        if (!corretto) {
            errori.add("Numero di incidenti isolati errato");
        }
        cont++;
        String incidentiWeekend = campi[cont];
        corretto = verificaIncidentiWeekend();
        if (!corretto) {
            errori.add("Numero di incidenti nei weekend errato");
        }
        cont++;
        String incidentiFeriali = campi[cont];
        corretto = verificaIncidentiFeriali();
        if (!corretto) {
            errori.add("Numero di incidenti nei feriali errato");
        }
        cont++;
        String incidentiNotte = campi[cont];
        corretto = verificaIncidentiNotte();
        if (!corretto) {
            errori.add("Numero di incidenti di notte errato");
        }
        cont++;
        String incidentiGiorno = campi[cont];
        corretto = verificaIncidentiGiorno();
        if (!corretto) {
            errori.add("Numero di incidenti di giorno errato");
        }
        cont++;
        String incidentiDa7_9 = campi[cont];
        corretto = verificaIncidentiDa7_9();
        if (!corretto) {
            errori.add("Numero di incidenti dalle 7 alle 9 errato");
        }
        cont++;
        String incidentiDa17_19 = campi[cont];
        corretto = verificaIncidentiDa17_19();
        if (!corretto) {
            errori.add("Numero di incidenti dalle 17 alle 19 errato");
        }
        cont++;
        String incidentiSereno = campi[cont];
        corretto = verificaIncidentiSereno();
        if (!corretto) {
            errori.add("Numero di incidenti con sereno errato");
        }
        cont++;
        String incidentiNebbia = campi[cont];
        corretto = verificaIncidentiNebbia();
        if (!corretto) {
            errori.add("Numero di incidenti con nebbia errato");
        }
        cont++;
        String incidentiPioggia_Neve = campi[cont];
        corretto = verificaIncidentiPioggia_Neve();
        if (!corretto) {
            errori.add("Numero di incidenti con pioggia e neve errato");
        }
        cont++;
        String autovetture = campi[cont];
        corretto = verificaAutovetture();
        if (!corretto) {
            errori.add("Numero di autovetture errato");
        }
        cont++;
        String autocarri = campi[cont];
        corretto = verificaAutocarri();
        if (!corretto) {
            errori.add("Numero di autocarri errato");
        }
        cont++;
        String motocicli = campi[cont];
        corretto = verificaMotocicli();
        if (!corretto) {
            errori.add("Numero di motocicli errato");
        }
        cont++;
        String velocipedi = campi[cont];
        corretto = verificaVelocipedi();
        if (!corretto) {
            errori.add("Numero di velocipiedi errato");
        }
        // Mancano i vincoli tra le variabili
        return errori;
    }

    private static boolean isDuplicate(String[] campi) {
        return false;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
