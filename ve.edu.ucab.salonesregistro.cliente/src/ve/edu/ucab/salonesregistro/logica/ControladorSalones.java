package ve.edu.ucab.salonesregistro.logica;

import java.rmi.RemoteException;
import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 * Clase para gestionar el control de las operaciones remotas sobre salones
 * @author gerardobarcia
 */
public class ControladorSalones {

    /** variable de referencia remota del registro de operaciones*/
    private RegistroGeneralContrato registro;

    /**
     * Constructor que recibe la interfaz remota para trabajar
     * @param reg interfaz remota del registro de operaciones
     */
    public ControladorSalones(RegistroGeneralContrato reg) {
        this.registro = reg;
    }

    /**
     * firma para agregar un nuevo salon en el sistema
     * @param salon objeto salon a agregar
     * @return valor booleano con la condicion de exito
     */
    public boolean nuevoSalon(Salon salon) {
        boolean resultado = false;
        try {
            resultado = this.registro.agregarSalon(salon);
        } catch (RemoteException ex) {
        } finally {
            return resultado;
        }
    }

    /**
     * firma para actualizar un salon determinado
     * @param salon objeto salon a actualizar
     * @return valor booleano con la condicion de exito
     */
    public boolean actualizarSalon(Salon salon) {
        boolean resultado = false;
        try {
            resultado = this.registro.editarSalon(salon);
        } catch (RemoteException ex) {
        } finally {
            return resultado;
        }
    }

    /**
     * firma para eliminar un salon del sistema
     * @param idSalon indentificador del salon a eliminar
     * @return valor booleano con la condicion de resultado
     */
    public boolean eliminarSalon(Integer idSalon) {
        boolean resultado = false;
        try {
            resultado = this.registro.borrarSalon(idSalon);
        } catch (RemoteException ex) {
        } finally {
            return resultado;
        }
    }

    /**
     * firma para buscar un salon en el sistema
     * @param idSalon indentificador del salon a buscar
     * @return null si no existe y el objeto salon buscado en dado caso que exista
     */
    public Salon buscarUnSalon(Integer idSalon) {
        Salon resultado = null;
        try {
            resultado = this.registro.buscarSalon(idSalon);
        } catch (RemoteException ex) {
        } finally {
            return resultado;
        }
    }

    /**
     * firma para obtener todos los salones registrados en el sistema
     * @return lista de objetos salon
     */
    public ArrayList<Salon> todosLosSalones() {
        ArrayList<Salon> resultado = null;
        try {
            resultado = this.registro.todosLosSalones();
        } catch (RemoteException ex) {
        } finally {
            return resultado;
        }
    }
}
