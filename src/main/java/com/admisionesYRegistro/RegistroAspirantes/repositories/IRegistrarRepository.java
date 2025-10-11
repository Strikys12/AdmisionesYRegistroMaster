package com.admisionesYRegistro.RegistroAspirantes.repositories;
import com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegistrarRepository extends JpaRepository<RegistroAspirantesModel, Long>{
}

