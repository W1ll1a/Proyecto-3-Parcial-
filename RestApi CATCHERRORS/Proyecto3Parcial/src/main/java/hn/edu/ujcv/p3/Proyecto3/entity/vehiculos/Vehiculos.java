package hn.edu.ujcv.p3.Proyecto3.entity.vehiculos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vehiculos")
public class Vehiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipodevehiculo;
    private String marcavehiculo;
    private String placa;
    private String color;
    private String estado;

}
