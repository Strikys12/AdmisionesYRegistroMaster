package com.admisionesYRegistro.RegistroAspirantes.services;

import com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel;
import com.admisionesYRegistro.RegistroAspirantes.repositories.IRegistrarRepository; // Usamos tu repositorio IRegistrarRepository
import com.admisionesYRegistro.LoginEstudiantes.services.LoginEstudiantesServices;
import com.admisionesYRegistro.LoginEstudiantes.models.LoginEstudianteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RegistroAspirantesServices {

    @Autowired
    IRegistrarRepository registroAspiranteRepository; // Inyectamos IRegistrarRepository

    @Autowired
    private LoginEstudiantesServices loginEstudianteService;

    public ArrayList<RegistroAspirantesModel> getRequests(){
        return (ArrayList<RegistroAspirantesModel>) registroAspiranteRepository.findAll();
    }

    public RegistroAspirantesModel saveRequest(RegistroAspirantesModel registroAspirantes){

        // 🛑 PASO 1: VERIFICAR SI EL EMAIL YA EXISTE
        Optional<RegistroAspirantesModel> aspiranteExistente =
                registroAspiranteRepository.findByEmail(registroAspirantes.getEmail());

        if (aspiranteExistente.isPresent()) {
            // Si existe, lanzamos una excepción, deteniendo el registro y la creación de credenciales de login.
            throw new IllegalStateException("El correo electrónico ya se encuentra registrado.");
        }

        // 🛑 PASO 2: PROCEDER CON EL REGISTRO SOLO SI EL EMAIL ES NUEVO

        // 1. Guardar el registro completo del aspirante
        RegistroAspirantesModel registroGuardado = registroAspiranteRepository.save(registroAspirantes);

        // 2. Preparar el objeto para la tabla loginEstudiante (Solo si el registro fue exitoso)
        LoginEstudianteModel loginCredentials = new LoginEstudianteModel();
        loginCredentials.setEmail(registroAspirantes.getEmail());
        loginCredentials.setContrasena(registroAspirantes.getContrasena());

        // 3. Guardar las credenciales usando el servicio del otro módulo
        loginEstudianteService.saveLoginEstudiante(loginCredentials);

        return registroGuardado;
    }


    public Optional<RegistroAspirantesModel> getById(Long id){
        return registroAspiranteRepository.findById(id);
    }

    public RegistroAspirantesModel updateById(RegistroAspirantesModel registrar, Long id){
        RegistroAspirantesModel registrar1 = registroAspiranteRepository.findById(id).get();

        // Nota: Si permites la actualización de email, también deberías validar
        // que el nuevo email no exista antes de guardar. Por ahora, solo copiamos campos.
        registrar1.setTipoDoc(registrar.getTipoDoc());
        registrar1.setNumeroDoc(registrar.getNumeroDoc());
        registrar1.setNombres(registrar.getNombres());
        registrar1.setApellidos(registrar.getApellidos());
        registrar1.setEmail(registrar.getEmail());
        registrar1.setContrasena(registrar.getContrasena());
        // Se recomienda no guardar 'confirmarContrasena' en el modelo final si solo es para validación frontend.
        registrar1.setConfirmarContrasena(registrar.getConfirmarContrasena());


        return registroAspiranteRepository.save(registrar1);

    }

    public Boolean deleteRequest (Long id){
        try{
            registroAspiranteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;

        }
    }
}