<?php
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.web.serviciotecnico/librerias/SOAP/Client.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.dominio/PersonaAutorizada.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.dominio/Reserva.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.dominio/Salon.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.web.serviciotecnico/ve.edu.ucab.salonesregistro.web.serviciotecnico.ws/ClienteReservas.php';

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

function mostrarListaSalones () {
    $resultado = obtenerTodosLosSalones();
    $objResponse = new xajaxResponse();
    $objResponse->addAssign("salones", "innerHTML", $resultado);
    return $objResponse;
}

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
            $edo = 'ACTIVO';
        }
        else {
            $edo = 'CANCELADO';
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

function mostrarListaReservas () {
    $resultado = obtenerTodasLasReservas();
    $objResponse = new xajaxResponse();
    $objResponse->addAssign("reservas", "innerHTML", $resultado);
    return $objResponse;
}

?>
