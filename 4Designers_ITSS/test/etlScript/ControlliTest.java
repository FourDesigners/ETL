/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

import eccezioni.WrongHeaderException;
import static etlScript.Constants.SEPARATOR;
import static etlScript.Controlli.fillKeys;
import static etlScript.Controlli.fillMunicipalities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Pan
 */
public class ControlliTest {

    private static final String LOCAL_PATH = "test/etlScript/";

    public ControlliTest() {
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
    }

    /**
     * Test of verificaIntestazione method, of class Controlli.
     */
    @Test
    public void testVerificaIntestazioneCorretta() throws Exception {
        System.out.println("* test VerificaIntestazione() con intestazione corretta");
        Scanner stramCorretta = new Scanner(new File(LOCAL_PATH + "csv_test_Intestazione/intestazione_corretta.csv"));
        String rigaSourceFile = stramCorretta.nextLine();
        Controlli.verificaIntestazione(rigaSourceFile.split(SEPARATOR));
    }

    @Test
    public void testVerificaIntestazioneErrata() throws Exception {
        System.out.println("* test VerificaIntestazione() con intestazione errata");
        //Test intestazione corta
        try (Scanner stream = new Scanner(new File(LOCAL_PATH + "csv_test_Intestazione/intestazione_corta.csv"));) {
            String rigaSourceFile = stream.nextLine();
            Controlli.verificaIntestazione(rigaSourceFile.split(SEPARATOR));
        } catch (WrongHeaderException e) {
            assertEquals(e.toString(), "Esecuzione interrotta, intestazione non conforme al protocollo");
        }
        //Test intestazione lunga
        try (Scanner stream = new Scanner(new File(LOCAL_PATH + "csv_test_Intestazione/intestazione_lunga.csv"));) {
            String rigaSourceFile = stream.nextLine();
            Controlli.verificaIntestazione(rigaSourceFile.split(SEPARATOR));
        } catch (WrongHeaderException e) {
            assertEquals(e.toString(), "Esecuzione interrotta, intestazione non conforme al protocollo");
        }
        //Test un campo sbaglaito
        try (Scanner stream = new Scanner(new File(LOCAL_PATH + "csv_test_Intestazione/intestazione_campo_2_errato.csv"));) {
            String rigaSourceFile = stream.nextLine();
            Controlli.verificaIntestazione(rigaSourceFile.split(SEPARATOR));
        } catch (WrongHeaderException e) {
            assertEquals(e.toString(), "Esecuzione interrotta, il campo 2 dell'intestazione non è conforme al protocollo");
        }
        //Test un 2 campi sbaglaiti
        try (Scanner stream = new Scanner(new File(LOCAL_PATH + "csv_test_Intestazione/intestazione_campo_6-9_errato.csv"));) {
            String rigaSourceFile = stream.nextLine();
            Controlli.verificaIntestazione(rigaSourceFile.split(SEPARATOR));
        } catch (WrongHeaderException e) {
            assertEquals(e.toString(), "Esecuzione interrotta, il campo 6 dell'intestazione non è conforme al protocollo");
        }
    }

    /**
     * Test of fillKeys method, of class Controlli. Testa che venga lanciata
     * l'eccezione file not found
     */
    @Test(expected = FileNotFoundException.class)
    public void testFillKeys() throws Exception {
        System.out.println("* test fillKeys() con file inesistente");
        fillKeys("Files/fineInesistente");
    }
 
     /** Test of fillMunicipalities
     * method, of class Controlli.
     */
    @Test(expected = FileNotFoundException.class)
    public void testFillMunicipalitiesEccezione() throws Exception {
        System.out.println("* test fillMunicipalities() con file inesistente");
        fillMunicipalities("Files/fineInesistente", new HashMap<>());
    }
    @Test
    public void testFillMunicipalities() throws Exception {
        System.out.println("* test fillMunicipalities() con fileTest dei comuni");
        Map<String, Set<String>> comuniTest = new HashMap<>();
        comuniTest.put("BRESCIA", new HashSet<>());
        comuniTest.get("BRESCIA").add("CASTENEDOLO");
        comuniTest.get("BRESCIA").add("CEDEGOLO");
        comuniTest.get("BRESCIA").add("ZONE");
        comuniTest.put("COMO", new HashSet<>());
        comuniTest.get("COMO").add("ALZATE BRIANZA");
        comuniTest.put("SONDRIO", new HashSet<>());
        comuniTest.get("SONDRIO").add("BIANZONE");
        comuniTest.put("CREMONA", new HashSet<>());
        comuniTest.get("CREMONA").add("CASTELVISCONTI");        
        Map<String, Set<String>> comuni = new HashMap<>();

        fillMunicipalities(LOCAL_PATH + "comuniTest.csv", comuni);
        assertEquals(comuni, comuniTest);
    }


    /**
     * Test of findMissingRecords method, of class Controlli.
     */
    @Test
    public void testFindMissingRecords() {
    }

}
