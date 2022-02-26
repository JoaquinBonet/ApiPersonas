package com.example.apiAbstract.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


import com.example.apiAbstract.models.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    @Query(value = "SELECT * from personas where apellido like :letters%", nativeQuery = true)
    public abstract ArrayList<Persona> findByApellido(@Param("letters") String letters);
    
    
}
