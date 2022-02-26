package com.example.apiAbstract;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.example.apiAbstract.models.Persona;
import com.example.apiAbstract.services.PersonaService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class ApiAbstractApplicationTests {

	@Test
	void PersonaServiceTest() {
		PersonaService pService = new PersonaService();
		Persona p = new Persona();
		pService.savePersona(p);
		int id = p.getId();
		Optional<Persona> aux = pService.findById(id);
		
		assertTrue(aux.isPresent(), "Case 1 test failed");		
		pService.deletePersona(id);
		assertFalse(aux.isPresent(), "Case 2 test failed");

	}

}
