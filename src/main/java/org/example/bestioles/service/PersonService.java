package org.example.bestioles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.bestioles.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void testCustomMethods() {
        personRepository.generateRandomPersons(5);
        personRepository.deleteAllWithoutAnimals();
    }
}
