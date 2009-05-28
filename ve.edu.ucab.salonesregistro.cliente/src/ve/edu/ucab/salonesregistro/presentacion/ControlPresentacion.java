package ve.edu.ucab.salonesregistro.presentacion;

import javax.swing.JOptionPane;
import ve.edu.ucab.salonesregistro.logica.FachadaLogica;

/**
 * Clase para el control de la presentacion
 * @author gerardobarcia
 */
public class ControlPresentacion {

    /** variable de referencia a la fachada de las operaciones remotas*/
    private FachadaLogica operaciones;
    /** variable de referencia a la ventana del login*/
    private VentanaInicio ventanaLogin;

    /**
     * constructor por defecto
     */
    public ControlPresentacion() {
    }

    /**
     * firma para iniciar el sistema
     */
    public void InicioSistema() {
        this.ventanaLogin = new VentanaInicio();
        this.ventanaLogin.setVisible(true);
    }

    /**
     * firma para comprobar la validez de un usuario que quiere ingresar en el
     * sistema
     * @param nombre nombre del usuario
     * @param clave clave del usuario
     */
    public void comprobarValidez(String nombre, String clave) {
        boolean resultado = false;
        Integer identificador = Integer.parseInt(clave);
        this.operaciones = new FachadaLogica();
        resultado = this.operaciones.comprobarLogin(identificador);
        if (resultado) {
            System.out.println("acceso autorizado"); // aca iria ir al menu principal
        } else {
            if (operaciones.isInterfazEnlazada()) {
                JOptionPane.showMessageDialog(null, "acceso no autorizado", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
