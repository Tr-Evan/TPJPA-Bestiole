package org.example.bestioles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.bestioles.repository.PersonRepository;

import org.example.bestioles.model.Person;
import org.example.bestioles.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // Create
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    // Update
    public Person updatePerson(int id, Person updatedPerson) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setFirstname(updatedPerson.getFirstname());
            person.setLastname(updatedPerson.getLastname());
            person.setAge(updatedPerson.getAge());
            person.setAnimals(updatedPerson.getAnimals());
            return personRepository.save(person);
        } else {
            throw new IllegalArgumentException("Person not found with id: " + id);
        }
    }

    // Passe-plats
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> findPersonById(int id) {
        return personRepository.findById(id);
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }
}

