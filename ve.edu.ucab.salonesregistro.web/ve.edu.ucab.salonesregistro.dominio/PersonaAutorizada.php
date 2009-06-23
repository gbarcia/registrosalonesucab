<?php
/**
 * Description of PersonaAutorizada
 * clase entidad para manejar informacion de las personas autorizadas
 * @author gerardobarcia
 */
class PersonaAutorizada {
/** identificador de la persona*/
    var $id;
/** nombre completo de la persona*/
    var $nombreCompleto;
/** rol de la persona*/
    var $rol;
/**
 * constructor por defecto
 */
    function __construct() {

    }
/**
 * firma para obtener el id
 * @return <Integer> id de la persona
 */
    public function getId() {
        return $this->id;
    }
/**
 * firma para establecer el id
 * @param <Integer> $id id de la persona
 */
    public function setId($id) {
        $this->id = $id;
    }
/**
 * firma para obtener el nombre completo
 * @return <String> nombre completo de la persona
 */
    public function getNombreCompleto() {
        return $this->nombreCompleto;
    }
/**
 * firma para establecer el nombre completo de la persona
 * @param <String> $nombreCompleto el nombre completo de la persona
 */
    public function setNombreCompleto($nombreCompleto) {
        $this->nombreCompleto = $nombreCompleto;
    }
/**
 * firma para obtener el rol de la persona
 * @return <String> el rol de la persona
 */
    public function getRol() {
        return $this->rol;
    }
/**
 * firma para establecer el rol de la persona
 * @param <String> $rol el rol de la persona
 */
    public function setRol($rol) {
        $this->rol = $rol;
    }

}
?>
