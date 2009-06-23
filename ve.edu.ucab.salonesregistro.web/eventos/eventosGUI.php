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
/**
 * firma para generar el codigo html que genere un formulario para realizar
 * reservas
 * @return <String> codigo HTML con el formulario
 */
function generarFormularioNuevaReserva () {
    $formulario ='<form id="formNuevaReserva">
   <table class="formTable" cellspacing="0">
   <tr>
      <thead>
        <td colspan="2">
        <div class="tituloBlanco1">
            NUEVA RESERVA
        </div>
        </td>
        </thead>
    </tr>
    <tr class="r1">
      <td colspan="2">Todos los campos son requeridos</td>
      </tr>
    <tr class="r0">
      <td>Identificador:</td>
      <td><label>
        <input type="text" name="id" id="id" size="30" onkeyup="this.value=this.value.toUpperCase();" />
      </label></td>
    </tr>
    <tr class="r1">
      <td>Persona id:</td>
      <td><label>
        <input type="text" name="per" id="per" onkeyup="this.value=this.value.toUpperCase();" size="30" />
      </label></td>
    </tr>
    <tr class="r0">
      <td>Salon id:</td>
      <td><label>
        <input type="text" name="sal" id="sal" onkeyup="this.value=this.value.toUpperCase();" size="30" />
      </label></td>
    </tr>
    <tr class="r1">
      <td height="26" colspan="2"><div align="center">
        <input name="button" type="button" id="button" value="RESERVAR" onclick= "xajax_procesar(xajax.getFormValues(\'formNuevaReserva\'))" />
      </div></td>
    </tr>
  </table>    <label></label></td>
    </tr>
</table>
</form>';
    return $formulario;
}
/**
 * funcion para desplegar el formulario para una nueva reserva
 * @return <RespuestaXajax> respuesta xajax para desplegar el form en un div
 */
function desplegarFormularioNuevaReserva () {
    $resultado = generarFormularioNuevaReserva();
    $objResponse = new xajaxResponse();
    $objResponse->addAssign("hacerReserva", "innerHTML", $resultado);
    return $objResponse;
}
/**
 * funcion para procesar una reserva
 * @param <Array> $datos los datos recogidos del formulario
 * @return <RespuestaXajax> respuesta xajax desplegando en un div un mensaje
 * de exito o fallo en la operacion
 */
function procesar ($datos) {
    $objResponse = new xajaxResponse();
    $respuesta = "";
    $control = new ClienteReservas();
    $r = new Reserva();
    $r->id = $datos['id'];
    $r->fecha = date ("d") ."-".date ("m"). "-".date ("Y");
    $r->status = true;
    $r->persona->id = $datos['per'];
    $r->salonReservado->id = $datos['sal'];
    $resultado = $control->realizarReserva($r);
    if ($resultado){
        $respuesta .= '<div class="exito">
                          <div class="textoMensaje">
                         Su reserva se ha efectado con exito
                          </div>
                          </div>';
    }
    else {
        $respuesta .= '<div class="error">
                          <div class="textoMensaje">
                          No se pudo completar la operacion. Intente de nuevo
                          </div>
                          </div>';
    }
    $objResponse->addAssign("mensaje", "innerHTML", $respuesta);
    $actualizarTablaPrincipalRespuesta = mostrarListaReservas();
    $objResponse->addAssign("reservas", "innerHTML", $actualizarTablaPrincipalRespuesta);
    return $objResponse;
}
?>
