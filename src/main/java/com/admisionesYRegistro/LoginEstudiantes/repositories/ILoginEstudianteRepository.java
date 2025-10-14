package com.admisionesYRegistro.LoginEstudiantes.repositories;

import com.admisionesYRegistro.LoginEstudiantes.models.LoginEstudianteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository

public interface ILoginEstudianteRepository extends JpaRepository<LoginEstudianteModel, Long> {
    Optional<LoginEstudianteModel> findByEmailAndContrasena(String email, String contrasena);

    //Optional<LoginEstudianteModel> findByEmail(String email);
}
