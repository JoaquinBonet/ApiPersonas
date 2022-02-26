package com.example.apiAbstract.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.apiAbstract.services.PersonaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;

import java.util.ArrayList;
import java.util.Optional;

import com.example.apiAbstract.models.Persona;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService pService;

    @GetMapping()
    public ArrayList<Persona> getPersonas(){
        return pService.getPersonas();
    }

    @PostMapping()
    public Persona savePersona(@RequestBody Persona persona){
        return this.pService.savePersona(persona);
    }

    @GetMapping( path = "/{id}")
    public Optional<Persona> getPersonaById(@PathVariable("id") int id){
        return this.pService.findById(id);
    }

    @GetMapping(path = "/query")
    public ArrayList<Persona> getPersonasByApellido(@RequestParam("apellido") String apellido){
      
        return this.pService.findByApellido(apellido);
    }

    @PatchMapping( value = "/{id}")
    public String patch(@PathVariable int id, @RequestBody Map<Object, Object> fields){
        return pService.patch(id, fields);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        boolean valid = this.pService.deletePersona(id);
        if (valid){
            return "Eliminada persona con id " + id;

        } else {
            return "No se pudo eliminar la persona con id " + id;
        }
    }
    
}
