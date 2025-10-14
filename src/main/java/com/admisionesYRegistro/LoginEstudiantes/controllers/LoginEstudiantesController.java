package com.admisionesYRegistro.LoginEstudiantes.controllers;

import com.admisionesYRegistro.LoginEstudiantes.models.LoginEstudianteModel;
import com.admisionesYRegistro.LoginEstudiantes.services.LoginEstudiantesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/loginEstudiantes")
@CrossOrigin(origins = "http://127.0.0.1:5501")

public class LoginEstudiantesController {
    @Autowired
    private LoginEstudiantesServices loginEstudianteService;


    @PostMapping
    public ResponseEntity<?> loginEstudiante(@RequestBody LoginEstudianteModel loginEstudiante){
        try {

            LoginEstudianteModel estudianteLogueado = this.loginEstudianteService.validateLogin(loginEstudiante);

            // Login exitoso: Devuelve 200 OK.
            return new ResponseEntity<>(estudianteLogueado, HttpStatus.OK);

        } catch (RuntimeException e) {
            // Login fallido (Credenciales incorrectas): Devuelve 401 Unauthorized.
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ArrayList<LoginEstudianteModel> getLogin(){
        return this.loginEstudianteService.getLogin();

    }




    @GetMapping(path = "/{id}")
    public Optional<LoginEstudianteModel> getById(@PathVariable("id") Long id){
        return this.loginEstudianteService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public LoginEstudianteModel updateById(@RequestBody LoginEstudianteModel registro, @PathVariable("id") Long id){
        return this.loginEstudianteService.updateById(registro, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.loginEstudianteService.deleteLogin(id);

        if(ok){
            return "Request with id " + id + "Has deleted";
        } else{
            return "Error, we have problems:";
        }

    }
}
