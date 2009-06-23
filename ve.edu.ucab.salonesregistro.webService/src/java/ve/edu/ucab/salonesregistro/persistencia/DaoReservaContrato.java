package ve.edu.ucab.salonesregistro.persistencia;

import java.util.ArrayList;
import ve.edu.ucab.salonesregistro.dominio.Reserva;

/**
 * Contrato para las firmas de acceso a datos de reserva
 * @author gerardobarcia
 * @version 1.0
 */
public interface DaoReservaContrato {

    /**
     * firma para realizar una reserva en el sistema
     * @param reserva objeto reserva con la informacion
     * @return valor booleano de exito de la operacion
     */
    public boolean realizarReserva(Reserva reserva);

    /**
     * firma para obtener todos las reservas del sistema
     * @return ArrayList de Objetos reserva con su informacion
     */
    public ArrayList<Reserva> todosLasReservas();
}
