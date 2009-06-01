package ve.edu.ucab.salonesregistro.presentacion;

import com.sun.org.apache.xpath.internal.compiler.OpCodes;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import ve.edu.ucab.salonesregistro.dominio.PersonaAutorizada;
import ve.edu.ucab.salonesregistro.dominio.Reserva;
import ve.edu.ucab.salonesregistro.dominio.Salon;
import ve.edu.ucab.salonesregistro.logica.FachadaLogica;

/**
 * Clase para el control de la presentacion
 * @author gerardobarcia
 */
public class ControlPresentacion {

    /** variable de referencia a la fachada de las operaciones remotas*/
    private FachadaLogica operaciones;
    /** variable de referencia a la ventana del login*/
    private VentanaInicio ventanaLogin;
    /** variable de referencia a la ventana de operaciones*/
    private VentanaOperaciones ventanaOperaciones;

    /**
     * constructor por defecto
     */
    public ControlPresentacion() {
    }

    /**
     * firma para iniciar el sistema
     */
    public void InicioSistema() {
        this.ventanaLogin = new VentanaInicio();
        this.ventanaLogin.setVisible(true);
    }

    /**
     * firma para comprobar la validez de un usuario que quiere ingresar en el
     * sistema
     * @param nombre nombre del usuario
     * @param clave clave del usuario
     */
    public void comprobarValidez(String nombre, String clave) {
        boolean resultado = false;
        Integer identificador = Integer.parseInt(clave);
        this.operaciones = new FachadaLogica();
        resultado = this.operaciones.comprobarLogin(identificador);
        if (resultado) {
            System.out.println("acceso autorizado"); // aca iria ir al menu principal
        } else {
            if (operaciones.isInterfazEnlazada()) {
                JOptionPane.showMessageDialog(null, "acceso no autorizado", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * metodo para realizar la consulta de salones
     * @return arreglo de salones
     */
    public ArrayList<Salon> cosultarSalones() {
        this.operaciones = new FachadaLogica();
        return this.operaciones.obtenerTodosLosSalones();
    }

    public Salon consultarSalon(int idSalon) {
        this.operaciones = new FachadaLogica();
        return this.operaciones.buscarSalon(idSalon);
    }

    public boolean modificarSalon(int idSalon, int capacidad, boolean vb,
            boolean aa, boolean co) {
        Salon salon = new Salon(idSalon, capacidad, aa, vb, co);
        this.operaciones = new FachadaLogica();
        return this.operaciones.actualizarRegistroSalon(salon);
    }

    public boolean eliminarSalon(int idSalon) {
        this.operaciones = new FachadaLogica();
        return this.operaciones.eliminarSalon(idSalon);
    }

    public boolean agregarSalon(int idSalon, int capacidad, boolean vb,
            boolean aa, boolean co) {
        Salon salon = new Salon(idSalon, capacidad, aa, vb, co);
        this.operaciones = new FachadaLogica();
        return this.operaciones.registroNuevoSalon(salon);
    }

    public boolean agregarReserva(int idReserva, int idSalon, int idPersona, Date fecha) {
        PersonaAutorizada persona = new PersonaAutorizada();
        persona.setId(idPersona);
        Salon salon = new Salon();
        salon.setId(idSalon);
        Reserva reserva = new Reserva(idReserva, fecha, true, persona, salon);
        this.operaciones = new FachadaLogica();
        return this.operaciones.realizarUnaReserva(reserva);
    }
}
