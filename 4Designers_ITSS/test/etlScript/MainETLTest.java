/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

import eccezioni.WrongHeaderException;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pan
 */
public class MainETLTest {

    private static final String LOCAL_PATH = "test/etlScript/";
    private static final String LOCAL_PATH_SORGENTI = "test/etlScript/csv_test_generali/";
    private static final String FILE_RISULTATO_TEST = "test/etlScript/risultatoTest.csv";
    private static final String FILE_TEMP_TEST = "test/etlScript/tempTest.csv";

    public MainETLTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        System.out.println("* test tearDown()");
        File fileRisultato = new File(FILE_RISULTATO_TEST);
        fileRisultato.delete();
        File fileTemp = new File(FILE_TEMP_TEST);
        fileTemp.delete();
        File fileLog = new File(LOCAL_PATH + "log.html");
        fileLog.delete();
    }

    /**
     * Test of proceduraETL method, of class MainETL.
     */
    @Test
    public void testProceduraETLSuNumeroRecord() throws Exception {
        System.out.println("* test proceduraETLSuNumeroRecord()");
        MainETL.proceduraETL(FILE_RISULTATO_TEST, FILE_TEMP_TEST,
                LOCAL_PATH_SORGENTI + "1_sorgente_corretto_A11_D0_E0.csv",
                LOCAL_PATH + "log.html", false);
        assertTrue(MainETL.accettati == 11 && MainETL.duplicati == 0 && MainETL.errati == 0);
        //In sequenza senza pulizia file perchè i risultati della successiva dipendono
        //dal caricamento di quella precedente
        MainETL.proceduraETL(FILE_RISULTATO_TEST, FILE_TEMP_TEST,
                LOCAL_PATH_SORGENTI + "2_sorgente_corretto_A4_D7_E0.csv",
                LOCAL_PATH + "log.html", false);
        assertTrue(MainETL.accettati == 4 && MainETL.duplicati == 7 && MainETL.errati == 0);

        (new File(FILE_RISULTATO_TEST)).delete();
        (new File(FILE_TEMP_TEST)).delete();
        Controlli.chiavi.clear();

        MainETL.proceduraETL(FILE_RISULTATO_TEST, FILE_TEMP_TEST,
                LOCAL_PATH_SORGENTI + "3_sorgente_corretto_A4_D0_E7.csv",
                LOCAL_PATH + "log.html", false);
        assertTrue(MainETL.accettati == 4 && MainETL.duplicati == 0 && MainETL.errati == 7);
        //In sequenza senza pulizia file perchè i risultati della successiva dipendono
        //dal caricamento di quella precedente
        MainETL.proceduraETL(FILE_RISULTATO_TEST, FILE_TEMP_TEST,
                LOCAL_PATH_SORGENTI + "4_sorgente_corretto_A4_D4_E4.csv",
                LOCAL_PATH + "log.html", true);
        assertTrue(MainETL.accettati == 4 && MainETL.duplicati == 3 && MainETL.errati == 5);
        
        (new File(FILE_RISULTATO_TEST)).delete();
        (new File(FILE_TEMP_TEST)).delete();
        Controlli.chiavi.clear();

    }

    @Test
    public void testProceduraETLSuIntestazioneErrata() throws Exception {
        System.out.println("* test proceduraETLuIntestazioneErrata()");
        try {
            MainETL.proceduraETL(FILE_RISULTATO_TEST, FILE_TEMP_TEST,
                    LOCAL_PATH + "csv_test_Intestazione/intestazione_corta.csv",
                    LOCAL_PATH + "log.html", false);
            throw new Exception();
        } catch (WrongHeaderException e) {
            assertEquals(e.toString(),
                    "Esecuzione interrotta, intestazione non conforme al protocollo");
        }

        try {
            MainETL.proceduraETL(FILE_RISULTATO_TEST, FILE_TEMP_TEST,
                    LOCAL_PATH + "csv_test_Intestazione/intestazione_lunga.csv",
                    LOCAL_PATH + "log.html", false);
            throw new Exception();
        } catch (WrongHeaderException e) {
            assertEquals(e.toString(),
                    "Esecuzione interrotta, intestazione non conforme al protocollo");
        }

        try {
            MainETL.proceduraETL(FILE_RISULTATO_TEST, FILE_TEMP_TEST,
                    LOCAL_PATH + "csv_test_Intestazione/intestazione_campo_2_errato.csv",
                    LOCAL_PATH + "log.html", false);
            throw new Exception();
        } catch (WrongHeaderException e) {
            assertEquals(e.toString(),
                    "Esecuzione interrotta, il campo 2 dell'intestazione non è conforme al protocollo");
        }

        try {
            MainETL.proceduraETL(FILE_RISULTATO_TEST, FILE_TEMP_TEST,
                    LOCAL_PATH + "csv_test_Intestazione/intestazione_campo_6-9_errato.csv",
                    LOCAL_PATH + "log.html", false);
            throw new Exception();
        } catch (WrongHeaderException e) {
            assertEquals(e.toString(),
                    "Esecuzione interrotta, il campo 6 dell'intestazione non è conforme al protocollo");
        }

    }

}
