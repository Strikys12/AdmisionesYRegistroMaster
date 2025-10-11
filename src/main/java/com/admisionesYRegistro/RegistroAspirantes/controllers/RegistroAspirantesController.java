package com.admisionesYRegistro.RegistroAspirantes.controllers;

import com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel;
import com.admisionesYRegistro.RegistroAspirantes.services.RegistroAspirantesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/registroAspirantes")
@CrossOrigin(origins = "http://127.0.0.1:5501")

public class RegistroAspirantesController {

    @Autowired
    private RegistroAspirantesServices registroService;


    @GetMapping
    public ArrayList<RegistroAspirantesModel> getRegistro(){
        return this.registroService.getRequests();

    }

    @PostMapping
    public RegistroAspirantesModel saveRegistro(@RequestBody RegistroAspirantesModel registro){

        return this.registroService.saveRequest(registro);
    }

    @GetMapping(path = "/{id}")
    public Optional<RegistroAspirantesModel> getRegistroById(@PathVariable("id") Long id){
        return this.registroService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public RegistroAspirantesModel updateRegistroById(@RequestBody RegistroAspirantesModel registro, @PathVariable("id") Long id){
        return this.registroService.updateById(registro, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.registroService.deleteRequest(id);

        if(ok){
            return "Request with id " + id + "Has deleted";
        } else{
            return "Error, we have problems D:";
        }

    }

}
