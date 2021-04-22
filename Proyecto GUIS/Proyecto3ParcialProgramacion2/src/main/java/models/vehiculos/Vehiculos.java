package models.vehiculos;

public class Vehiculos {
    private long id ;
    private String tipodevehiculo;
    private String marcavehiculo;
    private String placa;
    private String color;
    private String estado;

    public Vehiculos (){
        super();

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMarcavehiculo(String marcavehiculo) {
        this.marcavehiculo = marcavehiculo;
    }

    public void setTipodevehiculo(String tipodevehiculo) {
        this.tipodevehiculo = tipodevehiculo;
    }

    public String getEstado() {
        return estado;
    }

    public long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarcavehiculo() {
        return marcavehiculo;
    }

    public String getTipodevehiculo() {
        return tipodevehiculo;
    }

}
