package ve.edu.ucab.salonesregistro.presentacion;

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
    public void comprobarValidez(String nombre, String clave, VentanaInicio ven) {
        boolean resultado = false;
        Integer identificador = Integer.parseInt(clave);
        this.operaciones = new FachadaLogica();
        resultado = this.operaciones.comprobarLogin(identificador);
        if (resultado) {
            System.out.println("Acceso autorizado.");
            ven.setVisible(false);
            new VentanaOperaciones(identificador).setVisible(true);
        } else {
            if (operaciones.isInterfazEnlazada()) {
                JOptionPane.showMessageDialog(null, "Acceso denegado.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * funcion para consultar los salones del sistema
     * @return arreglo de salones
     */
    public ArrayList<Salon> cosultarSalones() {
        this.operaciones = new FachadaLogica();
        return this.operaciones.obtenerTodosLosSalones();
    }

    /**
     * metodo para consultar un salon
     * @param idSalon identificadore del salon
     * @return salon consultado
     */
    public Salon consultarSalon(int idSalon) {
        this.operaciones = new FachadaLogica();
        return this.operaciones.buscarSalon(idSalon);
    }

    /**
     * metodo para modificar un salon
     * @param idSalon identificador del salon
     * @param capacidad capacidad del salon
     * @param vb indica si el salon posee video beam
     * @param aa indica si el salon posee aire acondicionado
     * @param co indica si el salon posee computador
     * @return resultado de la operacion
     */
    public boolean modificarSalon(int idSalon, int capacidad, boolean vb,
            boolean aa, boolean co) {
        Salon salon = new Salon(idSalon, capacidad, aa, vb, co);
        this.operaciones = new FachadaLogica();
        return this.operaciones.actualizarRegistroSalon(salon);
    }

    /**
     * metodo para eliminar un salon
     * @param idSalon identificador del salon
     * @return resutado de la operacion
     */
    public boolean eliminarSalon(int idSalon) {
        this.operaciones = new FachadaLogica();
        return this.operaciones.eliminarSalon(idSalon);
    }

    /**
     * metodo para agreagar un salon
     * @param idSalon identificador del salon
     * @param capacidad capacidad del salon
     * @param vb indica si el salon posee video beam
     * @param aa indica si el salon posee aire acondicionado
     * @param co indica si el salon posee computador
     * @return resultado de la operacion
     */
    public boolean agregarSalon(int idSalon, int capacidad, boolean vb,
            boolean aa, boolean co) {
        Salon salon = new Salon(idSalon, capacidad, aa, vb, co);
        this.operaciones = new FachadaLogica();
        return this.operaciones.registroNuevoSalon(salon);
    }

    /**
     * metodo para agregar una reserva
     * @param idReserva identificador de la reserva
     * @param idSalon identificador del salon
     * @param idPersona identificador de la persona autorizada
     * @param fecha fecha dela reserva
     * @return resultado de la operacion
     */
    public boolean agregarReserva(int idReserva, int idSalon, int idPersona, Date fecha) {
        PersonaAutorizada persona = new PersonaAutorizada();
        persona.setId(idPersona);
        Salon salon = new Salon();
        salon.setId(idSalon);
        Reserva reserva = new Reserva(idReserva, fecha, true, persona, salon);
        this.operaciones = new FachadaLogica();
        return this.operaciones.realizarUnaReserva(reserva);
    }

    /**
     * metodo para validar string entero
     * @param in string a validar
     * @return si el string es un valor entero
     */
    public boolean validarInt(String in) {
        try {
            Integer.parseInt(in);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
