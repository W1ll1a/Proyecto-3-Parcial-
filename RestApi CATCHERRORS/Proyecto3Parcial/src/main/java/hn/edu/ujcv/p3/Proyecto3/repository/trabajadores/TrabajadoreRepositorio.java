package hn.edu.ujcv.p3.Proyecto3.repository.trabajadores;

import hn.edu.ujcv.p3.Proyecto3.entity.sedes.Sedes;
import hn.edu.ujcv.p3.Proyecto3.entity.trabajadores.Trabajadores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrabajadoreRepositorio extends JpaRepository<Trabajadores, Long> {
            Optional<Trabajadores> findBynumerotelefono(long numerotelefono);
}
