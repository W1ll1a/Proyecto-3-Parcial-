package hn.edu.ujcv.p3.Proyecto3.repository.planilla;

import hn.edu.ujcv.p3.Proyecto3.entity.planilla.Planilla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanillaRepository extends JpaRepository<Planilla, Long> {
    Optional<Planilla> findByname (String name);
}
