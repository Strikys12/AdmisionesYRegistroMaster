package com.admisionesYRegistro.Request.controllers;

import com.admisionesYRegistro.Request.models.RequestModel;
import com.admisionesYRegistro.Request.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Importar para códigos de estado
import org.springframework.http.ResponseEntity; // Importar para respuestas HTTP
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(origins = "http://127.0.0.1:5501")

public class RequestController {

    @Autowired
    private RequestService requestService;

    // 🛑 MÉTODO POST CORREGIDO PARA MANEJAR DOCUMENTO DUPLICADO (409)
    @PostMapping
    public ResponseEntity<?> saveRequest(@RequestBody RequestModel request){
        try {
            RequestModel nuevaSolicitud = this.requestService.saveRequest(request);

            // Éxito: Devuelve 201 Created (estándar REST para creación)
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSolicitud);
        } catch (IllegalStateException e) {
            // 🛑 ERROR DE CONFLICTO (DOCUMENTO DUPLICADO): Devuelve 409 Conflict
            // Se envía el mensaje de error del servicio en el cuerpo de la respuesta.
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            // Manejo genérico para otros errores de servidor
            System.err.println("Error al guardar la solicitud: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error interno al procesar la solicitud.\"}");
        }
    }

    @GetMapping
    public ArrayList<RequestModel> getRequests(){
        return this.requestService.getRequests();
    }

    @GetMapping(path = "/{id}")
    public Optional<RequestModel> getRequestById(@PathVariable("id") Long id){
        return this.requestService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public RequestModel updateRequestById(@RequestBody RequestModel request, @PathVariable("id") Long id){
        return this.requestService.updateById(request, id);
    }

    // Método DELETE actualizado para usar ResponseEntity para códigos HTTP claros
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        boolean ok = this.requestService.deleteRequest(id);

        if(ok){
            // Éxito: Devuelve 200 OK
            return ResponseEntity.status(HttpStatus.OK).body("Request with id " + id + " has been deleted");
        } else{
            // Error: Devuelve 404 Not Found (asumiendo que el error es no encontrar el ID)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, we have problems D:");
        }
    }
}