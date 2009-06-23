package ve.edu.ucab.salonesregistro.sincronizacion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.Salon;
import ve.edu.ucab.salonesregistro.logica.ManejadorSalon;

/**
 * Clase para sincronizacion entre servidores
 * @author gerardobarcia
 */
public class Sincronizacion extends UnicastRemoteObject
        implements Isincronizacion {

    /** referencia al manejador de versiones*/
    private ManejadorVersiones manejador = new ManejadorVersiones();
    /** referencia al manejador de salones*/
    private ManejadorSalon operadorSalon = new ManejadorSalon();
    /** nombre de referencia del archivo de salones en la version*/
    private final String nombreArchivoSalones = "Salon";

    /** constructor por defecto*/
    public Sincronizacion() throws RemoteException {
        super();
    }

    /**
     * firma para obtener la version del archivo de salones
     * @return Integer la version del archivo de salon
     * @throws java.rmi.RemoteException
     */
    public Integer versionSalones() throws RemoteException {
        Integer miVersion = manejador.obtenerNumeroVersion(nombreArchivoSalones);
        return miVersion;
    }

    /**
     * firma para obtener todos los datos del archivo de los salones
     * @return la lista de salones actual del servidor
     * @throws java.rmi.RemoteException
     */
    public ArrayList<Salon> obtenerSalones() throws RemoteException {
        return operadorSalon.todosLosSalones();
    }

    /**
     * firma para sincronizar los datos del archivo salon
     * @param lista la lista de salones a sincronizar
     * @param version numero de version
     * @return valor booleano con la condicion de exito
     * @throws java.rmi.RemoteException
     */
    public boolean sincronizarSalones(ArrayList<Salon> lista, Integer version) throws RemoteException {
        return operadorSalon.cambiarDocumento(lista, version);
    }

    /**
     * firma para comparar si las versiones son iguales
     * @param version la version recibida
     * @return valor booleano con la condicion de igual(true) o no
     * @throws java.rmi.RemoteException
     */
    public boolean compararVersiones(Integer version) throws RemoteException {
        boolean resultado = false;
        Integer miVersion = manejador.obtenerNumeroVersion(nombreArchivoSalones);
        if (miVersion == version) {
            resultado = true;
        }
        return resultado;
    }
}
