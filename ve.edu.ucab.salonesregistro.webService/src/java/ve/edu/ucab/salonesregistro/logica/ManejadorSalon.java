package ve.edu.ucab.salonesregistro.logica;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import ve.edu.ucab.salonesregistro.conf.ConfiguracionesRMI;
import ve.edu.ucab.salonesregistro.dominio.Salon;
import ve.edu.ucab.salonesregistro.persistencia.DaoSalonContrato;
import ve.edu.ucab.salonesregistro.persistencia.DaoSalonXml;
import ve.edu.ucab.salonesregistro.persistencia.FachadaBD;
import ve.edu.ucab.salonesregistro.sincronizacion.Isincronizacion;
import ve.edu.ucab.salonesregistro.sincronizacion.ManejadorVersiones;
import ve.edu.ucab.salonesregistro.sincronizacion.dominio.VersionDocumento;

/**
 * Clase controladora para el manejo de operaciones con salon
 * @author gerardobarcia
 * @version 1.0
 */
public class ManejadorSalon implements Serializable {

    /** variable de referencia a las operaciones del contrato de salones*/
    private DaoSalonContrato operacion;
    /** referencia a la version del documento*/
    private VersionDocumento version;
    /** referencia al manejador de versiones del documento*/
    private ManejadorVersiones manejadorVersiones = new ManejadorVersiones();
    /** nombre de referencia del archivo de version*/
    private final String nombreVersion = "Salon";
    /** variable para el manejo de la bitacora log4j */
    private Logger bitacora = Logger.getLogger(getClass());
    /** variable de referencia remota para sincronizar con la replica */
    private Isincronizacion sync;
    /** variable de referencia a el archivo de configuracion de rmi */
    private ConfiguracionesRMI crmi = ConfiguracionesRMI.obtenerInstancia();
    /** variable booleana para indicar si se enlazo con el servidor replica*/
    private boolean replicaActivada = false;

    /**
     * firma para actualizar la version del documento en caso de modificaciones
     * la version se incrementa en uno
     * @return valor booleano con la condicion de exito
     */
    private boolean actualizarVersionDocumento() {
        boolean resultado = false;
        Integer numeroVerAct = manejadorVersiones.obtenerNumeroVersion(nombreVersion);
        version = new VersionDocumento(nombreVersion, numeroVerAct);
        version.aumentarNumeroVersion();
        resultado = manejadorVersiones.editarVersion(version);
        return resultado;
    }

    /**
     * constructor que inica el objeto salon y contacta con la réplica para
     * fines de sincronizacion
     */
    public ManejadorSalon() {
        this.operacion = new DaoSalonXml();
    }

    /**
     * firma para agregar un nuevo salon
     * @param salon objeto salon a agregar
     * @return booleano de exito de la operacion
     */
    public boolean agregarSalon(Salon salon) {
        boolean resultadoFinal = false;
        try {
            boolean resultado = this.operacion.agregarSalon(salon);
            if (resultado) {
                resultadoFinal = actualizarVersionDocumento();
                if (replicaActivada) {
                    ArrayList<Salon> salonesAct = todosLosSalones();
                    Integer numeroVersion = this.manejadorVersiones.obtenerNumeroVersion(nombreVersion);
                    sync.sincronizarSalones(salonesAct, numeroVersion);
                }
            }
        } catch (RemoteException ex) {
            bitacora.info("No se pudo enlazar con la replica " + ex.getMessage());
        } finally {
            return resultadoFinal;
        }
    }

    /**
     * firma para buscar un salon determinado
     * @param idSalon el identificador del salon
     * @return objto Salon con sus datos
     */
    public Salon buscarSalon(int idSalon) {
        return this.operacion.buscarSalon(idSalon);
    }

    /**
     * firma para editar un salon
     * @param Salon objeto salon a editar
     * @return valor booleano de exito de la operacion
     */
    public boolean editarSalon(Salon Salon) {
        boolean resultadoFinal = false;
        try {
            boolean resultado = this.operacion.editarSalon(Salon);
            if (resultado) {
                resultadoFinal = actualizarVersionDocumento();
                if (replicaActivada) {
                    ArrayList<Salon> salonesAct = todosLosSalones();
                    Integer numeroVersion = this.manejadorVersiones.obtenerNumeroVersion(nombreVersion);
                    sync.sincronizarSalones(salonesAct, numeroVersion);
                }
            }
        } catch (RemoteException ex) {
            bitacora.info("No se pudo enlazar con la replica " + ex.getMessage());
        } finally {
            return resultadoFinal;
        }
    }

    /**
     * firma para borrar un salon del sistema
     * @param idSalon identificador del salon
     * @return valor booleano de exito de la operacion
     */
    public boolean borrarSalon(int idSalon) {
        boolean resultadoFinal = false;
        try {
            boolean resultado = this.operacion.borrarSalon(idSalon);
            if (resultado) {
                resultadoFinal = actualizarVersionDocumento();
                if (replicaActivada) {
                    ArrayList<Salon> salonesAct = todosLosSalones();
                    Integer numeroVersion = this.manejadorVersiones.obtenerNumeroVersion(nombreVersion);
                    sync.sincronizarSalones(salonesAct, numeroVersion);
                }
            }
        } catch (RemoteException ex) {
            bitacora.info("No se pudo enlazar con la replica " + ex.getMessage());
        } finally {
            return resultadoFinal;
        }
    }

    /**
     * firma para obtener todos los salones del sistema
     * @return ArrayList de Objetos salon con su informacion
     */
    public ArrayList<Salon> todosLosSalones() {
        ArrayList<Salon> resultado = null;
        resultado = this.operacion.todosLosSalones();
        return resultado;
    }

    /**
     * firma para cambiar el documento y reempleazarlo por otro para fine de
     * operaciones de intercambio entre servidores
     * @param lista lista de salones nueva
     * @param version la version del documento actual
     * @return valor booleano con la condicion de exito
     */
    public boolean cambiarDocumento(ArrayList<Salon> lista, Integer version) {
        boolean resultado = false;
        boolean r = this.operacion.cambiarDocumento(lista);
        if (r) {
            VersionDocumento vd = new VersionDocumento(nombreVersion, version);
            resultado = manejadorVersiones.editarVersion(vd);
        }
        return resultado;
    }
}
