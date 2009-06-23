<?php
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.web.serviciotecnico/librerias/SOAP/Client.php';

/**
 * Description of ClienteReservas
 * Clase para la interaccion con el servicio web
 * @author gerardobarcia
 */
class ClienteReservas {
/** variable para el manejo de la instancia de un cliente soap*/
    private $cliente;
/** nombre del espacio que contiene al servicio web*/
    private $nombreEspacio = 'http://logica.salonesregistro.ucab.edu.ve/';
/** nombre de la direccion que contiene el servicio web*/
    private $url = 'http://localhost:8080/ve.edu.ucab.salonesregistro.webService/Reservas';
/**
 * constructor que inicia al cliente Soap y asigna el espacio del servicio web
 */
    function __construct() {
        $this->cliente = new SOAP_Client($this->url);
        $this->nombreEspacio = $this->nombreEspacio;
    }
/**
 * firma para obtener la lista de toda las reservas realizadas
 * @return <Array> de objetos Reserva
 */
    function listaReservas() {
        $param;
        return $this->cliente->call('listaReservas',$param, $this->nombreEspacio);
    }
/**
 * firma para realizar una reserva
 * @param <Reserva> $reserva objeto reserva
 * @return <Boolean> valor booleano con la condicion de exito de la operacion
 */
    function realizarReserva ($reserva) {
        $params = array();
        $params['reserva'] = $reserva;
        return $this->cliente->call('realizarReserva', $params, $this->nombreEspacio);
    }
/**
 * firma para obtener la lista de salones
 * @return <Array> de objetos salon
 */
    function listaSalones() {
        $param;
        return $this->cliente->call('listaSalones',$param, $this->nombreEspacio);
    }
}
?>
