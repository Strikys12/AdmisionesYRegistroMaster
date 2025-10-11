package com.admisionesYRegistro.LoginEstudiantes.repositories;

import com.admisionesYRegistro.LoginEstudiantes.models.LoginEstudianteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ILoginEstudianteRepository extends JpaRepository<LoginEstudianteModel, Long> {


}
