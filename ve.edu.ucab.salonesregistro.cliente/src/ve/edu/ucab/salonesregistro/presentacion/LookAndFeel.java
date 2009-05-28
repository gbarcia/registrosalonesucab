package ve.edu.ucab.salonesregistro.presentacion;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Clase para el manejo del Look and Feel de la interfaza grafica, segun el
 * sistema operativo deonde se ejecute la aplicacion
 * @author jonathan
 */
public class LookAndFeel {

    public LookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
