package hn.edu.ujcv.p3.Proyecto3.repository.registro;

import hn.edu.ujcv.p3.Proyecto3.entity.registro.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistroRepositorio extends JpaRepository<Registro ,Long> {
    Optional<Registro> findByvehiculo(String vehiculo);
}
