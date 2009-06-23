<?php
/**
 * Description of Salon
 * Clase entidad para el manejo de la informacion de un salon
 * @author gerardobarcia
 */
class Salon {
/** identificador del salon*/
    var $id;
/** capacidad del salon*/
    var $capacidad;
/** variable que determina si el salon posee aire acondicionado*/
    var $aireAcondicionado;
/** variable que determina si el salon posee video bean*/
    var $videoBean;
/** variable que determina si el salon posee computador*/
    var $computador;
/**
 * constructor por defecto
 */
    function __construct() {
    }
/**
 * firma para obtener el id del salon
 * @return <Integer> id del salon
 */
    public function getId() {
        return $this->id;
    }
/**
 * firma para establecer el id del salon
 * @param <Integer> $id ide del salon
 */
    public function setId($id) {
        $this->id = $id;
    }
/**
 * firma para establecer la capacidad del salon
 * @return <Integer> capacidad del salon
 */
    public function getCapacidad() {
        return $this->capacidad;
    }
/**
 * firma para establecer la capacidad del salon
 * @param <Integer> $capacidad capacidad del salon
 */
    public function setCapacidad($capacidad) {
        $this->capacidad = $capacidad;
    }
/**
 * firma para verificar si un salon posee aire acondicionado
 * @return <Boolean> true si posee, False en caso contrario
 */
    public function getAireAcondicionado() {
        return $this->aireAcondicionado;
    }
/**
 * firma para establecer si un salon posee aire acondicionado
 * @param <Boolean> $aireAcondicionado true si posee, False en caso contrario
 */
    public function setAireAcondicionado($aireAcondicionado) {
        $this->aireAcondicionado = $aireAcondicionado;
    }
/**
 * firma para verificar si un salon posee video bean
 * @return <Boolean> true si posee, False en caso contrario
 */
    public function getVideoBean() {
        return $this->videoBean;
    }
/**
 * firma para establecer si un salon posee vide bean
 * @param <Boolean> $videoBean true si posee, False en caso contrario
 */
    public function setVideoBean($videoBean) {
        $this->videoBean = $videoBean;
    }
/**
 * firma para verificar si un salon posee computador
 * @return <Boolean> true si posee, False en caso contrario
 */
    public function getComputador() {
        return $this->computador;
    }
/**
 * firma para establecer si un salon posee computador
 * @param <Boolean> $computador true si posee, False en caso contrario
 */
    public function setComputador($computador) {
        $this->computador = $computador;
    }

}
?>
