package ve.edu.ucab.salonesregistro.logica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.Reserva;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 * Clase concreta para dar soporte a la fachada de las operaciones de la
 * aplcacion
 * @author gerardobarcia
 * @version  1.0
 */
public class RegistroGeneral extends UnicastRemoteObject
        implements RegistroGeneralContrato {

    /** varibale de referencia para el manejo de la autorizacion*/
    private ManejadorAutorizacion manejadorAutorizacion = new ManejadorAutorizacion();
    /** varibale de referencia para el manejo de las reservas*/
    private ManejadorReserva manejadorReserva = new ManejadorReserva();
    /** varibale de referencia para el manejo de los salones*/
    private ManejadorSalon manejadorSalon = new ManejadorSalon();

    /**
     * constructor por defecto
     * @throws java.rmi.RemoteException
     */
    public RegistroGeneral() throws RemoteException {
        super();
    }

    /**
     * firma para verificar si existe una persona para darle acceso al sistema. En
     * caso de la que persona exista se envia su identificador, si no existe se
     * regresa un valor nulo
     * @param id identificador de la persona a verificar
     * @return null si no existe, en caso contrario su identificador
     */
    public Integer existePersona(Integer id) {
        return manejadorAutorizacion.existePersona(id);
    }

    /**
     * firma para realizar una reserva en el sistema
     * @param reserva objeto reserva con la informacion
     * @return valor booleano de exito de la operacion
     */
    public boolean realizarReserva(Reserva reserva) {
        return manejadorReserva.realizarReserva(reserva);
    }

    /**
     * firma para agregar un nuevo salon
     * @param salon objeto salon a agregar
     * @return booleano de exito de la operacion
     */
    public boolean agregarSalon(Salon salon) {
        return manejadorSalon.agregarSalon(salon);
    }

    /**
     * firma para buscar un salon determinado
     * @param idSalon el identificador del salon
     * @return objto Salon con sus datos
     */
    public Salon buscarSalon(int idSalon) {
        return manejadorSalon.buscarSalon(idSalon);
    }

    /**
     * firma para editar un salon
     * @param Salon objeto salon a editar
     * @return valor booleano de exito de la operacion
     */
    public boolean editarSalon(Salon Salon) {
        return manejadorSalon.editarSalon(Salon);
    }

    /**
     * firma para borrar un salon del sistema
     * @param idSalon identificador del salon
     * @return valor booleano de exito de la operacion
     */
    public boolean borrarSalon(int idSalon) {
        return manejadorSalon.borrarSalon(idSalon);
    }

    /**
     * firma para obtener todos los salones del sistema
     * @return ArrayList de Objetos salon con su informacion
     */
    public ArrayList<Salon> todosLosSalones() {
        return manejadorSalon.todosLosSalones();
    }
}
