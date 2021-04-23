package hn.edu.ujcv.p3.Proyecto3.entity.trabajadores;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Trabajadores")
public class Trabajadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long        id;
        private long        numerotelefono;
    private String          nombre;
    private String          fechanacimiento;
    private String          puesto;
    private String          fechainicio;

}
