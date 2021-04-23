package models.ticket;

public class Ticket {
    private long id ;
    private String cliente;
    private int telefono;
    private String producto;
    private int cantidad;
    private String fechaentrega;

    public Ticket(){
        super();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setFechaentrega(String fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public long getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getCliente() {
        return cliente;
    }

    public String getFechaentrega() {
        return fechaentrega;
    }

    public String getProducto() {
        return producto;
    }
}
