/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author FourDesigners
 */
public class Record {

    private static final String ANNO_INIZIALE = "2000";
    private static final String ANNO_FINALE = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    private static int count = 2;
    private final int idRecord;
    private String annoIncidente;
    private String prov;
    private String comune;
    private String incidentiTotali;
    private String incidentiMortali;
    private String feritiTotali;
    private String mortiTotali;
    private String veicoliConConducente;
    private String veicoliSenzaConducente;
    private String conducentiTotali;
    private String conducentiDonne;
    private String conducentiFeriti;
    private String conducentiMortiInGiornata;
    private String conducentiIgnoti;
    private String conducentiDa0_14;
    private String conducentiDa15_19;
    private String conducentiDa20_64;
    private String conducentiDa65_piu;
    private String conducentiFeritiDa0_14;
    private String conducentiFeritiDa15_19;
    private String conducentiFeritiDa20_64;
    private String conducentiFeritiDa65_piu;
    private String conducentiMortiDa0_14;
    private String conducentiMortiDa15_19;
    private String conducentiMortiDa20_64;
    private String conducentiMortiDa65_piu;
    private String passeggeriFeriti;
    private String passeggeriMorti;
    private String pedoniFeriti;
    private String pedoniMorti;
    private String incidentiStradeUrbane;
    private String feritiStradeUrbane;
    private String mortiStradeUrbane;
    private String incidentiStradeExtra;
    private String feritiStradeExtra;
    private String mortiStradeExtra;
    private String incidentiAutostrada;
    private String feritiAutostrada;
    private String mortiAutostrada;
    private String incidentiMarcia;
    private String incidentiVeicoloPedone;
    private String incidentiIsolati;
    private String incidentiWeekend;
    private String incidentiFeriali;
    private String incidentiNotte;
    private String incidentiGiorno;
    private String incidentiDa7_9;
    private String incidentiDa17_19;
    private String incidentiSereno;
    private String incidentiNebbia;
    private String incidentiPioggiaNeve;
    private String autovetture;
    private String autocarri;
    private String motocicli;
    private String velocipedi;
    private Set<String> erroriRecord;
    private Set<String> warningsRecord;

    public Record() {
        idRecord = count++;
        erroriRecord = new HashSet<>();
        warningsRecord = new HashSet<>();
    }

