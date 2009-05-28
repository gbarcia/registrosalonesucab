package ve.edu.ucab.salonesregistro.sincronizacion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import ve.edu.ucab.salonesregistro.conf.ConfiguracionesRuta;
import ve.edu.ucab.salonesregistro.sincronizacion.dominio.VersionDocumento;

/**
 * Clase para el manejo de archivo de versiones
 * @author gerardobarcia
 */
public class ManejadorVersiones {

    /** variable que contiene la raiz del documento Xml*/
    private Element docRoot;
    /** variable que contiene la localizacion del archivo xml*/
    private String fileLocation;
    /** variable que contiene los el acceso a la bitacora log4j */
    private Logger bitacora = Logger.getLogger(getClass());

    /**
     * constructor por defecto que inicia los valores para trabajar con el documento
     * xml
     */
    public ManejadorVersiones() {
        try {
            ConfiguracionesRuta cr = ConfiguracionesRuta.obtenerInstancia();
            this.fileLocation = cr.getRutaArchivoVersionesXML();
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = null;
            doc = builder.build(fileLocation);
            docRoot = doc.getRootElement();
            bitacora.info("Lectura Correcta del documento XML para versiones");
        } catch (JDOMException ex) {
            bitacora.error("No se pudo leer el documento xml porque: " + ex.getMessage());
        } catch (IOException ex) {
            bitacora.error("No se pudo leer el documento xml porque: " + ex.getMessage());
        }
    }

    /**
     * firma para pasar un objeto de dominio salon a un elemento entendible
     * por la libreria jdom para dar soporte al trabajo con xml
     * @param salon objeto salon a traducir
     * @return objeto tipo element para el xml
     */
    private Element toXmlElement(VersionDocumento version) {
        Element versionNueva = new Element("version");
        Element archivo = new Element("archivo");
        archivo.setText(version.getNombre());
        Element numero = new Element("numero");
        numero.setText(Integer.toString(version.getNumeroVersion()));
        versionNueva.addContent(archivo);
        versionNueva.addContent(numero);
        return versionNueva;
    }

    /**
     * firma para pasar de on objeto de tipo elemento a un objeto de dominio
     * para dar soporte a la aplicacion
     * @param element elemento a traducir
     * @return objerto de dominio salon
     */
    private VersionDocumento elementToObject(Element element) {
        VersionDocumento documento = new VersionDocumento();
        documento.setNumeroVersion(Integer.parseInt(element.getChildText("numero")));
        documento.setNombre(element.getChildText("archivo"));
        return documento;
    }

    /**
     * Operacion para guardar en el documento Xml los cambios efectuados
     * @return true si se cumplio la operacion con exito, false en caso contrario
     */
    private boolean updateDocument() {
        try {
            XMLOutputter out = new XMLOutputter();
            FileOutputStream file = new FileOutputStream(fileLocation);
            out.output(docRoot, file);
            file.flush();
            file.close();
            bitacora.info("Documento XML Actualizado Exitosamente");
            return true;
        } catch (Exception e) {
            bitacora.error("No se pudo actualizar Documento XML por: " + e.getMessage());
            return false;
        }
    }

    /**
     * Operacion que busca un elemento que cumpla con una condicion en el id del xml
     * @param raiz la raiz del documento xml
     * @param dato el dato a comparar para la busqueda del elemento. Debe ser el identificador
     * @return retorna el elemento si existe con la condicion, en caso contrario retorna null
     */
    public static Element buscar(List raiz, String dato) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (dato.equals(e.getChild("archivo").getText())) {
                return e;
            }
        }
        return null;
    }

    /**
     * firma para agregar una nueva version
     * @param version objeto version a agregar
     * @return booleano de exito de la operacion
     */
    private boolean agregarVersion(VersionDocumento version) {
        boolean resultado = false;
        docRoot.addContent(toXmlElement(version));
        resultado = updateDocument();
        if (resultado) {
            bitacora.info("Version Agregada Correctamente: " + version.getNombre());
        } else {
            bitacora.error("No se agrego correctamente la version: " + version.getNombre());
        }
        return resultado;
    }

    /**
     * firma para editar una version
     * @param version objeto version a editar
     * @return valor booleano con la condicion de exito
     */
    public boolean editarVersion(VersionDocumento version) {
        boolean resultado = false;
        Element aux = new Element("version");
        List versiones = this.docRoot.getChildren("version");
        while (aux != null) {
            aux = ManejadorVersiones.buscar(versiones, version.getNombre());
            if (aux != null) {
                versiones.remove(aux);
                resultado = updateDocument();
            }
        }
        agregarVersion(version);
        return resultado;
    }

    /**
     * firma para obtener el numero de version de un archivo dado
     * @param nombreArchivo el nombre de archivo a buscar su version
     * para SalonXML el nombre es: Salon
     * para ReservaXML el nombre es: Reserva
     * @return el numero de version actual del archivo
     */
    public Integer obtenerNumeroVersion(String nombreArchivo) {
        Integer version = null;
        Element aux = new Element("version");
        List versiones = this.docRoot.getChildren("version");
        while (aux != null) {
            aux = ManejadorVersiones.buscar(versiones, nombreArchivo);
            if (aux != null) {
                version = Integer.parseInt(aux.getChildText("numero"));
                aux = null;
            }
        }
        return version;
    }
}
