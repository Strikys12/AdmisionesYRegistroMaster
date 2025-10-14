package com.admisionesYRegistro.RegistroAspirantes.controllers;

import com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel;
import com.admisionesYRegistro.RegistroAspirantes.services.RegistroAspirantesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/registroAspirantes")
// Nota: La configuración CORS global en SecurityConfig es preferible,
// pero mantenemos @CrossOrigin aquí si no tienes esa configuración global.
@CrossOrigin(origins = "http://127.0.0.1:5501")

public class RegistroAspirantesController {

    @Autowired
    private RegistroAspirantesServices registroService;


    @GetMapping
    public ArrayList<RegistroAspirantesModel> getRegistro(){
        return this.registroService.getRequests();

    }

    // 🛑 MÉTODO POST CORREGIDO PARA MANEJAR EMAIL DUPLICADO (409)
    @PostMapping
    public ResponseEntity<?> saveRegistro(@RequestBody RegistroAspirantesModel registro){
        try {
            RegistroAspirantesModel nuevoRegistro = this.registroService.saveRequest(registro);

            // Éxito: Devuelve 201 Created (estándar para la creación de recursos)
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
        } catch (IllegalStateException e) {
            // 🛑 ERROR DE CONFLICTO (EMAIL DUPLICADO): Devuelve 409 Conflict
            // El mensaje de error del Servicio se envía en el cuerpo.
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            // Manejo genérico para otros errores de servidor
            System.err.println("Error al guardar el registro y credenciales: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error interno al registrar el aspirante.\"}");
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