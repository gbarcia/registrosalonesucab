/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.salonesregistro.persistencia;

import java.util.ArrayList;
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
public class FachadaBDTest {

    public FachadaBDTest() {
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
     * Test of obtenerTodasLasFacultades method, of class FachadaBD.
     */
    @Test
    public void testObtenerTodasLasFacultades() {
        System.out.println("obtenerTodasLasFacultades");
        DaoFacultadContrato instance = FachadaBD.obtenerInstancia();
        ArrayList<Facultad> result = instance.traerTodasLasFacultades();
        assertNotNull(result);
        for (Facultad facultad : result) {
            System.out.println(facultad);
        }
        // TODO review the generated test code and remove the default call to fail.
    }
}