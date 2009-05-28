/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.salonesregistro.persistencia;

import java.util.ArrayList;
import java.util.List;
import org.jdom.Element;
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
public class DaoSalonXmlTest {

    public DaoSalonXmlTest() {
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
//     * Test of agregarSalon method, of class DaoSalonXml.
//     */
    @Test
    public void testAgregarSalon() {
        System.out.println("agregarSalon");
        Salon salon = new Salon(6, 100, true, false, true);
        DaoSalonXml instance = new DaoSalonXml();
        boolean expResult = true;
        boolean result = instance.agregarSalon(salon);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of buscar method, of class DaoSalonXml.
//     */
//    @Test
//    public void testBuscar() {
//        System.out.println("buscar");
//        List raiz = null;
//        String dato = "";
//        Element expResult = null;
//        Element result = DaoSalonXml.buscar(raiz, dato);
//        assertEquals(expResult, result);
//         TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarSalon method, of class DaoSalonXml.
//     */
//    @Test
//    public void testBuscarSalon() {
//        System.out.println("buscarSalon");
//        int idSalon = 2;
//        DaoSalonXml instance = new DaoSalonXml();
//        Salon result = instance.buscarSalon(idSalon);
//        assertNotNull(result);
//        System.out.println(result);
//    }
//
    /**
     * Test of editarSalon method, of class DaoSalonXml.
     */
//    @Test
//    public void testEditarSalon() {
//        System.out.println("editarSalon");
//        Salon Salon = new Salon(2, 100, true, false, true);
//        DaoSalonXml instance = new DaoSalonXml();
//        boolean expResult = true;
//        boolean result = instance.editarSalon(Salon);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of borrarSalon method, of class DaoSalonXml.
     */
//    @Test
//    public void testBorrarSalon() {
//        System.out.println("borrarSalon");
//        int idSalon = 1;
//        DaoSalonXml instance = new DaoSalonXml();
//        boolean expResult = true;
//        boolean result = instance.borrarSalon(idSalon);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of todosLosSalones method, of class DaoSalonXml.
     */
//    @Test
//    public void testTodosLosSalones() {
//        System.out.println("todosLosSalones");
//        DaoSalonXml instance = new DaoSalonXml();
//        ArrayList<Salon> result = instance.todosLosSalones();
//        assertNotNull(result);
//        for (Salon salon : result) {
//            System.out.println(salon);
//        }
//        // TODO review the generated test code and remove the default call to fail.
//    }

}