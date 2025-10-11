package com.admisionesYRegistro.LoginEstudiantes.controllers;

import com.admisionesYRegistro.LoginEstudiantes.models.LoginEstudianteModel;
import com.admisionesYRegistro.LoginEstudiantes.services.LoginEstudiantesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/loginEstudiantes")
@CrossOrigin(origins = "http://127.0.0.1:5501")

public class LoginEstudiantesController {
    @Autowired
    private LoginEstudiantesServices loginEstudianteService;


    @GetMapping
    public ArrayList<LoginEstudianteModel> getRegistro(){
        return this.loginEstudianteService.getRequests();

    }

    @PostMapping
    public LoginEstudianteModel saveRegistro(@RequestBody LoginEstudianteModel loginEstudiante){

        return this.loginEstudianteService.saveRequest(loginEstudiante);
    }

    @GetMapping(path = "/{id}")
    public Optional<LoginEstudianteModel> getRegistroById(@PathVariable("id") Long id){
        return this.loginEstudianteService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public LoginEstudianteModel updateRegistroById(@RequestBody LoginEstudianteModel registro, @PathVariable("id") Long id){
        return this.loginEstudianteService.updateById(registro, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.loginEstudianteService.deleteRequest(id);

        if(ok){
            return "Request with id " + id + "Has deleted";
        } else{
            return "Error, we have problems:";
        }

    }
}
