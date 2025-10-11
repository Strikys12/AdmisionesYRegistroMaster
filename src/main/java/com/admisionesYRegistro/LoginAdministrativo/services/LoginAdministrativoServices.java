package com.admisionesYRegistro.LoginAdministrativo.services;


import com.admisionesYRegistro.LoginAdministrativo.models.LoginAdministrativoModel;
import com.admisionesYRegistro.LoginAdministrativo.repositories.ILoginAdministrativo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class LoginAdministrativoServices {
    @Autowired
      ILoginAdministrativo loginAdministrativoRepository;


    public ArrayList<LoginAdministrativoModel> getRequests(){
        return (ArrayList<LoginAdministrativoModel>) loginAdministrativoRepository.findAll();
    }

    public com.admisionesYRegistro.LoginAdministrativo.models.LoginAdministrativoModel saveRequest(LoginAdministrativoModel loginAdministrativo){
        return loginAdministrativoRepository.save(loginAdministrativo);
    }

    public Optional<LoginAdministrativoModel> getById(Long id){
        return loginAdministrativoRepository.findById(id);
    }

    public LoginAdministrativoModel updateById(LoginAdministrativoModel login, Long id){
        LoginAdministrativoModel login1 = loginAdministrativoRepository.findById(id).get();

        login1.setEmail(login.getEmail());
        login1.setContrasena(login.getContrasena());



        return login1;

    }

    public Boolean deleteRequest (Long id){
        try{
            loginAdministrativoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;

        }
    }
}
