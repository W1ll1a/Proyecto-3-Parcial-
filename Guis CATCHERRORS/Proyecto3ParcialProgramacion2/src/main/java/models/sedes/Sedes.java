package models.sedes;

public class Sedes {
    private long id;
    private String ciudad;
    private int cantidaddevehiculosensede;
    private String nombredirector;
    private String nombredesede;
    private String direccion;

    public Sedes (){
        super();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCantidaddevehiculosensede(int cantidaddevehiculosensede) {
        this.cantidaddevehiculosensede = cantidaddevehiculosensede;
    }

    public void setNombredesede(String nombredesede) {
        this.nombredesede = nombredesede;
    }

    public void setNombredirector(String nombredirector) {
        this.nombredirector = nombredirector;
    }

    public long getId() {
        return id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCantidaddevehiculosensede() {
        return cantidaddevehiculosensede;
    }

    public String getNombredesede() {
        return nombredesede;
    }

    public String getNombredirector() {
        return nombredirector;
    }

}

