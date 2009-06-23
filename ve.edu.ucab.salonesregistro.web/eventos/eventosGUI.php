<?php
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.web.serviciotecnico/librerias/SOAP/Client.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.dominio/PersonaAutorizada.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.dominio/Reserva.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.dominio/Salon.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.web.serviciotecnico/ve.edu.ucab.salonesregistro.web.serviciotecnico.ws/ClienteReservas.php';
/**
 * firma para verificar el acceso a la aplicacion
 * @param <Array> $datos datos del formulario
 * @return <RespuestaXajax> respuesta de objeto xjax. Si el valor es correcto
 * se envia a la url del home, en caso contrario se despliega un mensaje de error
 */
function accesoSistema ($datos) {
    $objResponse = new xajaxResponse();
    if ($datos['clave'] == '1234') {
        $objResponse->addRedirect('presentacion/home.php');
    }
    else {
        $resultado = 'Acceso no autorizado';
        $objResponse->addAssign("mensaje", "innerHTML", $resultado);
    }
    return $objResponse;
}
/**
 * firma para desplegar la tabla que contiene la informacion de todos los salones
 * @return <String> codigo HTML para el despliegue de la tabla con la informacion
 * de todos los salones
 */
function obtenerTodosLosSalones () {
    $clienteReservas = new ClienteReservas();
    $lista = $clienteReservas->listaSalones();
    $resultado = '<table class="scrollTable" cellspacing="0">';
    $resultado.= '<thead>';
    $resultado.= '<tr>';
    $resultado.= '<th>IDENTIFICADOR</th>';
    $resultado.= '<th>CAPACIDAD</th>';
    $resultado.= '<th>AIRE ACONDICIONADO</th>';
    $resultado.= '<th>VIDEO BEAN</th>';
    $resultado.= '<th>COMPUTADOR</th>';
    $resultado.= '</tr>';
    $resultado.= '</thead>';
    $color = false;
    foreach ($lista as $salon) {
        if ($color){
            $resultado.= '<tr class="r0">';
        } else {
            $resultado.= '<tr class="r1">';
        }
        $resultado.= '<td>' . $salon->id. '</td>';
        $resultado.= '<td>' . $salon->capacidad. '</td>';
        if ($salon->aireAcondicionado == true) {
            $aire = 'SI';
        }
        else {
            $aire = 'NO';
        }
        $resultado.= '<td>' . $aire . '</td>';
        if ($salon->videoBean == true) {
            $video = 'SI';
        }
        else {
            $video = 'NO';
        }
        $resultado.= '<td>' . $video. '</td>';
        if ($salon->computador == true) {
            $comp = 'SI';
        }
        else {
            $comp = 'NO';
        }
        $resultado.= '<td>' . $comp. '</td>';
        $resultado.= '</tr>';
        $color = !$color;
    }
    $resultado.= '</table>';
    return $resultado;
}
/**
 * firma para desplegar el html que dibuja la tabla con la informacion
 * de todos los salones
 * @return <RespuestaXajax> respuesta con el div donde se desplegara la tabla
 */
function mostrarListaSalones () {
    $resultado = obtenerTodosLosSalones();
    $objResponse = new xajaxResponse();
    $objResponse->addAssign("salones", "innerHTML", $resultado);
    return $objResponse;
}
/**
 * firma para desplegar la tabla que contiene la informacion de todas las reservas
 * @return <String> codigo HTML para el despliegue de la tabla con la informacion
 * de todas las reservas
 */
function obtenerTodasLasReservas () {
    $clienteReservas = new ClienteReservas();
    $lista = $clienteReservas->listaReservas();
    $resultado = '<table class="scrollTable" cellspacing="0">';
    $resultado.= '<thead>';
    $resultado.= '<tr>';
    $resultado.= '<th>IDENTIFICADOR</th>';
    $resultado.= '<th>FECHA</th>';
    $resultado.= '<th>ESTADO</th>';
    $resultado.= '<th>PERSONA QUE RESERVO</th>';
    $resultado.= '<th>SALON RESERVADO</th>';
    $resultado.= '</tr>';
    $resultado.= '</thead>';
    $color = false;
    foreach ($lista as $reserva) {
        if ($color){
            $resultado.= '<tr class="r0">';
        } else {
            $resultado.= '<tr class="r1">';
        }
        $resultado.= '<td>' . $reserva->id. '</td>';
        $resultado.= '<td>' . $reserva->fecha. '</td>';
        if ($reserva->status == true) {
            $edo = 'ACTIVA';
        }
        else {
            $edo = 'CANCELADA';
        }
        $resultado.= '<td>' . $edo. '</td>';
        $resultado.= '<td>' . $reserva->persona->id. '</td>';
        $resultado.= '<td>' . $reserva->salonReservado->id. '</td>';
        $resultado.= '</tr>';
        $color = !$color;
    }
    $resultado.= '</table>';
    return $resultado;
}
/**
 * firma para desplegar el html que dibuja la tabla con la informacion
 * de todos las reservas
 * @return <RespuestaXajax> respuesta con el div donde se desplegara la tabla
 */
function mostrarListaReservas () {
    $resultado = obtenerTodasLasReservas();
    $objResponse = new xajaxResponse();
    $objResponse->addAssign("reservas", "innerHTML", $resultado);
    return $objResponse;
}

?>
