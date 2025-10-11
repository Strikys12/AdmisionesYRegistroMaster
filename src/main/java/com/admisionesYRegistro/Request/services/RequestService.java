package com.admisionesYRegistro.Request.services;
import com.admisionesYRegistro.Request.models.RequestModel;
import com.admisionesYRegistro.Request.repositories.IRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    IRequestRepository requestRepository;

    public ArrayList<com.admisionesYRegistro.Request.models.RequestModel> getRequests(){
        return (ArrayList<RequestModel>) requestRepository.findAll();
    }

    public com.admisionesYRegistro.Request.models.RequestModel saveRequest(RequestModel request){
        return requestRepository.save(request);
    }

    public Optional<RequestModel> getById(Long id){
        return requestRepository.findById(id);
    }

    public RequestModel updateById(RequestModel request, Long id){
        RequestModel request1 = requestRepository.findById(id).get();

        request1.setTipoDoc(request.getTipoDoc());
        request1.setNumeroDoc(request.getNumeroDoc());
        request1.setNombres(request.getNombres());
        request1.setApellidos(request.getApellidos());
        request1.setFechaExpedicion(request.getFechaExpedicion());
        request1.setPaisExpedicion(request.getPaisExpedicion());
        request1.setDepartamentoExpedicion(request.getDepartamentoExpedicion());
        request1.setCiudadExpedicion(request.getCiudadExpedicion());
        request1.setGenero(request.getGenero());
        request1.setEstadoCivil(request.getEstadoCivil());
        request1.setPaisNacimiento(request.getPaisNacimiento());
        request1.setDepartamentoNacimiento(request.getDepartamentoNacimiento());
        request1.setFechaNacimiento(request.getFechaNacimiento());
        request1.setCiudadNacimiento(request.getCiudadNacimiento());
        request1.setIndicativoPais(request.getIndicativoPais());
        request1.setNumeroCelular(request.getNumeroCelular());
        request1.setPrimerPrograma(request.getPrimerPrograma());
        request1.setSegundoPrograma(request.getSegundoPrograma());

        return request1;

    }

    public Boolean deleteRequest (Long id){
        try{
            requestRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;

        }
    }
}
