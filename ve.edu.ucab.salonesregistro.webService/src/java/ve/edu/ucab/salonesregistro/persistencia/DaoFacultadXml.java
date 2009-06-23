package ve.edu.ucab.salonesregistro.persistencia;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ve.edu.ucab.salonesregistro.conf.ConfiguracionesRuta;
import ve.edu.ucab.salonesregistro.dominio.Escuela;
import ve.edu.ucab.salonesregistro.dominio.Facultad;

/**
 * Clase concreta para el acceso a objetos de datos de Facultades y escuelas
 * @author gerardobarcia
 * @version 1.0
 */
public class DaoFacultadXml extends DefaultHandler implements DaoFacultadContrato {

    /** variable para almacenar la coleccion de escuelas que se leeran del archivo xml*/
    private ArrayList<Escuela> escuelas = null;
    /** variable para almacenar la coleccion de facultades que se leeran del archivo xml*/
    private ArrayList<Facultad> facultades = null;
    /** variable para almacenar la ruta del archivo perteneciente al xml de las facultades*/
    private String rutaArchivoFacultadXML;
    /** variable del elemento que sera leido en el proceso de parseo*/
    private String elemento = null;
    /** variable para almacenar informacion de las escuelas del archivo*/
    private Escuela escuela;
    /** variable para almacenar informacion de las facultades del archivo*/
    private Facultad facultad;
    /** variable para el manejo de la bitacora log4j */
    private Logger bitacora = Logger.getLogger(getClass());

    /**
     * constructor que inicia la varible para leer la ruta del archivo a trabajar
     * @throws java.io.IOException
     */
    public DaoFacultadXml() throws IOException {
        bitacora.info("Iniciando un DAO para trabajar con xml de personas");
        ConfiguracionesRuta cr = ConfiguracionesRuta.obtenerInstancia();
        this.rutaArchivoFacultadXML = cr.getRutaArchivoFacultadesXML();
    }

    /**
     * firma sobrecargada para inicio del documento XML
     * @throws org.xml.sax.SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        bitacora.info("Iniciando el documento FacultadXML");
        this.facultades = new ArrayList<Facultad>();
    }

    /**
     * firma sobrecargada para trabajar con los elementos del XML
     * @param uri
     * @param localName
     * @param rawName
     * @param atts
     * @throws org.xml.sax.SAXException
     */
    @Override
    public void startElement(String uri, String localName, String rawName,
            Attributes atts)
            throws SAXException {
        elemento = rawName;
        if (elemento.equals("facultad")) {
            this.facultad = new Facultad();
        } else if (elemento.equals("escuelas")) {
            this.escuelas = new ArrayList<Escuela>();
            this.facultad.setListaEscuelas(escuelas);
        } else if (elemento.equals("escuela")) {
            this.escuela = new Escuela();
        }
    }

    /**
     * firma sobrecargada para trabajar con elementos internos del xml
     * @param chars
     * @param start
     * @param length
     * @throws org.xml.sax.SAXException
     */
    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        String palabra = new String(chars, start, length).trim();
        if (palabra.equals("")) {
            return;
        }
        if (elemento.equals("id")) {
            this.facultad.setId(Integer.parseInt(palabra));
        } else if (elemento.equals("nombre")) {
            this.facultad.setNombre(palabra);
        } else if (elemento.equals("ide")) {
            this.escuela.setId(Integer.parseInt(palabra));
        } else if (elemento.equals("nombres")) {
            this.escuela.setNombre(palabra);
            this.facultad.getListaEscuelas().add(this.escuela);
        }
    }

    /**
     * firma sobrecargada para trabajar con el final del documento xml
     * @throws org.xml.sax.SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        bitacora.info("Finalizando lectura del documento XMLPersona");
        this.facultades.add(facultad);
    }

    /**
     * firma para obtener todas las facultades y escuelas del sistema
     * @return ArrayList de objetos Facultad
     */
    public ArrayList<Facultad> traerTodasLasFacultades() {
        try {
            DaoFacultadXml configParser = new DaoFacultadXml();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(this.rutaArchivoFacultadXML, configParser);
            this.facultades = configParser.facultades;
        } catch (ParserConfigurationException ex) {
            bitacora.error(ex.getMessage());
        } catch (SAXException e) {
            bitacora.error(e.getMessage());
        } catch (IOException e) {
            bitacora.error(e.getMessage());
        } finally {
            return this.facultades;
        }
    }
}
