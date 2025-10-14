package com.admisionesYRegistro.LoginEstudiantes.services;

import com.admisionesYRegistro.LoginEstudiantes.models.LoginEstudianteModel;
import com.admisionesYRegistro.LoginEstudiantes.repositories.ILoginEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder; // Necesario para la seguridad

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LoginEstudiantesServices {

    @Autowired
    ILoginEstudianteRepository loginEstudianteRepository;

    // Inyección del encriptador para la validación (login) y el guardado (update)
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    /**:
     * Usa findByEmail y luego compara la contraseña plana con el hash de la BD.
     */

    public LoginEstudianteModel saveLoginEstudiante(LoginEstudianteModel loginEstudiante){
        // En el modo inseguro, guarda la contraseña en texto plano
        return loginEstudianteRepository.save(loginEstudiante);
    }

    public LoginEstudianteModel validateLogin(LoginEstudianteModel loginEstudiante){

        // 1. Buscar por email y contraseña PLANA
        Optional<LoginEstudianteModel> estudiante =
                loginEstudianteRepository.findByEmailAndContrasena(
                        loginEstudiante.getEmail(),
                        loginEstudiante.getContrasena() // Usando la contraseña plana
                );

        if (estudiante.isEmpty()) {
            // El mensaje de error será el mismo, pero ahora sabemos que la BD no encontró la combinación.
            throw new RuntimeException("Credenciales incorrectas o usuario no registrado. 🚫");
        }

        return estudiante.get(); // Login exitoso
    }


    public ArrayList<LoginEstudianteModel> getLogin(){
        return (ArrayList<LoginEstudianteModel>) loginEstudianteRepository.findAll();
    }


    public Optional<LoginEstudianteModel> getById(Long id){
        return loginEstudianteRepository.findById(id);
    }

    /**
     * Actualiza el registro, asegurándose de ENCRIPTAR la contraseña nueva
     * y PERSISTIR los cambios.
     */
    public LoginEstudianteModel updateById(LoginEstudianteModel login, Long id){
        LoginEstudianteModel login1 = loginEstudianteRepository.findById(id).get();

        login1.setEmail(login.getEmail());

        // 🛑 CAMBIO NECESARIO: Guardar la contraseña en texto plano, sin encriptar.
        login1.setContrasena(login.getContrasena());

        // Guardar el objeto modificado en el repositorio para persistir.
        return loginEstudianteRepository.save(login1);
    }

    public Boolean deleteLogin (Long id){
        try{
            loginEstudianteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}