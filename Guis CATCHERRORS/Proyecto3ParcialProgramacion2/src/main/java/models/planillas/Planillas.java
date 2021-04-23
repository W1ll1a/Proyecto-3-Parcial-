package models.planillas;

public class Planillas {
    private long id;
    private String name;
    private double totalporhora;
    private double totalpagar;
    private int horastrabajadas;
    private int horasextra;

    public Planillas(){
        super();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHorasextra(int horasextra) {
        this.horasextra = horasextra;
    }

    public void setHorastrabajadas(int horastrabajadas) {
        this.horastrabajadas = horastrabajadas;
    }

    public void setTotalpagar(double totalpagar) {
        this.totalpagar = totalpagar;
    }

    public void setTotalporhora(double totalporhora) {
        this.totalporhora = totalporhora;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public double getTotalpagar() {
        return totalpagar;
    }

    public double getTotalporhora() {
        return totalporhora;
    }

    public int getHorasextra() {
        return horasextra;
    }

    public int getHorastrabajadas() {
        return horastrabajadas;
    }

}
