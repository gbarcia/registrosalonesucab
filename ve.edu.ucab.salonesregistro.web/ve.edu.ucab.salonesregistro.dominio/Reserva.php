<?php
/**
 * Description of Reserva
 * Clase entidad para almacenar informacion de una reserva
 * @author gerardobarcia
 */
class Reserva {
/** identificador de la reserva*/
    var $id;
/** fecha en que se efectuo la reserva*/
    var $fecha;
/** estado de la reserva*/
    var $status;
/** identificador de la persona que reaizo la reserva*/
    var $persona;
/** identificador del salon que fue reservado*/
    var $salonReservado;
/**
 * constructor por defecto
 */
    function __construct() {
    }
/**
 * firma para obtener el id
 * @return <Integer> id de la reserva
 */
    public function getId() {
        return $this->id;
    }
/**
 * firma para establecer el id
 * @param <Integer> $id id de la reserva
 */
    public function setId($id) {
        $this->id = $id;
    }
/**
 * firma para obtener la fecha
 * @return <Date> fecha en que fue realizada la reserva
 */
    public function getFecha() {
        return $this->fecha;
    }
/**
 * firma para establecer la fecha
 * @param <Date> $fecha fecha en que fue efectuada la reserva
 */
    public function setFecha($fecha) {
        $this->fecha = $fecha;
    }
/**
 * firma para obtener el estado de la reserva
 * @return <boolean> estado de la reserva
 */
    public function getStatus() {
        return $this->status;
    }
/**
 * firma para establecer el estado de la reserva
 * @param <boolean> $status  estado de la reserva
 */
    public function setStatus($status) {
        $this->status = $status;
    }
/**
 * firma para obtener la persona que realizo la reserva
 * @return <Persona> objeto persona que realizo la reserva
 */
    public function getPersona() {
        return $this->persona;
    }
/**
 * firma para establecer la persona que realizo la reserva
 * @param <Persona> $persona persona que realizo la reserva
 */
    public function setPersona($persona) {
        $this->persona = $persona;
    }
/**
 * firma para obtener el objeto salon que fue reservado
 * @return <Salon> objeto salon que se reservo
 */
    public function getSalonReservado() {
        return $this->salonReservado;
    }
/**
 * firma para establecer el salon que fue reservado
 * @param <Salon> $salonReservado objeto salon reservado
 */
    public function setSalonReservado($salonReservado) {
        $this->salonReservado = $salonReservado;
    }
}
?>
