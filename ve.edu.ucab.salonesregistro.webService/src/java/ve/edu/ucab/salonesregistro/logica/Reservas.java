
package ve.edu.ucab.salonesregistro.logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import ve.edu.ucab.salonesregistro.dominio.Reserva;
import ve.edu.ucab.salonesregistro.dominio.Salon;


/**
 * Servicio web para realizar reservas en el sistema de la ucab
 * @author gerardobarcia
 */
@WebService()
public class Reservas {

    /**
     * Web service operation listaReserva
     * @return coleccion de objetos reserva
     */
    @WebMethod(operationName = "listaReservas")
    public java.util.ArrayList<Reserva> listaReservas() {
        ArrayList<Reserva> resultado = null;
        ManejadorReserva control = new ManejadorReserva();
        resultado = control.todosLasReservas();
        return resultado;
    }

    /**
     * Web service operation relizarReserva
     * @param reserva objeto reserva
     * @return valor booleano con la condicion
     * @throws ParseException
     */
    @WebMethod(operationName = "realizarReserva")
    public boolean realizarReserva(@WebParam(name = "reserva")
    Reserva reserva) throws ParseException {
        boolean resultado = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date d = sdf.parse("02/07/2009");
        reserva.setFecha(d);
        ManejadorReserva control = new ManejadorReserva();
        resultado = control.realizarReserva(reserva);
        return resultado;
    }

    /**
     * Web service operation listaSalones
     * @return coleccion de objetos salon
     */
    @WebMethod(operationName = "listaSalones")
    public java.util.ArrayList<Salon> listaSalones() {
        ArrayList<Salon> resultado = null;
        ManejadorSalon control = new ManejadorSalon();
        resultado = control.todosLosSalones();
        return resultado;
    }

}