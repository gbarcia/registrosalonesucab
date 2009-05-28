package ve.edu.ucab.salonesregistro.conf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Clase para obtener las propiedades de rmi
 * @author gerardobarcia
 */
public class ManejadorConfiguraciones {

    /** variable de instancia de esta clase (Singleton) */
    private static ManejadorConfiguraciones INSTANCIA;
    /** variable para almacenar la ruta del archivo de propiedad */
    private final String rutaArchivo = "Configuracion.properties";
    /** variable para el manejo de la bitacora log4j */
    private Logger bitacora = Logger.getLogger(getClass());
    /** variable para almacenar el numero de servidores en funcion */
    private Integer numeroServidores = 0;

    /**
     * constructor de la clase casteado para propocito singleton
     */
    private ManejadorConfiguraciones() {
    }

    /**
     * firma para crear una instancia de esta clase (patron singleton)
     */
    private synchronized static void crearInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ManejadorConfiguraciones();
        }
    }

    /**
     * firma para obtener la instancia de esta clase
     * @return instancia de la clase
     */
    public static ManejadorConfiguraciones obtenerInstancia() {
        if (INSTANCIA == null) {
            crearInstancia();
        }
        return INSTANCIA;
    }

    /**
     * firma para obtener la ruta del archivo de politicas de java
     * @return String la ruta
     */
    public String getRutaPoliticas() {
        Properties propiedad = new Properties();
        String ruta = null;
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            ruta = propiedad.getProperty("cliente.configuracion.rutaPoliticas");
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return ruta;
        }
    }

    /**
     * firma para obtener el ip del servidor del archivo properties
     * @param numero el numero de servidor a pedir su ip
     * @return la direccion ip del servidor solicitado
     */
    private String getIpServidor(Integer numero) {
        Properties propiedad = new Properties();
        String ruta = null;
        String busqueda = "cliente.configuracion.servidor" + numero.toString();
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            ruta = propiedad.getProperty(busqueda);
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return ruta;
        }
    }

    /**
     * firma para obtener el numero de servidores disponibles configurados
     */
    private void getNumeroServ() {
        Properties propiedad = new Properties();
        Integer numero = 0;
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            numero = Integer.parseInt(propiedad.getProperty("cliente.configuracion.numServidores"));
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            this.numeroServidores = numero;
        }
    }

    /**
     * firma que devuelve una lista con lo servidores disponibles y sus direcciones
     * ip correspodiente
     * @return lista con las direcciones de los servidores
     */
    public ArrayList<String> obtenerListaServidores() {
        this.getNumeroServ();
        ArrayList<String> resultado = new ArrayList<String>(numeroServidores);
        for (int i = 1; i <= this.getNumeroServidores(); i++) {
            String ip = getIpServidor(i);
            resultado.add(ip);
        }
        return resultado;
    }

    /**
     * firma para obtener el numero de servidores operativos
     * @return Integer el numero de servidores
     */
    public Integer getNumeroServidores() {
        this.getNumeroServ();
        return numeroServidores;
    }
}
