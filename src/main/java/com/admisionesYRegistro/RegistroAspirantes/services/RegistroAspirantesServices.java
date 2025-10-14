    package com.admisionesYRegistro.RegistroAspirantes.services;
    import com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel;
    import com.admisionesYRegistro.RegistroAspirantes.repositories.IRegistrarRepository;
    import com.admisionesYRegistro.LoginEstudiantes.services.LoginEstudiantesServices; // 🛑 Importar el servicio de login
    import com.admisionesYRegistro.LoginEstudiantes.models.LoginEstudianteModel; // 🛑 Importar el modelo de login
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.Optional;

    @Service
    public class RegistroAspirantesServices {

        @Autowired
        IRegistrarRepository registroAspiranteRepository;

        @Autowired // <-- DEBE ESTAR
        private LoginEstudiantesServices loginEstudianteService;

        public ArrayList<com.admisionesYRegistro.RegistroAspirantes.models.RegistroAspirantesModel> getRequests(){
            return (ArrayList<RegistroAspirantesModel>) registroAspiranteRepository.findAll();
        }

        public RegistroAspirantesModel saveRequest(RegistroAspirantesModel registroAspirantes){

            // 1. Guardar el registro completo del aspirante
            RegistroAspirantesModel registroGuardado = registroAspiranteRepository.save(registroAspirantes);

            // 2. Preparar el objeto para la tabla loginEstudiante
            LoginEstudianteModel loginCredentials = new LoginEstudianteModel();
            loginCredentials.setEmail(registroAspirantes.getEmail());
            loginCredentials.setContrasena(registroAspirantes.getContrasena());

            // 3. Guardar las credenciales usando el servicio del otro módulo
            loginEstudianteService.saveLoginEstudiante(loginCredentials);

            return registroGuardado;
        }


        public Optional<RegistroAspirantesModel> getById(Long id){
            return registroAspiranteRepository.findById(id);
        }

        public RegistroAspirantesModel updateById(RegistroAspirantesModel registrar, Long id){
            RegistroAspirantesModel registrar1 = registroAspiranteRepository.findById(id).get();

            registrar1.setTipoDoc(registrar.getTipoDoc());
            registrar1.setNumeroDoc(registrar.getNumeroDoc());
            registrar1.setNombres(registrar.getNombres());
            registrar1.setApellidos(registrar.getApellidos());
            registrar1.setEmail(registrar.getEmail());
            registrar1.setContrasena(registrar.getContrasena());
            registrar1.setConfirmarContrasena(registrar.getConfirmarContrasena());


            return registroAspiranteRepository.save(registrar1);

        }

        public Boolean deleteRequest (Long id){
            try{
                registroAspiranteRepository.deleteById(id);
                return true;
            }catch (Exception e){
                return false;

            }
        }
    }




