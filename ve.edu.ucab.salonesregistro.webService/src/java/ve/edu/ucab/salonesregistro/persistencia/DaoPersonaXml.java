package ve.edu.ucab.salonesregistro.persistencia;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ve.edu.ucab.salonesregistro.conf.ConfiguracionesRuta;
import ve.edu.ucab.salonesregistro.dominio.PersonaAutorizada;
import org.apache.log4j.Logger;

/**
 * Clase de acceso a datos para el objeto Persona
 * Trabaja con la tecnologia SAX y el objetivo es el acceso a datos para el
 * repositorio xml de la aplicacion
 * @author gerardobarcia
 * @version 1.0
 */
public class DaoPersonaXml extends DefaultHandler implements DaoPersonaContrato {

    /** variable para almacenar la coleccion de personas que se leeran del archivo xml*/
    private ArrayList<PersonaAutorizada> personas = null;
    /** variable para almacenar la ruta del archivo perteneciente al xml de las personas*/
    private String rutaArchivoPersonasXML;
    /** variable del elemento que sera leido en el proceso de parseo*/
    private String elemento = null;
    /** variable para almacenar informacion de las personas del archivo*/
    private PersonaAutorizada personaA;
    /** variable para el manejo de la bitacora log4j */
    private Logger bitacora = Logger.getLogger(getClass());

    /**
     * constructor que inicia la varible para leer la ruta del archivo a trabajar
     * @throws java.io.IOException
     */
    public DaoPersonaXml() throws IOException {
        bitacora.info("Iniciando un DAO para trabajar con xml de personas");
        ConfiguracionesRuta cr = ConfiguracionesRuta.obtenerInstancia();
        this.rutaArchivoPersonasXML = cr.getRutaArchivoPersonasXML();
    }

    /**
     * firma para obtener la lista de objetos persona que se encuentran en el archivo
     * @return ArrayList de objeros PersonaAutorizada
     */
    public ArrayList<PersonaAutorizada> obtenerPersonas() {
        try {
            DaoPersonaXml configParser = new DaoPersonaXml();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(this.rutaArchivoPersonasXML, configParser);
            personas = configParser.personas;
        } catch (ParserConfigurationException ex) {
            bitacora.error(ex.getMessage());
        } catch (SAXException e) {
            bitacora.error(e.getMessage());
        } catch (IOException e) {
            bitacora.error(e.getMessage());
        } finally {
            return personas;
        }
    }

    /**
     * firma sobrecargada para inicio del documento XML
     * @throws org.xml.sax.SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        bitacora.info("Iniciando el documento PersonasXML");
        this.personas = new ArrayList<PersonaAutorizada>();
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
        if (elemento.equals("persona")) {
            personaA = new PersonaAutorizada();
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
            personaA.setId(Integer.parseInt(palabra));
        } else if (elemento.equals("nombre")) {
            personaA.setNombreCompleto(palabra);
        } else if (elemento.equals("rol")) {
            char[] a = palabra.toCharArray();
            personaA.setRol(a[0]);
            this.personas.add(personaA);
        }
    }

    /**
     * firma sobrecargada para trabajar con el final del documento xml
     * @throws org.xml.sax.SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        bitacora.info("Finalizando lectura del documento XMLPersona");
    }
}
