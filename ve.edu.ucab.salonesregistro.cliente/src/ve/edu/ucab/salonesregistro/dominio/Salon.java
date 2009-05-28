package ve.edu.ucab.salonesregistro.dominio;

import java.io.Serializable;

/**
 * Clase entidad para el manejo de informacion de Salon
 * @author gerardobarcia
 * @version 1.0
 */
public class Salon implements Serializable {

    /** variable para almacenar el identificador del salon*/
    private int id;
    /** variable para almacenar la capacidad del salon*/
    private int capacidad;
    /** variable para almacenar si el salon posee aire acondicionado*/
    private boolean aireAcondicionado;
    /** variable para almacenar si el salon posee video bean*/
    private boolean videoBean;
    /** variable para almacenar si el salon posee computador*/
    private boolean computador;

    /**
     * constructor por defecto
     */
    public Salon() {
    }

    /**
     * constructor completo
     * @param id identificador del salon
     * @param capacidad capacidad del salon
     * @param aireAcondicionado indica si el salon posee aire acondicionado
     * @param videoBean indica si el salon posee video bean
     * @param computador indica si el salon posee computador
     */
    public Salon(int id, int capacidad, boolean aireAcondicionado, boolean videoBean, boolean computador) {
        this.id = id;
        this.capacidad = capacidad;
        this.aireAcondicionado = aireAcondicionado;
        this.videoBean = videoBean;
        this.computador = computador;
    }

    /**
     * firma para comprobar si el salon posee aire acondicionado
     * @return valor booleano indicando si el salon posee aire acondicionado
     */
    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    /**
     * firma para establecer si el salon posee o no aire acondicionado
     * @param aireAcondicionado boolean indicador si el salon posee o no aire
     */
    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    /**
     * firma para obtener la capacidad de personas del salon
     * @return int capacidad del salon
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * firma para establecer la capacidad de personas del salon
     * @param capacidad int capacidad del salon
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * firma para comprobar si el salon posee o no computador
     * @return valor booleano que indica si el salon posee o no computador
     */
    public boolean isComputador() {
        return computador;
    }

    /**
     * firma para establecer si el salon posee o no computador
     * @param computador booleano que indica si el salon posee o no computador
     */
    public void setComputador(boolean computador) {
        this.computador = computador;
    }

    /**
     * firma para obtener el identificador del salon
     * @return int identificador del salon
     */
    public int getId() {
        return id;
    }

    /**
     * firma para establecer el identificador del salon
     * @param id identificador del salon
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * firma para identificar si el salon posee o no video bean
     * @return valor booleano que indica si el salon esta dotado o no con video b
     */
    public boolean isVideoBean() {
        return videoBean;
    }

    /**
     * firma para establecer si el salon esta dotado o no con video bean
     * @param videoBean valor booleano que indica si el salon esta dotado o no con
     * video bean
     */
    public void setVideoBean(boolean videoBean) {
        this.videoBean = videoBean;
    }

    /**
     * firma de sobrecarga
     * @return el objeto como String
     */
    @Override
    public String toString() {
        return "Salon: " + this.id + ", Capaidad: " + this.capacidad +
                ", Video Bean: " + this.videoBean + ", Aire A: " +
                this.aireAcondicionado + ", Computador: " + this.computador;
    }
}
