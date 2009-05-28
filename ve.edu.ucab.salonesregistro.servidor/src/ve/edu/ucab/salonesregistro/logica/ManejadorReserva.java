package ve.edu.ucab.salonesregistro.logica;

import java.io.Serializable;
import ve.edu.ucab.salonesregistro.dominio.Reserva;
import ve.edu.ucab.salonesregistro.persistencia.DaoReservaContrato;
import ve.edu.ucab.salonesregistro.persistencia.FachadaBD;
import ve.edu.ucab.salonesregistro.sincronizacion.ManejadorVersiones;
import ve.edu.ucab.salonesregistro.sincronizacion.dominio.VersionDocumento;

/**
 * Clase controladora para el manejo de reservas
 * @author gerardobarcia
 * @version 1.0
 */
public class ManejadorReserva implements Serializable {

    /** referencia a las operaciones del contrato para reservas*/
    private DaoReservaContrato operacion;
    /** referencia a la version del documento*/
    private VersionDocumento version;
    /** referencia al manejador de versiones del documento*/
    private ManejadorVersiones manejadorVersiones = new ManejadorVersiones();
    /** nombre de referencia del archivo de version*/
    private final String nombreVersion = "Reserva";

    /**
     * constructor por defecto
     */
    public ManejadorReserva() {
        this.operacion = FachadaBD.obtenerInstancia();
    }

    /**
     * firma para actualizar la version del documento en caso de modificaciones
     * la version se incrementa en uno
     * @return valor booleano con la condicion de exito
     */
    private boolean actualizarVersionDocumento() {
        boolean resultado = false;
        Integer numeroVerAct = manejadorVersiones.obtenerNumeroVersion(nombreVersion);
        version = new VersionDocumento(nombreVersion, numeroVerAct);
        version.aumentarNumeroVersion();
        resultado = manejadorVersiones.editarVersion(version);
        return resultado;
    }

    /**
     * firma para realizar una reserva en el sistema
     * @param reserva objeto reserva con la informacion
     * @return valor booleano de exito de la operacion
     */
    public boolean realizarReserva(Reserva reserva) {
        boolean resultadoFinal = false;
        boolean resultado = this.operacion.realizarReserva(reserva);
        if (resultado) {
            resultadoFinal = actualizarVersionDocumento();
        }
        return resultadoFinal;
    }
}
