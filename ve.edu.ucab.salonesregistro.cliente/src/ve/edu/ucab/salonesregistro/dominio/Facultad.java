package ve.edu.ucab.salonesregistro.dominio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase entidad para el manejo de la informacion de una facultad
 * @author gerardobarcia
 * @version 1.0
 */
public class Facultad implements Serializable {

    /** indetificador de la facultad*/
    private int id;
    /** nombre de la facultad*/
    private String nombre;
    /** listado de escuela que pertenecen a la facultad*/
    private ArrayList<Escuela> listaEscuelas;

    /** constructor por defecto*/
    public Facultad() {
    }

    /**
     * constructo con id
     * @param id int identificador de la facultad
     */
    public Facultad(int id) {
        this.setId(id);
    }

    /**
     * constructo con todos los datos
     * @param id int identificador de la facultad
     * @param nombre String nombre de la facultad
     * @param listaEscuelas ArrayList listado de escuelas delegadas a la facultad
     */
    public Facultad(int id, String nombre, ArrayList<Escuela> listaEscuelas) {
        this.setId(id);
        this.setNombre(nombre);
        this.setListaEscuelas(listaEscuelas);
    }

    /**
     * firma para obtener todas las escuelas de una facultad
     * @return ArrayList de las escuelas
     */
    public ArrayList getListaEscuelas() {
        return listaEscuelas;
    }

    /**
     * firma para establecer las escuelas de la facultad
     * @param listaEscuelas
     */
    public void setListaEscuelas(ArrayList<Escuela> listaEscuelas) {
        this.listaEscuelas = listaEscuelas;
    }

    /**
     * firma para obtener el id de la facultad
     * @return int id de la facultad
     */
    public int getId() {
        return id;
    }

    /**
     * firma para establecer el id de la facultad
     * @param id int identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * firma para obtener el nombre de la facultad
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * firma para establecer el nombre de la facultad
     * @param nombre String nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * firma de sobrecarga
     * @return el objeto como String
     */
    @Override
    public String toString() {
        return ("Facultad: " + this.id + "nombre: " + this.nombre +
                ", numero escuelas: " + this.listaEscuelas.size());
    }
}