    public Record(String annoIncidente, String prov, String comune, String incidentiTotali, String incidentiMortali,
            String feritiTotali, String mortiTotali, String veicoliConConducente, String veicoliSenzaConducente, String conducentiTotali,
            String conducentiDonne, String conducentiFeriti, String conducentiMortiInGiornata, String conducentiIgnoti, String conducentiDa0_14,
            String conducentiDa15_19, String conducentiDa20_64, String conducentiDa65_piu, String conducentiFeritiDa0_14, String conducentiFeritiDa15_19,
            String conducentiFeritiDa20_64, String conducentiFeritiDa65_piu, String conducentiMortiDa0_14, String conducentiMortiDa15_19, String conducentiMortiDa20_64, String conducentiMortiDa65_piu,
            String passeggeriFeriti, String passeggeriMorti, String pedoniFeriti, String pedoniMorti, String incidentiStradeUrbane,
            String feritiStradeUrbane, String mortiStradeUrbane, String incidentiStradeExtra, String feritiStradeExtra, String mortiStradeExtra,
            String incidentiAutostrada, String feritiAutostrada, String mortiAutostrada, String incidentiMarcia, String incidentiVeicoloPedone,
            String incidentiIsolati, String incidentiWeekend, String incidentiFeriali, String incidentiNotte, String incidentiGiorno,
            String incidentiDa7_9, String incidentiDa17_19, String incidentiSereno, String incidentiNebbia, String incidentiPioggiaNeve,
            String autovetture, String autocarri, String motocicli, String velocipedi) {
        this();
        this.annoIncidente = annoIncidente;
        this.prov = prov;
        this.comune = comune;
        this.incidentiTotali = incidentiTotali;
        this.incidentiMortali = incidentiMortali;
        this.feritiTotali = feritiTotali;
        this.mortiTotali = mortiTotali;
        this.veicoliConConducente = veicoliConConducente;
        this.veicoliSenzaConducente = veicoliSenzaConducente;
        this.conducentiTotali = conducentiTotali;
        this.conducentiDonne = conducentiDonne;
        this.conducentiFeriti = conducentiFeriti;
        this.conducentiMortiInGiornata = conducentiMortiInGiornata;
        this.conducentiIgnoti = conducentiIgnoti;
        this.conducentiDa0_14 = conducentiDa0_14;
        this.conducentiDa15_19 = conducentiDa15_19;
        this.conducentiDa20_64 = conducentiDa20_64;
        this.conducentiDa65_piu = conducentiDa65_piu;
        this.conducentiFeritiDa0_14 = conducentiFeritiDa0_14;
        this.conducentiFeritiDa15_19 = conducentiFeritiDa15_19;
        this.conducentiFeritiDa20_64 = conducentiFeritiDa20_64;
        this.conducentiFeritiDa65_piu = conducentiFeritiDa65_piu;
        this.conducentiMortiDa0_14 = conducentiMortiDa0_14;
        this.conducentiMortiDa15_19 = conducentiMortiDa15_19;
        this.conducentiMortiDa20_64 = conducentiMortiDa20_64;
        this.conducentiMortiDa65_piu = conducentiMortiDa65_piu;
        this.passeggeriFeriti = passeggeriFeriti;
        this.passeggeriMorti = passeggeriMorti;
        this.pedoniFeriti = pedoniFeriti;
        this.pedoniMorti = pedoniMorti;
        this.incidentiStradeUrbane = incidentiStradeUrbane;
        this.feritiStradeUrbane = feritiStradeUrbane;
        this.mortiStradeUrbane = mortiStradeUrbane;
        this.incidentiStradeExtra = incidentiStradeExtra;
        this.feritiStradeExtra = feritiStradeExtra;
        this.mortiStradeExtra = mortiStradeExtra;
        this.incidentiAutostrada = incidentiAutostrada;
        this.feritiAutostrada = feritiAutostrada;
        this.mortiAutostrada = mortiAutostrada;
        this.incidentiMarcia = incidentiMarcia;
        this.incidentiVeicoloPedone = incidentiVeicoloPedone;
        this.incidentiIsolati = incidentiIsolati;
        this.incidentiWeekend = incidentiWeekend;
        this.incidentiFeriali = incidentiFeriali;
        this.incidentiNotte = incidentiNotte;
        this.incidentiGiorno = incidentiGiorno;
        this.incidentiDa7_9 = incidentiDa7_9;
        this.incidentiDa17_19 = incidentiDa17_19;
        this.incidentiSereno = incidentiSereno;
        this.incidentiNebbia = incidentiNebbia;
        this.incidentiPioggiaNeve = incidentiPioggiaNeve;
        this.autovetture = autovetture;
        this.autocarri = autocarri;
        this.motocicli = motocicli;
        this.velocipedi = velocipedi;
    }

    public static Record create(String[] campi) {
        return new Record(campi[0], campi[1], campi[2], campi[3], campi[4],
                campi[5], campi[6], campi[7], campi[8], campi[9],
                campi[10], campi[11], campi[12], campi[13], campi[14],
                campi[15], campi[16], campi[17], campi[18], campi[19],
                campi[20], campi[21], campi[22], campi[23], campi[24],
                campi[25], campi[26], campi[27], campi[28], campi[29],
                campi[30], campi[31], campi[32], campi[33], campi[34],
                campi[35], campi[36], campi[37], campi[38], campi[39],
                campi[40], campi[41], campi[42], campi[43], campi[44],
                campi[45], campi[46], campi[47], campi[48], campi[49],
                campi[50], campi[51], campi[52], campi[53], campi[54]);
    }

    public String getAnnoIncidente() {
        return annoIncidente;
    }

    public String getComune() {
        return comune;
    }

