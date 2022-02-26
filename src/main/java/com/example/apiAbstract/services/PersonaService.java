package com.example.apiAbstract.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import com.example.apiAbstract.repositories.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import com.example.apiAbstract.models.Persona;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    public ArrayList<Persona> getPersonas(){
        return (ArrayList<Persona>) personaRepository.findAll();
    }

    public Persona savePersona(Persona persona){
        return personaRepository.save(persona);
    }

    public Optional<Persona> findById(int id){
        return personaRepository.findById(id);
    }


    public boolean deletePersona(int id){
        try{
            personaRepository.deleteById(id);
            return true;
        } catch(Exception err){
            return false;
        }
    }

    public String patch(int id, Map<Object, Object> fields){

        if (personaRepository.existsById(id)){
            fields.forEach((key, value) -> { 
                Field field = ReflectionUtils.findRequiredField(Persona.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, personaRepository.findById(id).get(), value);
            });
            Persona pAux = personaRepository.findById(id).get();
            personaRepository.save(pAux);

            return "Cambio efectuado";
        }
        return "Cambio no efectuado";
    }

    public ArrayList<Persona> findByApellido(String letters) {
        return personaRepository.findByApellido(letters);
    }
}
