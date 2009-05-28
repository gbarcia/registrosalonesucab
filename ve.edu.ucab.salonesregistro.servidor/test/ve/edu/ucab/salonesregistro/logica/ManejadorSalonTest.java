/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.salonesregistro.logica;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 *
 * @author gerardobarcia
 */
public class ManejadorSalonTest {

    public ManejadorSalonTest() {
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

//    /**
//     * Test of agregarSalon method, of class ManejadorSalon.
//     */
//    @Test
//    public void testAgregarSalon() {
//        System.out.println("agregarSalon");
//        Salon salon = null;
//        ManejadorSalon instance = new ManejadorSalon();
//        boolean expResult = false;
//        boolean result = instance.agregarSalon(salon);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarSalon method, of class ManejadorSalon.
//     */
//    @Test
//    public void testBuscarSalon() {
//        System.out.println("buscarSalon");
//        int idSalon = 0;
//        ManejadorSalon instance = new ManejadorSalon();
//        Salon expResult = null;
//        Salon result = instance.buscarSalon(idSalon);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editarSalon method, of class ManejadorSalon.
//     */
//    @Test
//    public void testEditarSalon() {
//        System.out.println("editarSalon");
//        Salon Salon = null;
//        ManejadorSalon instance = new ManejadorSalon();
//        boolean expResult = false;
//        boolean result = instance.editarSalon(Salon);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of borrarSalon method, of class ManejadorSalon.
     */
    @Test
    public void testBorrarSalon() {
        System.out.println("borrarSalon");
        int idSalon = 1;
        ManejadorSalon instance = new ManejadorSalon();
        boolean expResult = true;
        boolean result = instance.borrarSalon(idSalon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of todosLosSalones method, of class ManejadorSalon.
     */
    @Test
    public void testTodosLosSalones() {
        System.out.println("todosLosSalones");
        ManejadorSalon instance = new ManejadorSalon();
        ArrayList<Salon> result = instance.todosLosSalones();
        assertNotNull(result);
        for (Salon salon : result) {
            System.out.println(salon);
        }
        // TODO review the generated test code and remove the default call to fail.
    }

}