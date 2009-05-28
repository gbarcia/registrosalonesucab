package ve.edu.ucab.salonesregistro.logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.Reserva;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 * Interfaz remota para proporcionar los servicios de la aplicacion
 * @author gerardobarcia
 * @version 1.0
 */
public interface RegistroGeneralContrato extends Remote {

    /**
     * firma para verificar si existe una persona para darle acceso al sistema. En
     * caso de la que persona exista se envia su identificador, si no existe se
     * regresa un valor nulo
     * @param id identificador de la persona a verificar
     * @return null si no existe, en caso contrario su identificador
     */
    public Integer existePersona(Integer id) throws RemoteException;

    /**
     * firma para realizar una reserva en el sistema
     * @param reserva objeto reserva con la informacion
     * @return valor booleano de exito de la operacion
     */
    public boolean realizarReserva(Reserva reserva) throws RemoteException;

    /**
     * firma para agregar un nuevo salon
     * @param salon objeto salon a agregar
     * @return booleano de exito de la operacion
     */
    public boolean agregarSalon(Salon salon) throws RemoteException;

    /**
     * firma para buscar un salon determinado
     * @param idSalon el identificador del salon
     * @return objto Salon con sus datos
     */
    public Salon buscarSalon(int idSalon) throws RemoteException;

    /**
     * firma para editar un salon
     * @param Salon objeto salon a editar
     * @return valor booleano de exito de la operacion
     */
    public boolean editarSalon(Salon Salon) throws RemoteException;

    /**
     * firma para borrar un salon del sistema
     * @param idSalon identificador del salon
     * @return valor booleano de exito de la operacion
     */
    public boolean borrarSalon(int idSalon) throws RemoteException;

    /**
     * firma para obtener todos los salones del sistema
     * @return ArrayList de Objetos salon con su informacion
     */
    public ArrayList<Salon> todosLosSalones() throws RemoteException;
}
