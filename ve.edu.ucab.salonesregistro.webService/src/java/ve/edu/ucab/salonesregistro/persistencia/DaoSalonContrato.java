package ve.edu.ucab.salonesregistro.persistencia;

import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.Salon;

/**
 * Contrato para el Acceso a datos de salones
 * @author gerardobarcia
 * @version 1.0
 */
public interface DaoSalonContrato {

    /**
     * firma para agregar un nuevo salon
     * @param salon objeto salon a agregar
     * @return booleano de exito de la operacion
     */
    public boolean agregarSalon(Salon salon);

    /**
     * firma para buscar un salon determinado
     * @param idSalon el identificador del salon
     * @return objto Salon con sus datos
     */
    public Salon buscarSalon(int idSalon);

    /**
     * firma para editar un salon
     * @param Salon objeto salon a editar
     * @return valor booleano de exito de la operacion
     */
    public boolean editarSalon(Salon Salon);

    /**
     * firma para borrar un salon del sistema
     * @param idSalon identificador del salon
     * @return valor booleano de exito de la operacion
     */
    public boolean borrarSalon(int idSalon);

    /**
     * firma para obtener todos los salones del sistema
     * @return ArrayList de Objetos salon con su informacion
     */
    public ArrayList<Salon> todosLosSalones();

    /**
     * firma para cambiar el documento y reempleazarlo por otro para fine de
     * operaciones de intercambio entre servidores
     * @param lista lista de salones nueva
     * @return valor booleano con la condicion de exito
     */
    public boolean cambiarDocumento(ArrayList<Salon> lista);
}
