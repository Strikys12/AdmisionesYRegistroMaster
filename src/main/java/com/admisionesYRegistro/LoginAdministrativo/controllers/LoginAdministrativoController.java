package com.admisionesYRegistro.LoginAdministrativo.controllers;
import com.admisionesYRegistro.LoginAdministrativo.services.LoginAdministrativoServices;
import com.admisionesYRegistro.LoginAdministrativo.models.LoginAdministrativoModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/loginAdministrativos")
@CrossOrigin(origins = "http://127.0.0.1:5501")


public class LoginAdministrativoController {
    @Autowired

    private LoginAdministrativoServices loginAdministrativoService;


    @GetMapping
    public ArrayList<LoginAdministrativoModel> getRegistro(){
        return this.loginAdministrativoService.getRequests();

    }

    @PostMapping
    public LoginAdministrativoModel saveRegistro(@RequestBody LoginAdministrativoModel loginAdministrativo){

        return this.loginAdministrativoService.saveRequest(loginAdministrativo);
    }

    @GetMapping(path = "/{id}")
    public Optional<LoginAdministrativoModel> getRegistroById(@PathVariable("id") Long id){
        return this.loginAdministrativoService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public LoginAdministrativoModel updateRegistroById(@RequestBody LoginAdministrativoModel registro, @PathVariable("id") Long id){
        return this.loginAdministrativoService.updateById(registro, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.loginAdministrativoService.deleteRequest(id);

        if(ok){
            return "Request with id " + id + "Has deleted";
        } else{
            return "Error, we have problems:";
        }

    }
}
