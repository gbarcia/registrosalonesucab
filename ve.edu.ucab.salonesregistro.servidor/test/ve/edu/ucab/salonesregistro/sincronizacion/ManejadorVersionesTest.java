/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.salonesregistro.sincronizacion;

import java.util.List;
import org.jdom.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ve.edu.ucab.salonesregistro.sincronizacion.dominio.VersionDocumento;

/**
 *
 * @author gerardobarcia
 */
public class ManejadorVersionesTest {

    public ManejadorVersionesTest() {
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
     * Test of editarVersion method, of class ManejadorVersiones.
     */
    @Test
    public void testEditarVersion() {
        System.out.println("editarVersion");
        VersionDocumento version = new VersionDocumento("Reserva", 1);
        version.aumentarNumeroVersion();
        ManejadorVersiones instance = new ManejadorVersiones();
        boolean expResult = true;
        boolean result = instance.editarVersion(version);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerNumeroVersion method, of class ManejadorVersiones.
     */
//    @Test
//    public void testObtenerNumeroVersion() {
//        System.out.println("obtenerNumeroVersion");
//        String nombreArchivo = "Salon";
//        ManejadorVersiones instance = new ManejadorVersiones();
//        Integer result = instance.obtenerNumeroVersion(nombreArchivo);
//        assertNotNull(result);
//        System.out.println(result);
//        // TODO review the generated test code and remove the default call to fail.
//    }

}