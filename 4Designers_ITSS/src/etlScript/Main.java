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
    private static final String PATH_SOURCE_FILE = "Files/incidenti.csv";
    private static final String PATH_TEMP_FILE = "Files/temp.csv";
    private static final String PATH_DEST_FILE = "Files/new_incidenti.csv";
    private static final String SEPARATOR = ";";
    private static final int N_COLONNE = 55;
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws IOException {
        int duplicati = 0;
        try{
            Scanner inputStream = new Scanner(new File(PATH_SOURCE_FILE));
            String riga = inputStream.nextLine();
            if(!new File(PATH_DEST_FILE).exists()) {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_DEST_FILE)));
                out.println(riga);
                out.close();
            }
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_TEMP_FILE)));
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
            update(PATH_TEMP_FILE, PATH_DEST_FILE);
        }catch(FileNotFoundException e) {
            System.out.println("Non è stato trovato il file");
        }
    }

    private static boolean verificaAnnoIncidente(String annoIncidente) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaProvincia(String prov) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaCODCOM() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaComune(String comune) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaVeicoliSenzaConducente(String veicoliSenzaConducente) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiTotali(String incidentiTotali) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaVeicoliConConducente(String veicoliConConducente) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiTotali(String conducentiTotali) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiUomini() {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeriti(String conducentiFeriti) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDonne(String conducentiDonne) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiMortali(String incidentiMortali) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiStradeExtra(String incidentiStradeExtra) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMortiStradeUrbane(String mortiStradeUrbane) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaFeritiStradeUrbane(String feritiStradeUrbane) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiStradeUrbane(String incidentiStradeUrbane) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaPedoniMorti(String pedoniMorti) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaPedoniFeriti(String pedoniFeriti) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaPasseggeriMorti(String passeggeriMorti) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaPasseggeriFeriti(String passeggeriFeriti) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiDa65_piu(String conducentiDa65_piu) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiDa20_64(String conducentiMortiDa20_64) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiDa15_19(String conducentiMortiDa15_19) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiDa0_14(String conducentiMortiDa0_14) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeritiDa65_piu(String conducentiFeritiDa65_piu) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeritiDa20_64(String conducentiFeritiDa20_64) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiMortiInGiornata(String conducentiMortiInGiornata) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiIgnoti(String conducentiIgnoti) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDa0_14(String conducentiDa0_14) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDa15_19(String conducentiDa15_19) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDa20_64(String conducentiDa20_64) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaComuneFeritiTotali(String feritiTotali) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMortiTotali(String mortiTotali) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiDa65_piu(String conducentiDa65_piu) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeritiDa0_14(String conducentiFeritiDa0_14) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaConducentiFeritiDa15_19(String conducentiFeritiDa15_19) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaFeritiStradeExtra(String feritiStradeExtra) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiDa7_9(String incidentiDa7_9) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaFeritiAutostrada(String feritiAutostrada) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMortiAutostrada(String mortiAutostrada) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiWeekend(String incidentiWeekend) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiIsolati(String incidentiIsolati) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiDa17_19(String incidentiDa17_19) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiPioggia_Neve(String incidentiPioggiaNeve) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaAutocarri(String autocarri) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaAutovetture(String autovetture) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiMarcia(String incidentiMarcia) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiVeicoloPedone(String incidentiVeicoloPedone) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMortiStradeExtra(String mortiStradeExtra) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiFeriali(String incidentiFeriali) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaMotocicli(String motocicli) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiSereno(String incidentiSereno) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiAutostrada(String incidentiAutostrada) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiNotte(String incidentiNotte) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiGiorno(String incidentiGiorno) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean verificaIncidentiNebbia(String incidentiNebbia) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    private static Set verifica(String[] campi) {
        Set errori = new HashSet<>();
        @SuppressWarnings("UnusedAssignment")
        boolean corretto = true;
        
        if (campi.length != N_COLONNE) {
            errori.add("Numero di colonne errato");
        }
        int cont = 0;
        String annoIncidente = campi[cont];
        corretto = verificaAnnoIncidente(annoIncidente);
        if (!corretto) {
            errori.add("Anno incidente errato");
        }

        cont++;
        String prov = campi[cont];
        corretto = verificaProvincia(prov);
        if (!corretto) {
            errori.add("Provincia errata");
        }
        cont++;
        String comune = campi[cont];
        corretto = verificaComune(comune);
        if (!corretto) {
            errori.add("Comune errato");
        }
        cont++;
        String incidentiTotali = campi[cont];
        corretto = verificaIncidentiTotali(incidentiTotali);
        if (!corretto) {
            errori.add("Numero di incidenti totali errato");
        }
        cont++;
        String incidentiMortali = campi[cont];
        corretto = verificaIncidentiMortali(incidentiMortali);
        if (!corretto) {
            errori.add("Numero di incidenti mortali errato");
        }
        cont++;
        String feritiTotali = campi[cont];
        corretto = verificaComuneFeritiTotali(feritiTotali);
        if (!corretto) {
            errori.add("Numero di feriti totali errato");
        }
        cont++;
        String mortiTotali = campi[cont];
        corretto = verificaMortiTotali(mortiTotali);
        if (!corretto) {
            errori.add("Numero di morti totali errato");
        }
        cont++;
        String veicoliConConducente = campi[cont];
        corretto = verificaVeicoliConConducente(veicoliConConducente);
        if (!corretto) {
            errori.add("Numero di veicoli con conducente errato");
        }
        cont++;
        String veicoliSenzaConducente = campi[cont];
        corretto = verificaVeicoliSenzaConducente(veicoliSenzaConducente);
        if (!corretto) {
            errori.add("Numero di veicoli senza conducente errato");
        }
        cont++;
        String conducentiTotali = campi[cont];
        corretto = verificaConducentiTotali(conducentiTotali);
        if (!corretto) {
            errori.add("Numero di conducenti totali errato");
        }
        cont++;
        String conducentiDonne = campi[cont];
        corretto = verificaConducentiDonne(conducentiDonne);
        if (!corretto) {
            errori.add("Numero di conducenti donne errato");
        }
        
        cont++;
        String conducentiFeriti = campi[cont];
        corretto = verificaConducentiFeriti(conducentiFeriti);
        if (!corretto) {
            errori.add("Numero di conducenti feriti errato");
        }
        cont++;
        String conducentiMortiInGiornata = campi[cont];
        corretto = verificaConducentiMortiInGiornata(conducentiMortiInGiornata);
        if (!corretto) {
            errori.add("Numero di conducenti morti entro 24h errato");
        }
        cont++;
        String conducentiIgnoti = campi[cont];
        corretto = verificaConducentiIgnoti(conducentiIgnoti);
        if (!corretto) {
            errori.add("Numero di conducenti ignoti errato");
        }
        cont++;
        String conducentiDa0_14 = campi[cont];
        corretto = verificaConducentiDa0_14(conducentiDa0_14);
        if (!corretto) {
            errori.add("Numero di conducenti da 0 a 14 anni errato");
        }
        cont++;
        String conducentiDa15_19 = campi[cont];
        corretto = verificaConducentiDa15_19(conducentiDa15_19);
        if (!corretto) {
            errori.add("Numero di conducenti dai 15 ai 19 anni errato");
        }
        cont++;
        String conducentiDa20_64 = campi[cont];
        corretto = verificaConducentiDa20_64(conducentiDa20_64);
        if (!corretto) {
            errori.add("Numero di conducenti dai 20 ai 64 anni errato");
        }
        cont++;
        String conducentiDa65_piu = campi[cont];
        corretto = verificaConducentiDa65_piu(conducentiDa65_piu);
        if (!corretto) {
            errori.add("Numero di conducenti dai 65 anni in piu' errato");
        }
        cont++;
        String conducentiFeritiDa0_14 = campi[cont];
        corretto = verificaConducentiFeritiDa0_14(conducentiFeritiDa0_14);
        if (!corretto) {
            errori.add("Numero di feriti da 0 a 14 anni errato");
        }
        cont++;
        String conducentiFeritiDa15_19 = campi[cont];
        corretto = verificaConducentiFeritiDa15_19(conducentiFeritiDa15_19);
        if (!corretto) {
            errori.add("Numero di conducenti feriti dai 15 ai 19 anni errato");
        }
        cont++;
        String conducentiFeritiDa20_64 = campi[cont];
        corretto = verificaConducentiFeritiDa20_64(conducentiFeritiDa20_64);
        if (!corretto) {
            errori.add("Numero di conducenti feriti dai 20 ai 64 anni errato");
        }
        cont++;
        String conducentiFeritiDa65_piu = campi[cont];
        corretto = verificaConducentiFeritiDa65_piu(conducentiFeritiDa65_piu);
        if (!corretto) {
            errori.add("Numero di conducenti feriti dai 65 anni in piu' errato");
        }
        cont++;
        String conducentiMortiDa0_14 = campi[cont];
        corretto = verificaConducentiMortiDa0_14(conducentiMortiDa0_14);
        if (!corretto) {
            errori.add("Numero di conducenti morti da 0 a 14 anni errato");
        }
        cont++;
        String conducentiMortiDa15_19 = campi[cont];
        corretto = verificaConducentiMortiDa15_19(conducentiMortiDa15_19);
        if (!corretto) {
            errori.add("Numero di conducenti morti dai 15 ai 19 anni errato");
        }
        cont++;
        String conducentiMortiDa20_64 = campi[cont];
        corretto = verificaConducentiMortiDa20_64(conducentiMortiDa20_64);
        if (!corretto) {
            errori.add("Numero di conducenti morti dai 20 ai 64 anni errato");
        }
        cont++;
        String conducentiMortiDa65_piu = campi[cont];
        corretto = verificaConducentiMortiDa65_piu(conducentiDa65_piu);
        if (!corretto) {
            errori.add("Numero di conducenti morti dai 65 anni in piu' errato");
        }
        cont++;
        String passeggeriFeriti = campi[cont];
        corretto = verificaPasseggeriFeriti(passeggeriFeriti);
        if (!corretto) {
            errori.add("Numero di passeggeri feriti errato");
        }
        cont++;
        String passeggeriMorti = campi[cont];
        corretto = verificaPasseggeriMorti(passeggeriMorti);
        if (!corretto) {
            errori.add("Numero di passeggeri morti errato");
        }
        cont++;
        String pedoniFeriti = campi[cont];
        corretto = verificaPedoniFeriti(pedoniFeriti);
        if (!corretto) {
            errori.add("Numero di pedoni feriti errato");
        }
        cont++;
        String pedoniMorti = campi[cont];
        corretto = verificaPedoniMorti(pedoniMorti);
        if (!corretto) {
            errori.add("Numero di pedoni morti errato");
        }
        cont++;
        String incidentiStradeUrbane = campi[cont];
        corretto = verificaIncidentiStradeUrbane(incidentiStradeUrbane);
        if (!corretto) {
            errori.add("Numero di incidenti nelle urbane errato");
        }
        cont++;
        String feritiStradeUrbane = campi[cont];
        corretto = verificaFeritiStradeUrbane(feritiStradeUrbane);
        if (!corretto) {
            errori.add("Numero di feriti nelle urbane errato");
        }
        cont++;
        String mortiStradeUrbane = campi[cont];
        corretto = verificaMortiStradeUrbane(mortiStradeUrbane);
        if (!corretto) {
            errori.add("Numero di morti nelle urbane errato");
        }
        cont++;
        String incidentiStradeExtra = campi[cont];
        corretto = verificaIncidentiStradeExtra(incidentiStradeExtra);
        if (!corretto) {
            errori.add("Numero di incidenti nelle extraurbane errato");
        }
        cont++;
        String feritiStradeExtra = campi[cont];
        corretto = verificaFeritiStradeExtra(feritiStradeExtra);
        if (!corretto) {
            errori.add("Numero di feriti nelle extraurbane errato");
        }
        cont++;
        String mortiStradeExtra = campi[cont];
        corretto = verificaMortiStradeExtra(mortiStradeExtra);
        if (!corretto) {
            errori.add("Numero di morti nelle extraurbane errato");
        }
        cont++;
        String incidentiAutostrada = campi[cont];
        corretto = verificaIncidentiAutostrada(incidentiAutostrada);
        if (!corretto) {
            errori.add("Numero di incidenti in autostrada errato");
        }
        cont++;
        String feritiAutostrada = campi[cont];
        corretto = verificaFeritiAutostrada(feritiAutostrada);
        if (!corretto) {
            errori.add("Numero di feriti in autostrada errato");
        }
        cont++;
        String mortiAutostrada = campi[cont];
        corretto = verificaMortiAutostrada(mortiAutostrada);
        if (!corretto) {
            errori.add("Numero di morti in autostrada errato");
        }
        cont++;
        String incidentiMarcia = campi[cont];
        corretto = verificaIncidentiMarcia(incidentiMarcia);
        if (!corretto) {
            errori.add("Numero di incidenti in marcia errato");
        }
        cont++;
        String incidentiVeicoloPedone = campi[cont];
        corretto = verificaIncidentiVeicoloPedone(incidentiVeicoloPedone);
        if (!corretto) {
            errori.add("Numero di di incidenti veicolo-pedone errato");
        }
        cont++;
        String incidentiIsolati = campi[cont];
        corretto = verificaIncidentiIsolati(incidentiIsolati);
        if (!corretto) {
            errori.add("Numero di incidenti isolati errato");
        }
        cont++;
        String incidentiWeekend = campi[cont];
        corretto = verificaIncidentiWeekend(incidentiWeekend);
        if (!corretto) {
            errori.add("Numero di incidenti nei weekend errato");
        }
        cont++;
        String incidentiFeriali = campi[cont];
        corretto = verificaIncidentiFeriali(incidentiFeriali);
        if (!corretto) {
            errori.add("Numero di incidenti nei feriali errato");
        }
        cont++;
        String incidentiNotte = campi[cont];
        corretto = verificaIncidentiNotte(incidentiNotte);
        if (!corretto) {
            errori.add("Numero di incidenti di notte errato");
        }
        cont++;
        String incidentiGiorno = campi[cont];
        corretto = verificaIncidentiGiorno(incidentiGiorno);
        if (!corretto) {
            errori.add("Numero di incidenti di giorno errato");
        }
        cont++;
        String incidentiDa7_9 = campi[cont];
        corretto = verificaIncidentiDa7_9(incidentiDa7_9);
        if (!corretto) {
            errori.add("Numero di incidenti dalle 7 alle 9 errato");
        }
        cont++;
        String incidentiDa17_19 = campi[cont];
        corretto = verificaIncidentiDa17_19(incidentiDa17_19);
        if (!corretto) {
            errori.add("Numero di incidenti dalle 17 alle 19 errato");
        }
        cont++;
        String incidentiSereno = campi[cont];
        corretto = verificaIncidentiSereno(incidentiSereno);
        if (!corretto) {
            errori.add("Numero di incidenti con sereno errato");
        }
        cont++;
        String incidentiNebbia = campi[cont];
        corretto = verificaIncidentiNebbia(incidentiNebbia);
        if (!corretto) {
            errori.add("Numero di incidenti con nebbia errato");
        }
        cont++;
        String incidentiPioggiaNeve = campi[cont];
        corretto = verificaIncidentiPioggia_Neve(incidentiPioggiaNeve);
        if (!corretto) {
            errori.add("Numero di incidenti con pioggia e neve errato");
        }
        cont++;
        String autovetture = campi[cont];
        corretto = verificaAutovetture(autovetture);
        if (!corretto) {
            errori.add("Numero di autovetture errato");
        }
        cont++;
        String autocarri = campi[cont];
        corretto = verificaAutocarri(autocarri);
        if (!corretto) {
            errori.add("Numero di autocarri errato");
        }
        cont++;
        String motocicli = campi[cont];
        corretto = verificaMotocicli(motocicli);
        if (!corretto) {
            errori.add("Numero di motocicli errato");
        }
        cont++;
        String velocipedi = campi[cont];
        corretto = verificaVelocipedi(velocipedi);
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

    private static boolean verificaVelocipedi(String velocipedi) {
        return true;
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
