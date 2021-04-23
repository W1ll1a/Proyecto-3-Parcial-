package hn.edu.ujcv.p3.Proyecto3.repository.sedes;

import hn.edu.ujcv.p3.Proyecto3.entity.sedes.Sedes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SedesRepositorio extends JpaRepository<Sedes,Long> {
        Optional<Sedes> findBynombredesede(String sede );
    }

