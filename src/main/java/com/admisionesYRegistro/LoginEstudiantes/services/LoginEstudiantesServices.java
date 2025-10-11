package com.admisionesYRegistro.LoginEstudiantes.services;

import com.admisionesYRegistro.LoginEstudiantes.models.LoginEstudianteModel;
import com.admisionesYRegistro.LoginEstudiantes.repositories.ILoginEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service

public class LoginEstudiantesServices {
    @Autowired
    ILoginEstudianteRepository loginEstudianteRepository;


    public ArrayList<LoginEstudianteModel> getRequests(){
        return (ArrayList<LoginEstudianteModel>) loginEstudianteRepository.findAll();
    }

    public com.admisionesYRegistro.LoginEstudiantes.models.LoginEstudianteModel saveRequest(LoginEstudianteModel loginEstudiante){
        return loginEstudianteRepository.save(loginEstudiante);
    }

    public Optional<LoginEstudianteModel> getById(Long id){
        return loginEstudianteRepository.findById(id);
    }

    public LoginEstudianteModel updateById(LoginEstudianteModel login, Long id){
        LoginEstudianteModel login1 = loginEstudianteRepository.findById(id).get();

        login1.setEmail(login.getEmail());
        login1.setContrasena(login.getContrasena());



        return login1;

    }

    public Boolean deleteRequest (Long id){
        try{
            loginEstudianteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;

        }
    }


}
