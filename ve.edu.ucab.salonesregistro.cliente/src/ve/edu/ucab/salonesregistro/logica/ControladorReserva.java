package ve.edu.ucab.salonesregistro.logica;

import java.rmi.RemoteException;
import ve.edu.ucab.salonesregistro.dominio.Reserva;

/**
 * Clase para controlar las operaciones remotas de reserva
 * @author gerardobarcia
 */
public class ControladorReserva {

    /** variable de referencia remota del registro de operaciones*/
    private RegistroGeneralContrato registro;

    /**
     * Constructor que recibe la interfaz remota para trabajar
     * @param reg interfaz remota del registro de operaciones
     */
    public ControladorReserva(RegistroGeneralContrato reg) {
        this.registro = reg;
    }

    /**
     * firma para realizar una reserva de un salon en el sistema
     * @param r el objeto reserva
     * @return valor booleano con la condicion de exito
     */
    public boolean hacerReserva(Reserva r) {
        boolean resultado = false;
        try {
            resultado = this.registro.realizarReserva(r);
        } catch (RemoteException ex) {
        } finally {
            return resultado;
        }
    }
}
