package ve.edu.ucab.salonesregistro.logica;

import java.io.Serializable;
import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.PersonaAutorizada;
import ve.edu.ucab.salonesregistro.persistencia.DaoPersonaContrato;
import ve.edu.ucab.salonesregistro.persistencia.FachadaBD;

/**
 * Clase controladora para el manejo de las autorizaciones
 * @author gerardobarcia
 * @author v 1.0
 */
public class ManejadorAutorizacion implements Serializable {

    /** variabe de referencia al contrato para soporte de operaciones con personas*/
    private DaoPersonaContrato operacion;

    /**
     * constructor por defecto que genera una instancia de la fachada de la bd
     */
    public ManejadorAutorizacion() {
        operacion = FachadaBD.obtenerInstancia();
    }

    /**
     * firma para verificar si existe una persona para darle acceso al sistema. En
     * caso de la que persona exista se envia su identificador, si no existe se
     * regresa un valor nulo
     * @param id identificador de la persona a verificar
     * @return null si no existe, en caso contrario su identificador
     */
    public Integer existePersona(Integer id) {
        Integer resultado = null;
        ArrayList<PersonaAutorizada> listaPersonas = this.operacion.obtenerPersonas();
        for (PersonaAutorizada personaAutorizada : listaPersonas) {
            if (personaAutorizada.getId() == id) {
                resultado = personaAutorizada.getId();
            }
        }
        return resultado;
    }
}
