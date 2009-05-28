/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.salonesregistro.logica;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gerardobarcia
 */
public class ManejadorAutorizacionTest {

    public ManejadorAutorizacionTest() {
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
     * Test of existePersona method, of class ManejadorAutorizacion.
     */
    @Test
    public void testExistePersona() {
        System.out.println("existePersona");
        Integer id = 2;
        ManejadorAutorizacion instance = new ManejadorAutorizacion();
        Integer result = instance.existePersona(id);
        assertNotNull(result);
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
    }

}