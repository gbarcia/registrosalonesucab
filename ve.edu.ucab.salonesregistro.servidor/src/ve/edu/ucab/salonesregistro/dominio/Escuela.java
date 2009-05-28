package ve.edu.ucab.salonesregistro.dominio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase entidad para el manejo de la informacion de una escuela
 * @author gerardobarcia
 * @version 1.0
 */
public class Escuela implements Serializable {

    /** indetificador de la escuela*/
    private int id;
    /** nombre de la escuela*/
    private String nombre;
    /** coleccion de salones que tiene asignado la escuela*/
    private ArrayList<Salon> listaSalones;

    /** constructor por defecto*/
    public Escuela() {
    }

    /**
     * constructor con identificador
     * @param id int identificador de la escuela
     */
    public Escuela(int id) {
        this.setId(id);
    }

    /**
     * constructor con todos los atributos
     * @param id int identificador de la escuela
     * @param nombre String nombre de la escuela
     * @param listaSalones ArrayList listado de objetos Salon
     */
    public Escuela(int id, String nombre, ArrayList<Salon> listaSalones) {
        this.setId(id);
        this.setNombre(nombre);
        this.setListaSalones(listaSalones);
    }

    /**
     * firma para obtener el identificador
     * @return int identificador
     */
    public int getId() {
        return id;
    }

    /**
     * firma para establecer el identificador
     * @param id int id de la escuela
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * firma para obtener el listado de salones
     * @return ArrayList lista salones
     */
    public ArrayList<Salon> getListaSalones() {
        return listaSalones;
    }

    /**
     * firma para establecer la lista de salones de una escuela
     * @param listaSalones ArrayList lista de objetos salon
     */
    public void setListaSalones(ArrayList<Salon> listaSalones) {
        this.listaSalones = listaSalones;
    }

    /**
     * firma para obtener el nombre
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * firma para establecer el nombre
     * @param nombre String nombre de la ecuela
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
        return ("Escuela: " + this.id + ", nombre: " + this.nombre +
                ", numero de salones: " + this.listaSalones.size());
    }
}