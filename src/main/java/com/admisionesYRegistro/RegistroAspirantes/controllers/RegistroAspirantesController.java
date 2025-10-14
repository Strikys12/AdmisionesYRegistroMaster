package com.admisionesYRegistro.RegistroAspirantes.controllers;

import com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel;
import com.admisionesYRegistro.RegistroAspirantes.services.RegistroAspirantesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus; // 🛑 Importar HttpStatus
import org.springframework.http.ResponseEntity; // 🛑 Importar ResponseEntity
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
    public ResponseEntity<RegistroAspirantesModel> saveRegistro(@RequestBody RegistroAspirantesModel registro){
        try {
            RegistroAspirantesModel nuevoRegistro = this.registroService.saveRequest(registro);

            // Éxito: Devuelve 201 Created (estándar para la creación de recursos)
            return new ResponseEntity<>(nuevoRegistro, HttpStatus.CREATED);
        } catch (Exception e) {
            // Error: Devuelve 400 Bad Request o 500 Internal Server Error, según el caso.
            // Para simplificar, usamos 400.
            System.err.println("Error al guardar el registro y credenciales: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
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
