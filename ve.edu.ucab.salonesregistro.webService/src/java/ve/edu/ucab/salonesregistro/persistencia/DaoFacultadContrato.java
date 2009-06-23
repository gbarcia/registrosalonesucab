package ve.edu.ucab.salonesregistro.persistencia;

import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.Facultad;

/**
 * Contrato para el acceso a datos de facultades y escuelas
 * @author gerardobarcia
 * @version 1.0
 */
public interface DaoFacultadContrato {

    /**
     * firma para obtener todas las facultades y escuelas del sistema
     * @return ArrayList de objetos Facultad
     */
    public ArrayList<Facultad> traerTodasLasFacultades();
}
