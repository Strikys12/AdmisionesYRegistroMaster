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
    private ILoginAdministrativo loginAdministrativoRepository;


    public ArrayList<LoginAdministrativoModel> getRequests(){
        return (ArrayList<LoginAdministrativoModel>) loginAdministrativoRepository.findAll();
    }


    // public com.admisionesYRegistro.LoginAdministrativo.models.LoginAdministrativoModel saveRequest(LoginAdministrativoModel loginAdministrativo){
    //     return loginAdministrativoRepository.save(loginAdministrativo);
    // }

    public Optional<LoginAdministrativoModel> getById(Long id){
        return loginAdministrativoRepository.findById(id);
    }

    public LoginAdministrativoModel updateById(LoginAdministrativoModel login, Long id){
        LoginAdministrativoModel login1 = loginAdministrativoRepository.findById(id).get();

        login1.setEmail(login.getEmail());
        login1.setContrasena(login.getContrasena());

        return loginAdministrativoRepository.save(login1); // Persiste los cambios en la DB
    }

    public Boolean deleteRequest (Long id){
        try{
            loginAdministrativoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Valida las credenciales de un administrador.
     * Busca por email y compara la contraseña directamente (asumiendo texto plano en la DB).
     * @param email El correo electrónico proporcionado.
     * @param contrasena La contraseña proporcionada.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean validarCredenciales(String email, String contrasena) {

        // 1. Buscar al administrador por email.
        Optional<LoginAdministrativoModel> adminOptional = loginAdministrativoRepository.findByEmail(email);

        if (adminOptional.isEmpty()) {
            System.out.println("Intento de login fallido (Admin): Email no encontrado - " + email);
            return false;
        }

        LoginAdministrativoModel admin = adminOptional.get();

        // 2. Verificar la contraseña directamente contra el valor almacenado en la DB
        if (contrasena.equals(admin.getContrasena())) {
            System.out.println("Login de Administrador exitoso: " + email);
            return true;
        } else {
            System.out.println("Intento de login fallido (Admin): Contraseña incorrecta para - " + email);
            return false;
        }
    }
}