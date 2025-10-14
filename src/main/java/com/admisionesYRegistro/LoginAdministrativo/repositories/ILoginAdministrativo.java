package com.admisionesYRegistro.LoginAdministrativo.repositories;
import com.admisionesYRegistro.LoginAdministrativo.models.LoginAdministrativoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILoginAdministrativo extends JpaRepository<LoginAdministrativoModel, Long>{

    /**
     * Busca un registro de administrador en la base de datos por su correo electrónico.
     * Es crucial para el proceso de validación del login.
     * @param email El correo electrónico del administrador.
     * @return Un Optional que contiene el LoginAdministrativoModel si se encuentra.
     */
    Optional<LoginAdministrativoModel> findByEmail(String email);
}
