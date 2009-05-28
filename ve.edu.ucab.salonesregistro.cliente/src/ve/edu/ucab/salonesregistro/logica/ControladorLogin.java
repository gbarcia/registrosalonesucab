package ve.edu.ucab.salonesregistro.logica;

import java.rmi.RemoteException;

/**
 * Clase controladora del login
 * @author gerardobarcia
 */
public class ControladorLogin {

    /** variable de referencia remota del registro de operaciones*/
    private RegistroGeneralContrato registro;

    /**
     * Constructor que recibe la interfaz remota para trabajar
     * @param reg interfaz remota del registro de operaciones
     */
    public ControladorLogin(RegistroGeneralContrato reg) {
        this.registro = reg;
    }

    /**
     * firma para verifcar si la persona tiene acceso al sistema
     * @param id identificador a comprobar
     * @return valor booleano con la condicion de entrada
     */
    public boolean accesoAutorizado(Integer id) {
        boolean resultado = false;
        try {
            Integer respuesta = registro.existePersona(id);
            if (respuesta > 0) {
                resultado = true;
            }
        } catch (RemoteException ex) {
        } finally {
            return resultado;
        }
    }
}
