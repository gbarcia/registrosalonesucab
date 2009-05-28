package ve.edu.ucab.salonesregistro.persistencia;

import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.PersonaAutorizada;

/**
 * Contrato para el Acceso a datos de personas
 * @author gerardobarcia
 * @version 1.0
 */
public interface DaoPersonaContrato {

    /**
     * firma para obtener la lista de objetos persona que se encuentran en el archivo
     * @return ArrayList de objeros PersonaAutorizada
     */
    public ArrayList<PersonaAutorizada> obtenerPersonas();
}
