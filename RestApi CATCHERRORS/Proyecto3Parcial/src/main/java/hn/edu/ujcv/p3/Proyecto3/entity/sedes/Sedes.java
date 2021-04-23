package hn.edu.ujcv.p3.Proyecto3.entity.sedes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sedes")
public class Sedes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long            id;
    private String      ciudad;
    private  int        cantidaddevehiculosensede;
    private String      Nombredirector;
    private String      nombredesede;
    private String      direccion;

}
