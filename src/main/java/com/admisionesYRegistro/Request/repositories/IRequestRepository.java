package com.admisionesYRegistro.Request.repositories;

import com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel;
import com.admisionesYRegistro.Request.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRequestRepository extends JpaRepository<RequestModel, Long> {

    Optional<RequestModel> findBynumeroDoc(String numeroDoc);
}
