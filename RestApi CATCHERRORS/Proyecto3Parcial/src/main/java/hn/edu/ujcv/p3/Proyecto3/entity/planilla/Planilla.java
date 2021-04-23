package hn.edu.ujcv.p3.Proyecto3.entity.planilla;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Planilla")
public class Planilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long        id;
    private String      name;
    private double      totalporhora;
    private double      totalpagar;
    private int         horastrabajadas;
    private int         horasextra;

}


