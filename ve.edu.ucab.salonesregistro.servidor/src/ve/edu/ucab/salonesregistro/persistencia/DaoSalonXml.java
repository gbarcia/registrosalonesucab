package ve.edu.ucab.salonesregistro.persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import ve.edu.ucab.salonesregistro.conf.ConfiguracionesRuta;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 * Clase para el acceso de datos de salones
 * @author gerardobarcia
 * @version 1.0
 */
public class DaoSalonXml implements DaoSalonContrato {

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
    public DaoSalonXml() {
        try {
            ConfiguracionesRuta cr = ConfiguracionesRuta.obtenerInstancia();
            this.fileLocation = cr.getRutaArchivoSalonesXML();
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = null;
            doc = builder.build(fileLocation);
            docRoot = doc.getRootElement();
            bitacora.info("Lectura Correcta del documento XML para salones");
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
    private Element toXmlElement(Salon salon) {
        Element salonTransf = new Element("salon");
        Element identificador = new Element("id");
        identificador.setText(Integer.toString(salon.getId()));
        Element capacidad = new Element("capacidad");
        capacidad.setText(Integer.toString(salon.getCapacidad()));
        Element aire = new Element("aire");
        aire.setText(Boolean.toString(salon.isAireAcondicionado()));
        Element video = new Element("video");
        video.setText(Boolean.toString(salon.isVideoBean()));
        Element pc = new Element("pc");
        pc.setText(Boolean.toString(salon.isComputador()));
        salonTransf.addContent(identificador);
        salonTransf.addContent(capacidad);
        salonTransf.addContent(aire);
        salonTransf.addContent(video);
        salonTransf.addContent(pc);
        return salonTransf;
    }

    /**
     * firma para pasar de on objeto de tipo elemento a un objeto de dominio
     * para dar soporte a la aplicacion
     * @param element elemento a traducir
     * @return objerto de dominio salon
     */
    private Salon salonToObject(Element element) {
        Salon salon = new Salon();
        salon.setId(Integer.parseInt(element.getChildText("id")));
        salon.setCapacidad(Integer.parseInt(element.getChildText("capacidad")));
        salon.setAireAcondicionado(Boolean.parseBoolean(element.getChildText("aire")));
        salon.setVideoBean(Boolean.parseBoolean(element.getChildText("video")));
        salon.setComputador(Boolean.parseBoolean(element.getChildText("pc")));
        return salon;
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
     * firma para agregar un nuevo salon
     * @param salon objeto salon a agregar
     * @return booleano de exito de la operacion
     */
    public boolean agregarSalon(Salon salon) {
        boolean resultado = false;
        docRoot.addContent(toXmlElement(salon));
        resultado = updateDocument();
        if (resultado) {
            bitacora.info("Salon Agregado Correctamente: " + salon.getId());
        } else {
            bitacora.error("No se agrego correctamente el salon: " + salon.getId());
        }
        return resultado;
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
            if (dato.equals(e.getChild("id").getText())) {
                return e;
            }
        }
        return null;
    }

    /**
     * firma para buscar un salon determinado
     * @param idSalon el identificador del salon
     * @return objto Salon con sus datos
     */
    public Salon buscarSalon(int idSalon) {
        Element aux = new Element("salon");
        List salones = this.docRoot.getChildren("salon");
        while (aux != null) {
            aux = DaoSalonXml.buscar(salones, Integer.toString(idSalon));
            if (aux != null) {
                return (salonToObject(aux));
            }
        }
        return null;
    }

    /**
     * firma para editar un salon
     * @param Salon objeto salon a editar
     * @return valor booleano de exito de la operacion
     */
    public boolean editarSalon(Salon Salon) {
        boolean resultado = false;
        Element aux = new Element("salon");
        List salones = this.docRoot.getChildren("salon");
        while (aux != null) {
            aux = DaoSalonXml.buscar(salones, Integer.toString(Salon.getId()));
            if (aux != null) {
                salones.remove(aux);
                resultado = updateDocument();
            }
        }
        agregarSalon(Salon);
        return resultado;
    }

    /**
     * firma para borrar un salon del sistema
     * @param idSalon identificador del salon
     * @return valor booleano de exito de la operacion
     */
    public boolean borrarSalon(int idSalon) {
        boolean resultado = false;
        Element aux = new Element("salon");
        List salones = this.docRoot.getChildren("salon");
        while (aux != null) {
            aux = DaoSalonXml.buscar(salones, Integer.toString(idSalon));
            if (aux != null) {
                salones.remove(aux);
                resultado = updateDocument();
            }
        }
        return resultado;
    }

    /**
     * firma para obtener todos los salones del sistema
     * @return ArrayList de Objetos salon con su informacion
     */
    public ArrayList<Salon> todosLosSalones() {
        ArrayList<Salon> resultado = new ArrayList<Salon>();
        for (Object it : docRoot.getChildren()) {
            Element xmlElem = (Element) it;
            resultado.add(salonToObject(xmlElem));
        }
        return resultado;
    }

    /**
     * firma para cambiar el documento y reempleazarlo por otro para fine de
     * operaciones de intercambio entre servidores
     * @param lista lista de salones nueva
     * @return valor booleano con la condicion de exito
     */
    public boolean cambiarDocumento(ArrayList<Salon> lista) {
        boolean resultadoFinal = true;
        ArrayList<Boolean> resultados = new ArrayList<Boolean>();
        ArrayList<Salon> salonesExistentes = todosLosSalones();
        for (Salon salon : salonesExistentes) {
            boolean resultadoBorrar = borrarSalon(salon.getId());
            resultados.add(resultadoBorrar);
        }
        for (Salon salon : lista) {
            boolean resultadoAgregar = agregarSalon(salon);
            resultados.add(resultadoAgregar);
        }
        for (Boolean resultadoL : resultados) {
            if (resultadoL == false) {
                resultadoFinal = false;
            }
        }
        return resultadoFinal;
    }
}
