package models.registros;

public class Registros {
        private long id;
        private String vehiculo;
        private String fechasalida;
        private String fechaentrada;
        private String fechamaximaentrega;
        private int cantidadproducto;
         public Registros(){
             super();
         }

    public void setId(long id) {
        this.id = id;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setCantidadproducto(int cantidadproducto) {
        this.cantidadproducto = cantidadproducto;
    }

    public void setFechaentrada(String fechaentrada) {
        this.fechaentrada = fechaentrada;
    }

    public void setFechamaximaentrega(String fechamaximaentrega) {
        this.fechamaximaentrega = fechamaximaentrega;
    }

    public void setFechasalida(String fechasalida) {
        this.fechasalida = fechasalida;
    }

    public long getId() {
        return id;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public int getCantidadproducto() {
        return cantidadproducto;
    }

    public String getFechaentrada() {
        return fechaentrada;
    }

    public String getFechamaximaentrega() {
        return fechamaximaentrega;
    }

    public String getFechasalida() {
        return fechasalida;
    }
}
