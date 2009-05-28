package ve.edu.ucab.salonesregistro.logica;

import ve.edu.ucab.salonesregistro.presentacion.ControlPresentacion;

/**
 * Clase main de incio para el cliente
 * @author gerardobarcia
 */
public class Cliente {

    /**
     * metodo de inicio del sistema para el cliente
     * @param args argumentos a pasar en caso de ser necesario
     */
    public static void main(String[] args) {
       ControlPresentacion control = new ControlPresentacion();
       control.InicioSistema();
    }
}
