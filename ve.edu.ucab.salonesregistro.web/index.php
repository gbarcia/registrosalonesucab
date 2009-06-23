<?php
require $_SERVER['DOCUMENT_ROOT'] .'/ve.edu.ucab.salonesregistro.web/ve.edu.ucab.salonesregistro.web.serviciotecnico/librerias/xajax/xajax.inc.php';
require_once $_SERVER['DOCUMENT_ROOT'] . '/ve.edu.ucab.salonesregistro.web/eventos/eventosGUI.php';
$xajax = new xajax();
$xajax->registerFunction("accesoSistema");
$xajax->processRequests();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Sistema de registro de Salones - UCAB</title>
        <?
        $xajax->printJavascript ("ve.edu.ucab.salonesregistro.web.serviciotecnico/librerias/xajax/");
        ?>
        <style type="text/css">
            @import url(presentacion/ccs/styleMain.css);
            @import url(presentacion/ccs/styleTabla.css);
        </style>
        <style type="text/css">
            <!--
            .style1 {
                font-size: 10px
            }
            .style2 {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
            }
            -->
        </style>
    </head>

    <body>
        <form id="entrada" name="entrada">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center"><table width="900" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td colspan="3">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="585"><img src="presentacion/imagenes/header-bg.jpg" width="585" height="76" /></td>
                                <td width="33">&nbsp;</td>
                                <td width="282"><img src="presentacion/imagenes/logo.jpg" width="101" height="80" /></td>
                            </tr>
                            <tr>
                                <td colspan="3">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="3"><strong>SISTEMA DE RESERVA DE SALONES - UCAB</strong></td>
                            </tr>
                            <tr>
                                <td colspan="3">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center"><table width="303" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td colspan="3"><img src="presentacion/imagenes/up.gif" width="303" height="37" /></td>
                                        </tr>
                                        <tr>
                                            <td><img src="presentacion/imagenes/1.gif" width="21" height="151" /></td>
                                            <td width="258" valign="top" bgcolor="#7FB8DD"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                <tr>
                                                                    <td colspan="2">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="31%"><span class="style2">Usuario:</span></td>
                                                                    <td width="69%"><input type="text" name="usuario" id="usuario" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="2">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="style2">Clave:</td>
                                                                    <td><input type="password" name="clave" id="clave" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="2">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="2"><div align="center">
                                                                                            <input type="button" name="button" id="button" value="ACCEDER" onclick="xajax_accesoSistema(xajax.getFormValues('entrada'))" />
                                                                    </div></td>
                                                                </tr>
                                                        </table></td>
                                                    </tr>
                                            </table></td>
                                            <td><img src="presentacion/imagenes/2.gif" width="24" height="151" /></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3"><img src="presentacion/imagenes/down.gif" width="303" height="22" /></td>
                                        </tr>
                                </table></td>
                            </tr>
                            <tr>
                                <td colspan="3"><div id="mensaje" align="center"></div></td>
                            </tr>
                            <tr>
                                <td colspan="3">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="3"><div align="center" class="style1">©2009. Todos los derechos reservados</div></td>
                            </tr>
                    </table></td>
                </tr>
            </table>
        </form>
    </body>
</html>
