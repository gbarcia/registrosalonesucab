<?php
require_once '../ve.edu.ucab.salonesregistro.web.serviciotecnico/librerias/SOAP/Client.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.dominio/PersonaAutorizada.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.dominio/Reserva.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.dominio/Salon.php';
require_once$_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.web.serviciotecnico/ve.edu.ucab.salonesregistro.web.serviciotecnico.ws/ClienteReservas.php';

$clienteReservas = new ClienteReservas();
//
//$resultado = $clienteReservas->listaReservas();
//$reserva = new Reserva();
//$salon = new Salon();
//
//foreach ($resultado as $reserva) {
//    echo $reserva->salonReservado->id;
//    echo $reserva->fecha;
//}
//echo $resultado;

//        $r = new Reserva();
//        $r->id = 200;
//        $r->fecha = date ("d") ."-".date ("m"). "-".date ("Y");
//        $r->status = true;
//        $r->persona->id = 1;
//        $r->salonReservado->id = 1;
//
//$resultado = $clienteReservas->realizarReserva($r);
//echo $resultado;
$salon = new Salon();
$resultado = $clienteReservas->listaSalones();
foreach ($resultado as $salon) {
    echo $salon->id;
}
echo $resultado;

?>
