package hn.edu.ujcv.p3.Proyecto3.entity.registro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Registro")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long         id;
    private String vehiculo;
    private String fechasalida;
    private String fechaentrada;
    private String fechamaximaentrega;
    private int    cantidadproducto;
}
