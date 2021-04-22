package models.trabajadores;

public class Trabajadores {
    private long id ;
    private int numerotelefono;
    private String nombre;
    private String fechanacimiento;
    private String puesto;
    private String fechainicio;
     public Trabajadores(){
         super();
     }

    public void setNumerotelefono(int numerotelefono) {
        this.numerotelefono = numerotelefono;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public int getNumerotelefono() {
        return numerotelefono;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getFechainicio() {
        return fechainicio;
    }
}
