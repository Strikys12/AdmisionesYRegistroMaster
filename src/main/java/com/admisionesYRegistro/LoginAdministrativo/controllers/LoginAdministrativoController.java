package com.admisionesYRegistro.LoginAdministrativo.controllers;

import com.admisionesYRegistro.LoginAdministrativo.services.LoginAdministrativoServices;
import com.admisionesYRegistro.LoginAdministrativo.models.LoginAdministrativoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity; // Para respuestas HTTP 200/401
import org.springframework.http.HttpStatus;   // Para códigos de estado

import java.util.Optional;
import java.util.ArrayList;

// 🛑 CLASE AUXILIAR: DTO para recibir solo email y contrasena
class LoginRequest {
    private String email;
    private String contrasena;

    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}


@RestController
// 🛑 URL BASE CORREGIDA: Coincide con el URL_API_ADMIN de tu JavaScript.
@RequestMapping("/api/loginAdministrativo")
public class LoginAdministrativoController {
    @Autowired
    private LoginAdministrativoServices loginAdministrativoService;


    // -----------------------------------------------------

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginRequest loginRequest) {

        // Llama al servicio para validar ambas credenciales
        boolean esValido = loginAdministrativoService.validarCredenciales(
                loginRequest.getEmail(),
                loginRequest.getContrasena()
        );

        if (esValido) {
            // Éxito: Devuelve 200 OK
            return ResponseEntity.ok().body("{\"message\": \"Login de administrador exitoso\"}");
        } else {
            // Fracaso: Devuelve 401 Unauthorized (No autorizado)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Credenciales de administrador inválidas\"}");
        }
    }
    // -----------------------------------------------------


    // Métodos CRUD estándar (mantenidos para otras operaciones)

    @GetMapping
    public ArrayList<LoginAdministrativoModel> getRegistro(){
        return this.loginAdministrativoService.getRequests();
    }

    // El método POST original para 'saveRegistro' fue retirado ya que el admin se crea manualmente.
    // Si necesitas reactivarlo, puedes agregarlo aquí.

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
