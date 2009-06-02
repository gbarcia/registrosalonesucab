package ve.edu.ucab.salonesregistro.logica;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ve.edu.ucab.salonesregistro.conf.ManejadorConfiguraciones;
import ve.edu.ucab.salonesregistro.dominio.Reserva;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 * Clase que hace de fachada para la logica de la aplicacion
 * @author gerardobarcia
 */
public class FachadaLogica {

    /** variable de referencia al registro remoto*/
    private RegistroGeneralContrato registro;
    /** variable de referencia a la lista de servidores disponibles*/
    private ArrayList<String> listaServidores;
    /** variable de referencia al manejador de las configuraciones*/
    private ManejadorConfiguraciones manejadorConf = ManejadorConfiguraciones.obtenerInstancia();
    /** variable de referencia a la ruta de las politicas de java*/
    private String rutaPoliticas;
    /** variable que alamacenta los intentos de conexion*/
    private Integer intentosConexion = 0;
    /** variable que indica si la interfaz esta enlazada correctamente*/
    private boolean interfazEnlazada = false;
    /** variable que contiene el mensaje para el caso de que no funcione el servicio*/
    private final String mensajeNoDisp = "Disculpe el servicio no se encuentra disponible";

    /**
     * constructor que toma la ruta de las politicas, inicia la lista de servidores y
     * enlaza con la interfaz remota
     */
    public FachadaLogica() {
        rutaPoliticas = manejadorConf.getRutaPoliticas();
        listaServidores = manejadorConf.obtenerListaServidores();
        enlazarIntefazRemota((String) listaServidores.get(this.intentosConexion));
    }

    /**
     * firma para enlazar con la interfaz remota del registro general
     * realiza tantos intentos como numero de servidores existan
     * @param direccionIp la direccion ip a conectar
     */
    private void enlazarIntefazRemota(String direccionIp) {
        try {
            System.setProperty("java.security.policy", rutaPoliticas);
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }
            registro = (RegistroGeneralContrato) Naming.lookup("rmi://" + direccionIp + "/Registro");
            this.interfazEnlazada = true;
        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(null, this.mensajeNoDisp, "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, this.mensajeNoDisp, "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException ex) {
            intentosConexion++;
            if (intentosConexion < this.manejadorConf.getNumeroServidores()) {
                enlazarIntefazRemota((String) this.listaServidores.get(this.intentosConexion));
            } else {
                JOptionPane.showMessageDialog(null, this.mensajeNoDisp, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * firma para comprobar la validez de un loging usando el servicio remoto
     * @param id indetificador de la persona a consultar
     * @return valor booleano con la condicion de entrada
     */
    public boolean comprobarLogin(Integer id) {
        ControladorLogin cl = new ControladorLogin(registro);
        return cl.accesoAutorizado(id);
    }

    /**
     * firma para agregar un nuevo salon en el sistema
     * @param Salon objeto salon a agregar
     * @return valor booleano con la condicion de exito
     */
    public boolean registroNuevoSalon(Salon s) {
        ControladorSalones cs = new ControladorSalones(registro);
        return cs.nuevoSalon(s);

    }

    /**
     * firma para actualizar un salon determinado
     * @param Salon objeto salon a actualizar
     * @return valor booleano con la condicion de exito
     */
    public boolean actualizarRegistroSalon(Salon s) {
        ControladorSalones cs = new ControladorSalones(registro);
        return cs.actualizarSalon(s);
    }

    /**
     * firma para buscar un salon en el sistema
     * @param id indentificador del salon a buscar
     * @return null si no existe y el objeto salon buscado en dado caso que exista
     */
    public Salon buscarSalon(Integer id) {
        ControladorSalones cs = new ControladorSalones(registro);
        return cs.buscarUnSalon(id);
    }

    /**
     * firma para eliminar un salon del sistema
     * @param id indentificador del salon a eliminar
     * @return valor booleano con la condicion de resultado
     */
    public boolean eliminarSalon(Integer id) {
        ControladorSalones cs = new ControladorSalones(registro);
        return cs.eliminarSalon(id);
    }

    /**
     * firma para obtener todos los salones registrados en el sistema
     * @return lista de objetos salon
     */
    public ArrayList<Salon> obtenerTodosLosSalones() {
        ControladorSalones cs = new ControladorSalones(registro);
        return cs.todosLosSalones();
    }

    /**
     * firma para realizar una reserva de un salon en el sistema
     * @param r el objeto reserva
     * @return valor booleano con la condicion de exito
     */
    public boolean realizarUnaReserva(Reserva r) {
        ControladorReserva cr = new ControladorReserva(registro);
        return cr.hacerReserva(r);
    }

    /**
     * firma para conocer si la interfaz fue enlazada correctamente
     * @return valor booleano con la condicion de enlace
     */
    public boolean isInterfazEnlazada() {
        return interfazEnlazada;
    }
}
