package ve.edu.ucab.salonesregistro.sincronizacion.dominio;

/**
 * Entidad responsable de guardar la version de los documentos del servidor
 * @author gerardobarcia
 */
public class VersionDocumento {

    /**nombre del documento*/
    private String nombre;
    /** numero de version del documento*/
    private Integer numeroVersion;

    /** constructor por defecto*/
    public VersionDocumento() {
    }

    /**
     * constructor con nombre y numero de version
     * @param nombre nombre del documento
     * @param numeroVersion numero de version
     */
    public VersionDocumento(String nombre, Integer numeroVersion) {
        this.nombre = nombre;
        this.numeroVersion = numeroVersion;
    }

    /**
     * firma para obtener el nombre del documento
     * @return nombre del documento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * firma para establecer el nombre del documento
     * @param nombre el nombre del documento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * firma para obtener el numero de version
     * @return numero de version
     */
    public Integer getNumeroVersion() {
        return numeroVersion;
    }

    /**
     * firma para aumentar en uno el numero de version
     */
    public void aumentarNumeroVersion() {
        this.numeroVersion++;
    }

    /**
     * firma para establecer el numero de version
     */
    public void setNumeroVersion(Integer numero) {
        this.numeroVersion = numero;
    }
}