    public String[] setNewCampi() {

        String[] newCampi = new String[3];
        Integer tot_incidenti_nonmortali = Integer.parseInt(incidentiTotali) - Integer.parseInt(incidentiMortali);
        Integer conduc_maschi = Integer.parseInt(conducentiTotali) - Integer.parseInt(conducentiDonne);
        Integer veic_coinvolti_altro = Integer.parseInt(veicoliConConducente) - Integer.parseInt(autocarri) - Integer.parseInt(autovetture) - Integer.parseInt(velocipedi);
        newCampi[0] = tot_incidenti_nonmortali.toString();
        newCampi[1] = conduc_maschi.toString();
        newCampi[2] = veic_coinvolti_altro.toString();
        return newCampi;
    }

    public void verificaRecord() {
        boolean corretto;

        corretto = verificaAnnoIncidente(annoIncidente);
        if (!corretto) {
            erroriRecord.add("Anno incidente errato");
        }

        corretto = verificaProvincia(prov);
        if (!corretto) {
            erroriRecord.add("Provincia errata");
        }

        corretto = verificaComune(comune, prov);
        if (!corretto) {
            erroriRecord.add("Comune errato");
        }

        corretto = verificaIncidentiTotali(incidentiTotali);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti totali errato");
        }

        corretto = verificaIncidentiMortali(incidentiMortali);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti mortali errato");
        }

        corretto = verificaComuneFeritiTotali(feritiTotali);
        if (!corretto) {
            erroriRecord.add("Numero di feriti totali errato");
        }

        corretto = verificaMortiTotali(mortiTotali);
        if (!corretto) {
            erroriRecord.add("Numero di morti totali errato");
        }

        corretto = verificaVeicoliConConducente(veicoliConConducente);
        if (!corretto) {
            erroriRecord.add("Numero di veicoli con conducente errato");
        }

        corretto = verificaVeicoliSenzaConducente(veicoliSenzaConducente);
        if (!corretto) {
            erroriRecord.add("Numero di veicoli senza conducente errato");
        }

        corretto = verificaConducentiTotali(conducentiTotali);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti totali errato");
        }

        corretto = verificaConducentiDonne(conducentiDonne);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti donne errato");
        }

        corretto = verificaConducentiFeriti(conducentiFeriti);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti feriti errato");
        }

        corretto = verificaConducentiMortiInGiornata(conducentiMortiInGiornata);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti morti entro 24h errato");
        }

        corretto = verificaConducentiIgnoti(conducentiIgnoti);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti ignoti errato");
        }

        corretto = verificaConducentiDa0_14(conducentiDa0_14);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti da 0 a 14 anni errato");
        }

        corretto = verificaConducentiDa15_19(conducentiDa15_19);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti dai 15 ai 19 anni errato");
        }

        corretto = verificaConducentiDa20_64(conducentiDa20_64);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti dai 20 ai 64 anni errato");
        }

        corretto = verificaConducentiDa65_piu(conducentiDa65_piu);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti dai 65 anni in piu' errato");
        }

        corretto = verificaConducentiFeritiDa0_14(conducentiFeritiDa0_14);
        if (!corretto) {
            erroriRecord.add("Numero di feriti da 0 a 14 anni errato");
        }

        corretto = verificaConducentiFeritiDa15_19(conducentiFeritiDa15_19);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti feriti dai 15 ai 19 anni errato");
        }

        corretto = verificaConducentiFeritiDa20_64(conducentiFeritiDa20_64);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti feriti dai 20 ai 64 anni errato");
        }

        corretto = verificaConducentiFeritiDa65_piu(conducentiFeritiDa65_piu);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti feriti dai 65 anni in piu' errato");
        }

        corretto = verificaConducentiMortiDa0_14(conducentiMortiDa0_14);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti morti da 0 a 14 anni errato");
        }

        corretto = verificaConducentiMortiDa15_19(conducentiMortiDa15_19);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti morti dai 15 ai 19 anni errato");
        }

        corretto = verificaConducentiMortiDa20_64(conducentiMortiDa20_64);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti morti dai 20 ai 64 anni errato");
        }

        corretto = verificaConducentiMortiDa65_piu(conducentiDa65_piu);
        if (!corretto) {
            erroriRecord.add("Numero di conducenti morti dai 65 anni in piu' errato");
        }

        corretto = verificaPasseggeriFeriti(passeggeriFeriti);
        if (!corretto) {
            erroriRecord.add("Numero di passeggeri feriti errato");
        }

        corretto = verificaPasseggeriMorti(passeggeriMorti);
        if (!corretto) {
            erroriRecord.add("Numero di passeggeri morti errato");
        }

        corretto = verificaPedoniFeriti(pedoniFeriti);
        if (!corretto) {
            erroriRecord.add("Numero di pedoni feriti errato");
        }

        corretto = verificaPedoniMorti(pedoniMorti);
        if (!corretto) {
            erroriRecord.add("Numero di pedoni morti errato");
        }

        corretto = verificaIncidentiStradeUrbane(incidentiStradeUrbane);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti nelle urbane errato");
        }

        corretto = verificaFeritiStradeUrbane(feritiStradeUrbane);
        if (!corretto) {
            erroriRecord.add("Numero di feriti nelle urbane errato");
        }

        corretto = verificaMortiStradeUrbane(mortiStradeUrbane);
        if (!corretto) {
            erroriRecord.add("Numero di morti nelle urbane errato");
        }

        corretto = verificaIncidentiStradeExtra(incidentiStradeExtra);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti nelle extraurbane errato");
        }

        corretto = verificaFeritiStradeExtra(feritiStradeExtra);
        if (!corretto) {
            erroriRecord.add("Numero di feriti nelle extraurbane errato");
        }

        corretto = verificaMortiStradeExtra(mortiStradeExtra);
        if (!corretto) {
            erroriRecord.add("Numero di morti nelle extraurbane errato");
        }

        corretto = verificaIncidentiAutostrada(incidentiAutostrada);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti in autostrada errato");
        }

        corretto = verificaFeritiAutostrada(feritiAutostrada);
        if (!corretto) {
            erroriRecord.add("Numero di feriti in autostrada errato");
        }

        corretto = verificaMortiAutostrada(mortiAutostrada);
        if (!corretto) {
            erroriRecord.add("Numero di morti in autostrada errato");
        }

        corretto = verificaIncidentiMarcia(incidentiMarcia);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti in marcia errato");
        }

        corretto = verificaIncidentiVeicoloPedone(incidentiVeicoloPedone);
        if (!corretto) {
            erroriRecord.add("Numero di di incidenti veicolo-pedone errato");
        }

        corretto = verificaIncidentiIsolati(incidentiIsolati);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti isolati errato");
        }

        corretto = verificaIncidentiWeekend(incidentiWeekend);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti nei weekend errato");
        }

        corretto = verificaIncidentiFeriali(incidentiFeriali);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti nei feriali errato");
        }

        corretto = verificaIncidentiNotte(incidentiNotte);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti di notte errato");
        }

        corretto = verificaIncidentiGiorno(incidentiGiorno);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti di giorno errato");
        }

        corretto = verificaIncidentiDa7_9(incidentiDa7_9);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti dalle 7 alle 9 errato");
        }

        corretto = verificaIncidentiDa17_19(incidentiDa17_19);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti dalle 17 alle 19 errato");
        }

        corretto = verificaIncidentiSereno(incidentiSereno);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti con sereno errato");
        }

        corretto = verificaIncidentiNebbia(incidentiNebbia);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti con nebbia errato");
        }

        corretto = verificaIncidentiPioggia_Neve(incidentiPioggiaNeve);
        if (!corretto) {
            erroriRecord.add("Numero di incidenti con pioggia e neve errato");
        }

        corretto = verificaAutovetture(autovetture);
        if (!corretto) {
            erroriRecord.add("Numero di autovetture errato");
        }

        corretto = verificaAutocarri(autocarri);
        if (!corretto) {
            erroriRecord.add("Numero di autocarri errato");
        }

        corretto = verificaMotocicli(motocicli);
        if (!corretto) {
            erroriRecord.add("Numero di motocicli errato");
        }

        corretto = verificaVelocipedi(velocipedi);
        if (!corretto) {
            erroriRecord.add("Numero di velocipiedi errato");
        }

        corretto = verificaIncidentiPerLuogo(incidentiTotali, incidentiStradeUrbane, incidentiStradeExtra, incidentiAutostrada);
        if (!corretto) {
            warningsRecord.add("Numero di incidenti inconsistente per luogo");
        }

        corretto = verificaIncidentiPerTipologia(incidentiTotali, incidentiMarcia, incidentiVeicoloPedone, incidentiIsolati);
        if (!corretto) {
            warningsRecord.add("Numero di incidenti inconsistente per tipologia");
        }

        corretto = verificaIncidentiPerGiorni(incidentiTotali, incidentiFeriali, incidentiWeekend);
        if (!corretto) {
            warningsRecord.add("Numero di incidenti inconsistente per giorni");
        }

        corretto = verificaIncidentiPerOrario(incidentiTotali, incidentiGiorno, incidentiNotte);
        if (!corretto) {
            warningsRecord.add("Numero di incidenti inconsistente per orario");
        }

        corretto = verificaIncidentiPerOreDiPunta(incidentiTotali, incidentiDa7_9, incidentiDa17_19);
        if (!corretto) {
            warningsRecord.add("Numero di incidenti inconsistente per orari di punta");
        }

        corretto = verificaIncidentiPerMeteo(incidentiTotali, incidentiSereno, incidentiNebbia, incidentiPioggiaNeve);
        if (!corretto) {
            warningsRecord.add("Numero di incidenti inconsistente per meteo");
        }

        corretto = verificaFeriti(feritiTotali, conducentiFeriti, passeggeriFeriti, pedoniFeriti);
        if (!corretto) {
            warningsRecord.add("Numero di feriti inconsistente");
        }

        corretto = verificaMorti(mortiTotali, conducentiMortiDa0_14, conducentiMortiDa15_19, conducentiMortiDa20_64, conducentiMortiDa65_piu, passeggeriMorti, pedoniMorti);
        if (!corretto) {
            warningsRecord.add("Numero di feriti inconsistente");
        }

        corretto = verificaVeicoliConConducente(veicoliConConducente, autovetture, autocarri, motocicli, velocipedi);
        if (!corretto) {
            warningsRecord.add("Numero di veicolo con conducente inconsistente");
        }

        corretto = verificaConducentiTotaliPerAnni(incidentiTotali, conducentiDa0_14, conducentiDa15_19, conducentiDa20_64, conducentiDa65_piu);
        if (!corretto) {
            warningsRecord.add("Numero di incidenti totali inconsistente");
        }

        corretto = verificaConducentiFeritiPerAnni(incidentiTotali, conducentiFeritiDa0_14, conducentiFeritiDa15_19, conducentiFeritiDa20_64, conducentiFeritiDa65_piu);
        if (!corretto) {
            warningsRecord.add("Numero di incidenti inconsistente");
        }
    }

    public boolean isCorrect() {
        return erroriRecord.isEmpty();
    }

    public void addError(String error) {
        erroriRecord.add(error);
    }

    @Override
    public String toString() {
        String stringa="Record{"+annoIncidente+","+comune+", ("+prov+")}<br>riga("+idRecord+")";
        if (erroriRecord.size()>0){
            stringa+=",\t errori=" + erroriRecord;
        }
        if(warningsRecord.size()>0){
            stringa+=",\t warnings=" + warningsRecord;
        }
        return  stringa;
    }

    static boolean verificaAnnoIncidente(String annoIncidente) {
        return annoIncidente.compareTo(ANNO_INIZIALE) >= 0 && annoIncidente.compareTo(ANNO_FINALE) <= 0;
    }

    private static boolean verificaProvincia(String prov) {
        Set<String> province = new HashSet<>();
        province.add("BERGAMO");
        province.add("BRESCIA");
        province.add("COMO");
        province.add("CREMONA");
        province.add("LECCO");
        province.add("LODI");
        province.add("MANTOVA");
        province.add("MILANO");
        province.add("MONZA E DELLA BRIANZA");
        province.add("PAVIA");
        province.add("SONDRIO");
        province.add("VARESE");

        return province.contains(prov);
    }

    static boolean verificaComune(String comune, String prov) {
        return Controlli.getComuni().containsKey(prov) && Controlli.getComuni().get(prov).contains(comune);
    }

    static boolean verificaNumero(String s) {
        int num;
        try {
            num = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return num >= 0;
    }

    private static boolean verificaVeicoliSenzaConducente(String veicoliSenzaConducente) {
        return verificaNumero(veicoliSenzaConducente);
    }

    private static boolean verificaIncidentiTotali(String incidentiTotali) {
        return verificaNumero(incidentiTotali);
    }

    private static boolean verificaVeicoliConConducente(String veicoliConConducente) {
        return verificaNumero(veicoliConConducente);
    }

    private static boolean verificaConducentiTotali(String conducentiTotali) {
        return verificaNumero(conducentiTotali);
    }

    private static boolean verificaConducentiFeriti(String conducentiFeriti) {
        return verificaNumero(conducentiFeriti);
    }

    private static boolean verificaConducentiDonne(String conducentiDonne) {
        return verificaNumero(conducentiDonne);
    }

    private static boolean verificaIncidentiMortali(String incidentiMortali) {
        return verificaNumero(incidentiMortali);
    }

    private static boolean verificaIncidentiStradeExtra(String incidentiStradeExtra) {
        return verificaNumero(incidentiStradeExtra);
    }

    private static boolean verificaMortiStradeUrbane(String mortiStradeUrbane) {
        return verificaNumero(mortiStradeUrbane);
    }

    private static boolean verificaFeritiStradeUrbane(String feritiStradeUrbane) {
        return verificaNumero(feritiStradeUrbane);
    }

    private static boolean verificaIncidentiStradeUrbane(String incidentiStradeUrbane) {
        return verificaNumero(incidentiStradeUrbane);
    }

    private static boolean verificaPedoniMorti(String pedoniMorti) {
        return verificaNumero(pedoniMorti);
    }

    private static boolean verificaPedoniFeriti(String pedoniFeriti) {
        return verificaNumero(pedoniFeriti);
    }

    private static boolean verificaPasseggeriMorti(String passeggeriMorti) {
        return verificaNumero(passeggeriMorti);
    }

    private static boolean verificaPasseggeriFeriti(String passeggeriFeriti) {
        return verificaNumero(passeggeriFeriti);
    }

    private static boolean verificaConducentiMortiDa65_piu(String conducentiDa65_piu) {
        return verificaNumero(conducentiDa65_piu);
    }

    private static boolean verificaConducentiMortiDa20_64(String conducentiMortiDa20_64) {
        return verificaNumero(conducentiMortiDa20_64);
    }

    private static boolean verificaConducentiMortiDa15_19(String conducentiMortiDa15_19) {
        return verificaNumero(conducentiMortiDa15_19);
    }

    private static boolean verificaConducentiMortiDa0_14(String conducentiMortiDa0_14) {
        return verificaNumero(conducentiMortiDa0_14);
    }

    private static boolean verificaConducentiFeritiDa65_piu(String conducentiFeritiDa65_piu) {
        return verificaNumero(conducentiFeritiDa65_piu);
    }

    private static boolean verificaConducentiFeritiDa20_64(String conducentiFeritiDa20_64) {
        return verificaNumero(conducentiFeritiDa20_64);
    }

    private static boolean verificaConducentiMortiInGiornata(String conducentiMortiInGiornata) {
        return verificaNumero(conducentiMortiInGiornata);
    }

    private static boolean verificaConducentiIgnoti(String conducentiIgnoti) {
        return verificaNumero(conducentiIgnoti);
    }

    private static boolean verificaConducentiDa0_14(String conducentiDa0_14) {
        return verificaNumero(conducentiDa0_14);
    }

    private static boolean verificaConducentiDa15_19(String conducentiDa15_19) {
        return verificaNumero(conducentiDa15_19);
    }

    private static boolean verificaConducentiDa20_64(String conducentiDa20_64) {
        return verificaNumero(conducentiDa20_64);
    }

    private static boolean verificaComuneFeritiTotali(String feritiTotali) {
        return verificaNumero(feritiTotali);
    }

    private static boolean verificaMortiTotali(String mortiTotali) {
        return verificaNumero(mortiTotali);
    }

    private static boolean verificaConducentiDa65_piu(String conducentiDa65_piu) {
        return verificaNumero(conducentiDa65_piu);
    }

    private static boolean verificaConducentiFeritiDa0_14(String conducentiFeritiDa0_14) {
        return verificaNumero(conducentiFeritiDa0_14);
    }

    private static boolean verificaConducentiFeritiDa15_19(String conducentiFeritiDa15_19) {
        return verificaNumero(conducentiFeritiDa15_19);
    }

    private static boolean verificaFeritiStradeExtra(String feritiStradeExtra) {
        return verificaNumero(feritiStradeExtra);
    }

    private static boolean verificaIncidentiDa7_9(String incidentiDa7_9) {
        return verificaNumero(incidentiDa7_9);
    }

    private static boolean verificaFeritiAutostrada(String feritiAutostrada) {
        return verificaNumero(feritiAutostrada);
    }

    private static boolean verificaMortiAutostrada(String mortiAutostrada) {
        return verificaNumero(mortiAutostrada);
    }

    private static boolean verificaIncidentiWeekend(String incidentiWeekend) {
        return verificaNumero(incidentiWeekend);
    }

    private static boolean verificaIncidentiIsolati(String incidentiIsolati) {
        return verificaNumero(incidentiIsolati);
    }

    private static boolean verificaIncidentiDa17_19(String incidentiDa17_19) {
        return verificaNumero(incidentiDa17_19);
    }

    private static boolean verificaIncidentiPioggia_Neve(String incidentiPioggiaNeve) {
        return verificaNumero(incidentiPioggiaNeve);
    }

    private static boolean verificaAutocarri(String autocarri) {
        return verificaNumero(autocarri);
    }

    private static boolean verificaAutovetture(String autovetture) {
        return verificaNumero(autovetture);
    }

    private static boolean verificaIncidentiMarcia(String incidentiMarcia) {
        return verificaNumero(incidentiMarcia);
    }

    private static boolean verificaIncidentiVeicoloPedone(String incidentiVeicoloPedone) {
        return verificaNumero(incidentiVeicoloPedone);
    }

    private static boolean verificaMortiStradeExtra(String mortiStradeExtra) {
        return verificaNumero(mortiStradeExtra);
    }

    private static boolean verificaIncidentiFeriali(String incidentiFeriali) {
        return verificaNumero(incidentiFeriali);
    }

    private static boolean verificaMotocicli(String motocicli) {
        return verificaNumero(motocicli);
    }

    private static boolean verificaIncidentiSereno(String incidentiSereno) {
        return verificaNumero(incidentiSereno);
    }

    private static boolean verificaIncidentiAutostrada(String incidentiAutostrada) {
        return verificaNumero(incidentiAutostrada);
    }

    private static boolean verificaIncidentiNotte(String incidentiNotte) {
        return verificaNumero(incidentiNotte);
    }

    private static boolean verificaIncidentiGiorno(String incidentiGiorno) {
        return verificaNumero(incidentiGiorno);
    }

    private static boolean verificaIncidentiNebbia(String incidentiNebbia) {
        return verificaNumero(incidentiNebbia);
    }

    private static boolean verificaVelocipedi(String velocipedi) {
        return verificaNumero(velocipedi);
    }

    public static boolean verificaIncidentiPerTipologia(String incidentiTotali, String incidentiMarcia, String incidentiVeicoloPedone, String incidentiIsolati) {
        try {
            return Integer.parseInt(incidentiTotali) >= Integer.parseInt(incidentiMarcia)
                + Integer.parseInt(incidentiVeicoloPedone)
                + Integer.parseInt(incidentiIsolati);
        }catch (NumberFormatException e) {
            return false;
        }
        
    }

    static boolean verificaIncidentiPerLuogo(String incidentiTotali, String incidentiStradeUrbane, String incidentiStradeExtra, String incidentiAutostrada) {
        try {
            return Integer.parseInt(incidentiTotali) >= Integer.parseInt(incidentiStradeUrbane)
                + Integer.parseInt(incidentiStradeExtra)
                + Integer.parseInt(incidentiAutostrada);
        }catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean verificaIncidentiPerGiorni(String incidentiTotali, String incidentiFeriali, String incidentiWeekend) {
        try {
            return Integer.parseInt(incidentiTotali) == Integer.parseInt(incidentiFeriali)
                + Integer.parseInt(incidentiWeekend);
        } catch (NumberFormatException e) {
            return false;
        }
       
    }

    private static boolean verificaIncidentiPerOrario(String incidentiTotali, String incidentiGiorno, String incidentiNotte) {
        try {
            return Integer.parseInt(incidentiTotali) >= Integer.parseInt(incidentiGiorno)
                + Integer.parseInt(incidentiNotte);
        } catch (NumberFormatException e) {
            return false;
        }
        
    }

    private static boolean verificaIncidentiPerOreDiPunta(String incidentiTotali, String incidentiDa7_9, String incidentiDa17_19) {
        try {
            return Integer.parseInt(incidentiTotali) >= Integer.parseInt(incidentiDa7_9)
                + Integer.parseInt(incidentiDa17_19);
        } catch (NumberFormatException e) {
            return false;
        }
        
    }

    private static boolean verificaIncidentiPerMeteo(String incidentiTotali, String incidentiSereno, String incidentiNebbia, String incidentiPioggiaNeve) {
        try {
            return Integer.parseInt(incidentiTotali) >= Integer.parseInt(incidentiSereno)
                + Integer.parseInt(incidentiNebbia)
                + Integer.parseInt(incidentiPioggiaNeve);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean verificaFeriti(String feritiTotali, String conducentiFeriti, String passeggeriFeriti, String pedoniFeriti) {
        try {
            return Integer.parseInt(feritiTotali) >= Integer.parseInt(conducentiFeriti)
                + Integer.parseInt(passeggeriFeriti)
                + Integer.parseInt(pedoniFeriti);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean verificaMorti(String mortiTotali, String conducentiMortiDa0_14, String conducentiMortiDa15_19, String conducentiMortiDa20_64, String conducentiMortiDa65_piu, String passeggeriMorti, String pedoniMorti) {
        try {
            return Integer.parseInt(mortiTotali) >= Integer.parseInt(conducentiMortiDa0_14)
                + Integer.parseInt(conducentiMortiDa15_19)
                + Integer.parseInt(conducentiMortiDa20_64)
                + Integer.parseInt(conducentiMortiDa65_piu)
                + Integer.parseInt(passeggeriMorti)
                + Integer.parseInt(pedoniMorti);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean verificaVeicoliConConducente(String veicoliConConducente, String autovetture, String autocarri, String motocicli, String velocipedi) {
        try {
            return Integer.parseInt(veicoliConConducente) >= Integer.parseInt(autovetture)
                + Integer.parseInt(autocarri)
                + Integer.parseInt(motocicli)
                + Integer.parseInt(velocipedi);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean verificaConducentiTotaliPerAnni(String incidentiTotali, String conducentiDa0_14, String conducentiDa15_19, String conducentiDa20_64, String conducentiDa65_piu) {
        try {
            return Integer.parseInt(incidentiTotali) >= Integer.parseInt(conducentiDa0_14)
                + Integer.parseInt(conducentiDa15_19)
                + Integer.parseInt(conducentiDa20_64)
                + Integer.parseInt(conducentiDa65_piu);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean verificaConducentiFeritiPerAnni(String incidentiTotali, String conducentiFeritiDa0_14, String conducentiFeritiDa15_19, String conducentiFeritiDa20_64, String conducentiFeritiDa65_piu) {
        try {
            return Integer.parseInt(incidentiTotali) >= Integer.parseInt(conducentiFeritiDa0_14)
                + Integer.parseInt(conducentiFeritiDa15_19)
                + Integer.parseInt(conducentiFeritiDa20_64)
                + Integer.parseInt(conducentiFeritiDa65_piu);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
