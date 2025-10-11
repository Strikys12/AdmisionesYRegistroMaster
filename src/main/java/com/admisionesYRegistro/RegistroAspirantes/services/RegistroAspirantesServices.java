package com.admisionesYRegistro.RegistroAspirantes.services;
import com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel;
import com.admisionesYRegistro.RegistroAspirantes.repositories.IRegistrarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RegistroAspirantesServices {

    @Autowired
    IRegistrarRepository registroAspiranteRepository;


    public ArrayList<com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel> getRequests(){
        return (ArrayList<RegistroAspirantesModel>) registroAspiranteRepository.findAll();
    }

    public com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel saveRequest(RegistroAspirantesModel registroAspirantes){
        return registroAspiranteRepository.save(registroAspirantes);
    }

    public Optional<RegistroAspirantesModel> getById(Long id){
        return registroAspiranteRepository.findById(id);
    }

    public RegistroAspirantesModel updateById(RegistroAspirantesModel registrar, Long id){
        RegistroAspirantesModel registrar1 = registroAspiranteRepository.findById(id).get();

        registrar1.setTipoDoc(registrar.getTipoDoc());
        registrar1.setNumeroDoc(registrar.getNumeroDoc());
        registrar1.setNombres(registrar.getNombres());
        registrar1.setApellidos(registrar.getApellidos());
        registrar1.setEmail(registrar.getEmail());
        registrar1.setContrasena(registrar.getContrasena());
        registrar1.setConfirmarContrasena(registrar.getConfirmarContrasena());


        return registrar1;

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




