/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.salonesregistro.persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ve.edu.ucab.salonesregistro.dominio.Facultad;

/**
 *
 * @author gerardobarcia
 */
public class DaoFacultadXmlTest {

    public DaoFacultadXmlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of traerTodasLasFacultades method, of class DaoFacultadXml.
     */
    @Test
    public void testTraerTodasLasFacultades() {
        System.out.println("traerTodasLasFacultades");
        DaoFacultadXml instance = null;
        try {
            instance = new DaoFacultadXml();
        } catch (IOException ex) {
            Logger.getLogger(DaoFacultadXmlTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Facultad> result = instance.traerTodasLasFacultades();
        assertNotNull(result);
        for (Facultad facultad : result) {
            System.out.println(facultad);
        }
    // TODO review the generated test code and remove the default call to fail.
    }
}