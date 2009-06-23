package ve.edu.ucab.salonesregistro.persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import ve.edu.ucab.salonesregistro.conf.ConfiguracionesRuta;
import ve.edu.ucab.salonesregistro.dominio.PersonaAutorizada;
import ve.edu.ucab.salonesregistro.dominio.Reserva;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 * Clase concreta para el manejo de acceso a datos con xml de reserva
 * @author gerardobarcia
 * @version 1.0
 */
public class DaoReservaXml implements DaoReservaContrato {

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
    public DaoReservaXml() {
        try {
            ConfiguracionesRuta cr = ConfiguracionesRuta.obtenerInstancia();
            this.fileLocation = cr.getRutaArchivoReservasXML();
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = null;
            doc = builder.build(fileLocation);
            docRoot = doc.getRootElement();
            bitacora.info("Lectura Correcta del documento XML para reservas");
        } catch (JDOMException ex) {
            bitacora.error("No se pudo leer el documento xml porque: " + ex.getMessage());
        } catch (IOException ex) {
            bitacora.error("No se pudo leer el documento xml porque: " + ex.getMessage());
        }
    }

    /**
     * firma para pasar un objeto de dominio reserva a un elemento entendible
     * por la libreria jdom para dar soporte al trabajo con xml
     * @param reserva objeto reserva a traducir
     * @return objeto tipo element para el xml
     */
    private Element toXmlElement(Reserva reserva) {
        Element salonTransf = new Element("reserva");
        Element identificador = new Element("id");
        identificador.setText(Integer.toString(reserva.getId()));
        Element fecha = new Element("fecha");
        fecha.setText(reserva.getFecha().toString());
        Element estatus = new Element("estatus");
        estatus.setText(Boolean.toString(reserva.isStatus()));
        Element idPersona = new Element("idPersona");
        idPersona.setText(Integer.toString(reserva.getPersona().getId()));
        Element idSalon = new Element("idSalon");
        idSalon.setText(Integer.toString(reserva.getSalonReservado().getId()));
        salonTransf.addContent(identificador);
        salonTransf.addContent(fecha);
        salonTransf.addContent(estatus);
        salonTransf.addContent(idPersona);
        salonTransf.addContent(idSalon);
        return salonTransf;
    }

    /**
     * firma para pasar de un elemento de jdom a un objeto de dominio Reserva
     * @param element elemento a pasar
     * @return objeto Reserva
     * @throws java.text.ParseException
     */
    private Reserva reservaToObject(Element element) throws ParseException {
        Reserva reserva = new Reserva();
        reserva.setId(Integer.parseInt(element.getChildText("id")));
        String fecha = element.getChildText("fecha");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date d = sdf.parse(fecha);
        reserva.setFecha(d);
        Integer idPer = Integer.parseInt(element.getChildText("idPersona"));
        PersonaAutorizada persona = new PersonaAutorizada();
        persona.setId(idPer);
        reserva.setPersona(persona);
        Integer idSalon = Integer.parseInt(element.getChildText("idSalon"));
        Salon salon = new Salon();
        salon.setId(idSalon);
        reserva.setSalonReservado(salon);
        reserva.setStatus(Boolean.parseBoolean(element.getChildText("estatus")));
        return reserva;
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
     * firma para realizar una reserva en el sistema
     * @param reserva objeto reserva con la informacion
     * @return valor booleano de exito de la operacion
     */
    public boolean realizarReserva(Reserva reserva) {
        boolean resultado = false;
        docRoot.addContent(toXmlElement(reserva));
        resultado = updateDocument();
        if (resultado) {
            bitacora.info("Reserva Agregada Correctamente: " + reserva.getId());
        } else {
            bitacora.error("No se agrego correctamente la reserva: " + reserva.getId());
        }
        return resultado;
    }

    /**
     * firma para obtener todos las reservas del sistema
     * @return ArrayList de Objetos reserva con su informacion
     */
    public ArrayList<Reserva> todosLasReservas() {
        ArrayList<Reserva> resultado = new ArrayList<Reserva>();
        for (Object it : docRoot.getChildren()) {
            try {
                Element xmlElem = (Element) it;
                resultado.add(reservaToObject(xmlElem));
            } catch (ParseException ex) {
                bitacora.error("Error parseando la fecha: " + ex.getMessage());
            }
        }
        return resultado;
    }
}
