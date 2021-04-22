package hn.edu.ujcv.p3.Proyecto3.repository.login;

import hn.edu.ujcv.p3.Proyecto3.entity.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByusuario(String usuario);
}
