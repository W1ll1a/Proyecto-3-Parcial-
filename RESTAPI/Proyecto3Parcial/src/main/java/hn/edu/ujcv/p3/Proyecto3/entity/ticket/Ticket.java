package hn.edu.ujcv.p3.Proyecto3.entity.ticket;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String cliente;
    private int telefono;
    private String producto;
    private int cantidad;
    private String fechaentrega;



}
