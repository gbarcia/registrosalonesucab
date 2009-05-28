package ve.edu.ucab.salonesregistro.sincronizacion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 * Contraro para sincronizacion entre servidores
 * @author gerardobarcia
 */
public interface Isincronizacion extends Remote {

    /**
     * firma para obtener la version del archivo de salones
     * @return Integer la version del archivo de salon
     * @throws java.rmi.RemoteException
     */
    public Integer versionSalones() throws RemoteException;

    /**
     * firma para obtener todos los datos del archivo de los salones
     * @return la lista de salones actual del servidor
     * @throws java.rmi.RemoteException
     */
    public ArrayList<Salon> obtenerSalones() throws RemoteException;

    /**
     * firma para sincronizar los datos del archivo salon
     * @param lista la lista de salones a sincronizar
     * @param version numero de version
     * @return valor booleano con la condicion de exito
     * @throws java.rmi.RemoteException
     */
    public boolean sincronizarSalones(ArrayList<Salon> lista, Integer version) throws RemoteException;

    /**
     * firma para comparar si las versiones son iguales
     * @param version la version recibida
     * @return valor booleano con la condicion de igual(true) o no
     * @throws java.rmi.RemoteException
     */
    public boolean compararVersiones(Integer version) throws RemoteException;
}
