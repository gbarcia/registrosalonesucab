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
import ve.edu.ucab.salonesregistro.dominio.PersonaAutorizada;

/**
 *
 * @author gerardobarcia
 */
public class DaoPersonaXmlTest {

    public DaoPersonaXmlTest() {
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
     * Test of obtenerPersonas method, of class DaoPersonaXml.
     */
    @Test
    public void testObtenerPersonas() {
        System.out.println("obtenerPersonas");
        DaoPersonaXml instance = null;
        try {
            instance = new DaoPersonaXml();
        } catch (IOException ex) {
            Logger.getLogger(DaoPersonaXmlTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<PersonaAutorizada> result = instance.obtenerPersonas();
        assertNotNull(result);
        for (PersonaAutorizada personaAutorizada : result) {
            System.out.println(personaAutorizada);
        }
    // TODO review the generated test code and remove the default call to fail.
    }
}