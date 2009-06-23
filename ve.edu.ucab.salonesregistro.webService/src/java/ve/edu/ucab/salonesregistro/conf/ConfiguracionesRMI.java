package ve.edu.ucab.salonesregistro.conf;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Clase para obtener las propiedades de rmi
 * @author gerardobarcia
 */
public class ConfiguracionesRMI {

    /** variable de instancia de esta clase (Singleton) */
    private static ConfiguracionesRMI INSTANCIA;
    /** variable para almacenar la ruta del archivo de propiedad */
    private final String rutaArchivo = "PropiedadesRMI.properties";
    /** variable para el manejo de la bitacora log4j */
    private Logger bitacora = Logger.getLogger(getClass());

    /**
     * constructor de la clase casteado para propocito singleton
     */
    private ConfiguracionesRMI() {
    }

    /**
     * firma para crear una instancia de esta clase (patron singleton)
     */
    private synchronized static void crearInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ConfiguracionesRMI();
        }
    }

    /**
     * firma para obtener la instancia de esta clase
     * @return instancia de la clase
     */
    public static ConfiguracionesRMI obtenerInstancia() {
        if (INSTANCIA == null) {
            crearInstancia();
        }
        return INSTANCIA;
    }

    /**
     * firma para obtener la ruta del codigo base de rmi
     * @return String la ruta
     */
    public String getRutaCodeBase() {
        Properties propiedad = new Properties();
        String ruta = null;
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            ruta = propiedad.getProperty("propiedad.rmi.codebase");
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return ruta;
        }
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
            ruta = propiedad.getProperty("propiedad.rmi.politicajava");
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return ruta;
        }
    }

    /**
     * firma para obtener la direccion ip del servidor replica
     * @return String la ruta
     */
    public String getIPReplica() {
        Properties propiedad = new Properties();
        String ruta = null;
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            ruta = propiedad.getProperty("propiedad.rmi.replica");
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return ruta;
        }
    }
}
