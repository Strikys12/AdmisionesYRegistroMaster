package com.admisionesYRegistro.RegistroAspirantes.repositories;
import com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRegistrarRepository extends JpaRepository<RegistroAspirantesModel, Long>{


    Optional<RegistroAspirantesModel> findByEmail(String email);

}
