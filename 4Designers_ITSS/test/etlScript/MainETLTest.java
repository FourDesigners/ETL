/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

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
        File fileLog = new File(LOCAL_PATH+"log.html");
        fileLog.delete();
    }

    /**
     * Test of proceduraETL method, of class MainETL.
     */
    @Test
    public void testProceduraETL() throws Exception {
        System.out.println("* test proceduraETL()");
        MainETL.proceduraETL(FILE_RISULTATO_TEST, FILE_TEMP_TEST, LOCAL_PATH_SORGENTI+"sorgente_corretto.csv",LOCAL_PATH+"log.html", false);
        assertTrue(MainETL.accettati==11 && MainETL.duplicati==0 && MainETL.errati == 0);
    }


}
