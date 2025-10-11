
package com.admisionesYRegistro.Request.controllers;


import com.admisionesYRegistro.Request.models.RequestModel;
import com.admisionesYRegistro.Request.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


import java.util.ArrayList;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(origins = "http://127.0.0.1:5501")

public class RequestController {
    @Autowired
    private RequestService requestService;


    @GetMapping
    public ArrayList<RequestModel> getRequests(){
        return this.requestService.getRequests();

    }

    @PostMapping
    public RequestModel saveRequest(@RequestBody RequestModel request){

        return this.requestService.saveRequest(request);
    }

    @GetMapping(path = "/{id}")
    public Optional<RequestModel> getRequestById(@PathVariable("id") Long id){
        return this.requestService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public RequestModel updateRequestById(@RequestBody RequestModel request, @PathVariable("id") Long id){
        return this.requestService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.requestService.deleteRequest(id);

        if(ok){
            return "Request with id " + id + "Has deleted";
        } else{
            return "Error, we have problems D:";
        }

    }
}
