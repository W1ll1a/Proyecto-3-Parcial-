package hn.edu.ujcv.p3.Proyecto3.repository.vehiculos;

import hn.edu.ujcv.p3.Proyecto3.entity.sedes.Sedes;
import hn.edu.ujcv.p3.Proyecto3.entity.vehiculos.Vehiculos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculosRepositorio extends JpaRepository<Vehiculos,Long> {
            Optional<Vehiculos> findByplaca(String placa);
}
