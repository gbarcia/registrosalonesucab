package ve.edu.ucab.salonesregistro.conf;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Clase para mantener la configuracion de rutas de archivos XML
 * @author gerardobarcia
 */
public class ConfiguracionesRuta {

    /** variable de instancia para dar soporte al patron Singleton*/
    private static ConfiguracionesRuta INSTANCIA;
    /** variable para almacenar la ruta del archivo de propiedades*/
    private final String rutaArchivo = "RutasArchivosXML.properties";
    /** variable para el manejo de la bitacora log4j */
    private Logger bitacora = Logger.getLogger(getClass());

    /**
     * constructor casteado para propositos del patron singleton
     */
    private ConfiguracionesRuta() {
    }

    /**
     * firma para crear una instancia de esta clase (patron singleton)
     */
    private synchronized static void crearInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ConfiguracionesRuta();
        }
    }

    /**
     * firma para obtener la instancia de esta clase
     * @return instancia de la clase
     */
    public static ConfiguracionesRuta obtenerInstancia() {
        if (INSTANCIA == null) {
            crearInstancia();
        }
        return INSTANCIA;
    }

    /**
     * firma para obtener la ruta del archivo XML para trabajar con personas
     * @return String la ruta del archivo
     */
    public String getRutaArchivoPersonasXML() {
        Properties propiedad = new Properties();
        String rutaArchivoPersonas = null;
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            rutaArchivoPersonas = propiedad.getProperty("ruta.archivo.personas");
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return rutaArchivoPersonas;
        }
    }

    /**
     * firma para obtener la ruta del archivo XML para trabajar con reservas
     * @return String la ruta del archivo
     */
    public String getRutaArchivoReservasXML() {
        Properties propiedad = new Properties();
        String rutaArchivoReservas = null;
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            rutaArchivoReservas = propiedad.getProperty("ruta.archivo.reservas");
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return rutaArchivoReservas;
        }
    }

    /**
     *firma para obtener la ruta del archivo XML para trabajar con salones
     * @return String la ruta del archivo
     */
    public String getRutaArchivoSalonesXML() {
        Properties propiedad = new Properties();
        String rutaArchivoSalones = null;
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            rutaArchivoSalones = propiedad.getProperty("ruta.archivo.salones");
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return rutaArchivoSalones;
        }
    }

    /**
     *firma para obtener la ruta del archivo XML para trabajar con facultades
     * @return String la ruta del archivo
     */
    public String getRutaArchivoFacultadesXML() {
        Properties propiedad = new Properties();
        String rutaArchivoSalones = null;
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            rutaArchivoSalones = propiedad.getProperty("ruta.archivo.facultades");
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return rutaArchivoSalones;
        }
    }
    /**
     *firma para obtener la ruta del archivo XML para trabajar con versiones
     * @return String la ruta del archivo
     */
    public String getRutaArchivoVersionesXML() {
        Properties propiedad = new Properties();
        String rutaArchivoVersiones = null;
        try {
            propiedad.load(getClass().getResourceAsStream(this.rutaArchivo));
            rutaArchivoVersiones = propiedad.getProperty("ruta.archivo.versiones");
        } catch (IOException ex) {
            bitacora.info(ex.getMessage());
        } finally {
            return rutaArchivoVersiones;
        }
    }
}
