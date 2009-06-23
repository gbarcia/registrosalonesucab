package ve.edu.ucab.salonesregistro.dominio;

import java.io.Serializable;

/**
 * Clase entidad para el manejo de información de personas autorizadas
 * @author gerardobarcia
 * @version 1.0
 */
public class PersonaAutorizada implements Serializable {

    /** variable para almacenar el identificador de la persona*/
    private int id;
    /** variable para almacenar el nombre completo de la persona*/
    private String nombreCompleto;
    /**
     * variable para almacenar el rol de la persona
     * E: estudiante
     * P: profesor
     * A: administrador
     */
    private char rol;

    /**
     * constructor por defecto
     */
    public PersonaAutorizada() {
    }

    /**
     * constructor completo
     * @param id identificador de la persona
     * @param nombreCompleto nombre completo de la persona
     * @param rol rol de la persona
     */
    public PersonaAutorizada(int id, String nombreCompleto, char rol) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
    }

    /**
     * constructor con id
     * @param id identificador de la persona
     */
    public PersonaAutorizada(int id) {
        this.id = id;
    }

    /**
     * firma para obtener el identificador de la persona
     * @return int identificador de la persona
     */
    public int getId() {
        return id;
    }

    /**
     * firma para establecer el identificador de la persona
     * @param id int identificador de la persona
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * firma para obtener el nombre completo de la persona
     * @return String nombre completo
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * firma para establecer el nombre completo de la persona
     * @param nombreCompleto nombre completo de la persona
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * firma para obtener el rol de la persona
     * @return Char el rol de la persona
     */
    public char getRol() {
        return rol;
    }

    /**
     * firma para establecer el rol de la persona
     * @param rol el rol de la persona. Debe ser: A:administrador, E: estudiante y
     * P: profesor
     */
    public void setRol(char rol) {
        if (rol == 'A' || rol == 'E' || rol == 'P') {
            this.rol = rol;
        }
    }

    /**
     * firma de sobrecarga
     * @return el objeto como String
     */
    @Override
    public String toString() {
        return ("Persona id: " + this.id + ", nombre: " + this.nombreCompleto +
                ", rol: " + this.rol);
    }
}
