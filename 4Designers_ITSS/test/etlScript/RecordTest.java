/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

import static etlScript.Constants.PATH_COMUNI_FILE;
import static etlScript.Record.verificaAnnoIncidente;
import java.io.FileNotFoundException;
import java.util.Calendar;
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
public class RecordTest{
    
    public RecordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws FileNotFoundException {
        Controlli.fillMunicipalities(PATH_COMUNI_FILE, Controlli.comuni);
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
     * Test of create method, of class Record.
     */
    @Test
    public void testCreate() {
    }




    /**
     * Test of verificaRecord method, of class Record.
     */
    @Test
    public void testVerificaRecord() {
    }


    
    /**
     * Test of verificaAnnoIncidente method, of class Record.
     */
    @Test
    public void testVerificaAnnoIncidente() {
        System.out.println("* test verificaAnnoIncidente()");
        assertTrue(verificaAnnoIncidente("2002"));
        assertFalse(verificaAnnoIncidente("1998"));
        assertFalse(verificaAnnoIncidente(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+1));
    }
    
    /**
     * Test of verificaNumero method, of class Record.
     */
    @Test
    public void testverificaNumero() {
        System.out.println("* test verificaverificaNumero()");
        assertTrue(Record.verificaNumero("3"));
        assertFalse(Record.verificaNumero("-2"));
    }
    
    /**
     * Test of verificaComune method, of class Record.
     */
    @Test
    public void testVerificaComune() {
        System.out.println("* test verificaComune()");
        assertTrue(Record.verificaComune("MILANO", "MILANO"));
        assertTrue(Record.verificaComune("BORGO PRIOLO", "PAVIA"));
        assertFalse(Record.verificaComune("BRESCIA", "BRECIA"));
        assertFalse(Record.verificaComune("BORGO", "PAVIA"));
        assertFalse(Record.verificaComune("LECO", "LECCO"));
        assertFalse(Record.verificaComune("MILANO", "NAPOLI"));
        assertFalse(Record.verificaComune("NAPOLI", "MILANO"));
        assertFalse(Record.verificaComune("ALTAMURA", "BARI"));
        
    }
    
}
