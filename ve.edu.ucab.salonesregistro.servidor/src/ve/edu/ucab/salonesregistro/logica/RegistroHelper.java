package ve.edu.ucab.salonesregistro.logica;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ve.edu.ucab.salonesregistro.conf.ConfiguracionesRMI;
import ve.edu.ucab.salonesregistro.dominio.Salon;
import ve.edu.ucab.salonesregistro.sincronizacion.Isincronizacion;
import ve.edu.ucab.salonesregistro.sincronizacion.ManejadorVersiones;
import ve.edu.ucab.salonesregistro.sincronizacion.Sincronizacion;

/**
 * Clase ayudadora para iniciar el modudlo de enlace de objetos
 * @author gerardobarcia
 * @version 1.0
 */
public class RegistroHelper {

    private static ConfiguracionesRMI configuracionRMI = ConfiguracionesRMI.obtenerInstancia();

    /**
     * firma para poner en marcja la clase ayudadora
     * @param args argumentos opcionales
     */
    public static void main(String[] args) {
        try {
            RegistroHelper.revisarVersionesSync();
            String rutaCodeBase = RegistroHelper.configuracionRMI.getRutaCodeBase();
            String rutaPoliticas = RegistroHelper.configuracionRMI.getRutaPoliticas();
            System.setProperty("java.rmi.server.codebase", rutaCodeBase);
            System.setProperty("java.security.policy", rutaPoliticas);
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }
            RegistroGeneralContrato registro = new RegistroGeneral();
            Isincronizacion sync = new Sincronizacion();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("Registro", registro);
            Naming.rebind("Sincronizacion", sync);
            InetAddress direccion = InetAddress.getLocalHost();
            System.out.println("Servidor Iniciado. IP: " + direccion);
        } catch (UnknownHostException ex) {
            Logger.getLogger(RegistroHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * firma para realizar la sincronizacion entre servidores
     * Se realiza una comparacion de las versiones por medio de IR JRMI y se sincro
     * nizan los archivos
     */
    private static void revisarVersionesSync() {
        try {
            RegistroGeneral rg = new RegistroGeneral();
            ConfiguracionesRMI crmi = ConfiguracionesRMI.obtenerInstancia();
            String poltica = crmi.getRutaPoliticas();
            String ipReplica = crmi.getIPReplica();
            System.setProperty("java.security.policy", poltica);
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }
            Isincronizacion sync = (Isincronizacion) Naming.lookup("rmi://" + ipReplica + ":1099/Sincronizacion");
            ManejadorVersiones miVersion = new ManejadorVersiones();
            Integer miNumeroVersion = miVersion.obtenerNumeroVersion("Salon");
            if (!sync.compararVersiones(miNumeroVersion)) {
                Integer numeroVerReplica = sync.versionSalones();
                System.out.println(numeroVerReplica);
                if (miNumeroVersion > numeroVerReplica) {
                    ArrayList<Salon> lista = rg.todosLosSalones();
                    sync.sincronizarSalones(lista, miNumeroVersion);
                } else if (miNumeroVersion < numeroVerReplica) {
                    ArrayList<Salon> salonesRemotos = sync.obtenerSalones();
                    ManejadorSalon msalon = new ManejadorSalon();
                    boolean r = msalon.cambiarDocumento(salonesRemotos, numeroVerReplica);
                    if (r) {
                        System.out.println("realizada sincronizacion con servidor: " + ipReplica);
                    }
                }
            }

        } catch (NotBoundException ex) {
            System.out.println("no existe servidor para sincronizar");
        } catch (MalformedURLException ex) {
            System.out.println("no existe servidor para sincronizar");
        } catch (RemoteException ex) {
            System.out.println("no existe servidor para sincronizar");
        }
    }
}
