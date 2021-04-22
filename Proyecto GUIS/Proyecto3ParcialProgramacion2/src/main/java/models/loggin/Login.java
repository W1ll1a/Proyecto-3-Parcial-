package models.loggin;

public class Login {
    private long id;
    private String usuario;
    private String clave;
    private String email;
    private String  fechanacimiento;
    private int numerotelefono;

    public Login (){
        super();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setNumerotelefono(int numerotelefono) {
        this.numerotelefono = numerotelefono;
    }

    public long getId() {
        return id;
    }

    public int getNumerotelefono() {
        return numerotelefono;
    }

    public String getClave() {
        return clave;
    }

    public String getEmail() {
        return email;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public String getUsuario() {
        return usuario;
    }
}
