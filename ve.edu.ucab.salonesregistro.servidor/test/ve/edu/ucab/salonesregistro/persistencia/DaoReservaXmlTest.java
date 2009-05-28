/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.salonesregistro.persistencia;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ve.edu.ucab.salonesregistro.dominio.PersonaAutorizada;
import static org.junit.Assert.*;
import ve.edu.ucab.salonesregistro.dominio.Reserva;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 *
 * @author gerardobarcia
 */
public class DaoReservaXmlTest {

    public DaoReservaXmlTest() {
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
     * Test of realizarReserva method, of class DaoReservaXml.
     */
    @Test
    public void testRealizarReserva() {
        System.out.println("realizarReserva");
        PersonaAutorizada persona = new PersonaAutorizada(1, "Gerardo Barcia", 'E');
        Salon salon = new Salon(1, 50, true, false, false);
        Date a = new Date(10/05/2009);
        Reserva reserva = new Reserva(3, a, true, persona, salon);
        DaoReservaXml instance = new DaoReservaXml();
        boolean expResult = true;
        boolean result = instance.realizarReserva(reserva);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}