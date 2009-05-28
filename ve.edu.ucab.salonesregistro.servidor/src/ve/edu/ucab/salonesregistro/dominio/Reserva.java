package ve.edu.ucab.salonesregistro.dominio;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase entidad para el manejo de informacion de Reserva
 * @author gerardobarcia
 * @version 1.0
 */
public class Reserva implements Serializable {

    /** variable para almacenar el identificador de la reserva*/
    private int id;
    /** variable para almacenar la fecha de la reserva*/
    private Date fecha;
    /** variable para almacenar el status de la reserva*/
    private boolean status;
    /** variable para almacenar la persona que realizo la reserva*/
    private PersonaAutorizada persona;
    /** variable para almacenar el el salon reservado*/
    private Salon salonReservado;

    /**
     * constructor por defecto
     */
    public Reserva() {
    }

    /**
     * constructor con identificador
     * @param id identificador de la reserva
     */
    public Reserva(int id) {
        this.id = id;
    }

    /**
     * constructor con id,fecha y status
     * @param id indentificador de la reserva
     * @param fecha fecha que fue realizada la reserva
     * @param status estado de la reserva
     */
    public Reserva(int id, Date fecha, boolean status) {
        this.id = id;
        this.fecha = fecha;
        this.status = status;
    }

    /**
     * constructor completo
     * @param id identificador de la reserva
     * @param fecha fecha en que fue realizada la reserva
     * @param status estado de la reserva
     * @param persona persona que realizo la reserva
     * @param salonReservado salon que fue reservado
     */
    public Reserva(int id, Date fecha, boolean status, PersonaAutorizada persona,
            Salon salonReservado) {
        this.id = id;
        this.fecha = fecha;
        this.status = status;
        this.persona = persona;
        this.salonReservado = salonReservado;
    }

    /**
     * firma para obtener la persona autorizada que realizo la reserva
     * @return Objeto PersonaAutorizada que realizo la reserva
     */
    public PersonaAutorizada getPersona() {
        return persona;
    }

    /**
     * firma para establecer la persona autorizada que realizo la reserva
     * @param persona objeto PersonaAutorizada que realizo la reserva
     */
    public void setPersona(PersonaAutorizada persona) {
        this.persona = persona;
    }

    /**
     * firma para otener el salon que fue reservado
     * @return objeto Salon que fue reservado
     */
    public Salon getSalonReservado() {
        return salonReservado;
    }

    /**
     * firma para establecer el salon que fue reservado
     * @param salonReservado objeto Salon que fue reservado
     */
    public void setSalonReservado(Salon salonReservado) {
        this.salonReservado = salonReservado;
    }

    /**
     * firma para obtener la fecha de la reserva
     * @return la fecha de la reserva (Date)
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * firma para establecer la fecha de la reserva
     * @param fecha fecha en que fue realizada la reserva (Date)
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * firma para obtener el id de la reserva
     * @return identificador de la reserva
     */
    public int getId() {
        return id;
    }

    /**
     * firma para establecer el id de la reserva
     * @param id indentificador de la reserva
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * firma para obtener el estado actual de la reserva
     * @return boolean el estado actual de la reserva
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * firma para establecer el estado actual de la reserva
     * @param status boolean el estado actual de la reserva
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * firma de sobrecarga
     * @return el objeto como String
     */
    @Override
    public String toString() {
        return ("Reserva numero: " + this.id +
                ", fecha: " + this.fecha +
                ", estado: " + this.status +
                ", realizada por: " + this.persona.toString() +
                ", salon reservado: " + this.salonReservado.toString());
    }
}
