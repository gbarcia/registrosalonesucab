package ve.edu.ucab.salonesregistro.persistencia;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import ve.edu.ucab.salonesregistro.dominio.Facultad;
import ve.edu.ucab.salonesregistro.dominio.PersonaAutorizada;
import ve.edu.ucab.salonesregistro.dominio.Reserva;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 * Clase fachada para soporte de operaciones de persistencia
 * @author gerardobarcia
 * @version 1.0
 */
public class FachadaBD implements DaoFacultadContrato, DaoPersonaContrato,
        DaoReservaContrato, DaoSalonContrato {

    /** contrato para dar soporte a operaciones sobre facultad*/
    private DaoFacultadContrato operacionesFacultad;
    /** contrato para dar soporte a operaciones sobre personas*/
    private DaoPersonaContrato operacionesPersona;
    /** contrato para dar soporte a operaciones sobre reservas*/
    private DaoReservaContrato operacionesReserva;
    /** contrato para dar soporte a operaciones sobre salones*/
    private DaoSalonContrato operacionesSalon;
    /** variable de instancia para dar soporte al patron Singleton*/
    private static FachadaBD INSTANCIA;
    /** variable para el manejo de la bitacora log4j */
    private Logger bitacora = Logger.getLogger(getClass());

    /**
     * constructor por defecto casteado para propositos Singleton
     */
    private FachadaBD() {
    }

    /**
     * firma para crear una instancia de esta clase (patron singleton)
     */
    private synchronized static void crearInstancia() {
        if (INSTANCIA == null) {
            try {
                INSTANCIA = new FachadaBD();
                INSTANCIA.operacionesFacultad = new DaoFacultadXml();
                INSTANCIA.operacionesPersona = new DaoPersonaXml();
                INSTANCIA.operacionesReserva = new DaoReservaXml();
                INSTANCIA.operacionesSalon = new DaoSalonXml();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * firma para obtener la instancia de esta clase
     * @return instancia de la clase
     */
    public static FachadaBD obtenerInstancia() {
        if (INSTANCIA == null) {
            crearInstancia();
        }
        return INSTANCIA;
    }

    /**
     * firma para obtener todas las facultades y escuelas del sistema
     * @return ArrayList de objetos Facultad
     */
    public ArrayList<Facultad> traerTodasLasFacultades() {
        return this.operacionesFacultad.traerTodasLasFacultades();
    }

    /**
     * firma para obtener la lista de objetos persona que se encuentran en el archivo
     * @return ArrayList de objeros PersonaAutorizada
     */
    public ArrayList<PersonaAutorizada> obtenerPersonas() {
        return this.operacionesPersona.obtenerPersonas();
    }

    /**
     * firma para realizar una reserva en el sistema
     * @param reserva objeto reserva con la informacion
     * @return valor booleano de exito de la operacion
     */
    public boolean realizarReserva(Reserva reserva) {
        return this.operacionesReserva.realizarReserva(reserva);
    }

    /**
     * firma para agregar un nuevo salon
     * @param salon objeto salon a agregar
     * @return booleano de exito de la operacion
     */
    public boolean agregarSalon(Salon salon) {
        return this.operacionesSalon.agregarSalon(salon);
    }

    /**
     * firma para buscar un salon determinado
     * @param idSalon el identificador del salon
     * @return objto Salon con sus datos
     */
    public Salon buscarSalon(int idSalon) {
        return this.operacionesSalon.buscarSalon(idSalon);
    }

    /**
     * firma para editar un salon
     * @param Salon objeto salon a editar
     * @return valor booleano de exito de la operacion
     */
    public boolean editarSalon(Salon Salon) {
        return this.operacionesSalon.editarSalon(Salon);
    }

    /**
     * firma para borrar un salon del sistema
     * @param idSalon identificador del salon
     * @return valor booleano de exito de la operacion
     */
    public boolean borrarSalon(int idSalon) {
        return this.operacionesSalon.borrarSalon(idSalon);
    }

    /**
     * firma para obtener todos los salones del sistema
     * @return ArrayList de Objetos salon con su informacion
     */
    public ArrayList<Salon> todosLosSalones() {
        return this.operacionesSalon.todosLosSalones();
    }

    /**
     * firma para cambiar el documento y reempleazarlo por otro para fine de
     * operaciones de intercambio entre servidores
     * @param lista lista de salones nueva
     * @return valor booleano con la condicion de exito
     */
    public boolean cambiarDocumento(ArrayList<Salon> lista) {
        return this.operacionesSalon.cambiarDocumento(lista);
    }
}
