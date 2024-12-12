package org.example.bestioles;


import org.example.bestioles.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.bestioles.repository.PersonRepository;

import java.util.List;

@SpringBootTest
public class CustomPersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testDeleteAllWithoutAnimals() {
        //Ajout des personnes avec et sans animaux
        Person personWithoutAnimal = new Person();
        personWithoutAnimal.setFirstname("NoAnimal");
        personWithoutAnimal.setLastname("Person");
        personWithoutAnimal.setAge(30);

        Person personWithAnimal = new Person();
        personWithAnimal.setFirstname("WithAnimal");
        personWithAnimal.setLastname("Person");
        personWithAnimal.setAge(25);

        personRepository.save(personWithoutAnimal);
        personRepository.save(personWithAnimal);

        //  Supprimer les personnes sans animaux
        personRepository.deleteAllWithoutAnimals();

        List<Person> remainingPersons = personRepository.findAll();
        Assertions.assertFalse(remainingPersons.isEmpty(), "La liste ne devrait pas être vide");
        Assertions.assertTrue(
                remainingPersons.stream().allMatch(person -> person.getAnimals() != null && !person.getAnimals().isEmpty()),
                "Toutes les personnes restantes doivent avoir des animaux"
        );
    }

    @Test
    void testGenerateRandomPersons() {
        long initialCount = personRepository.count();

        personRepository.generateRandomPersons(10);

        long finalCount = personRepository.count();
        Assertions.assertEquals(initialCount + 10, finalCount, "10 personnes doivent être ajoutées");

        List<Person> newPersons = personRepository.findAll();
        Assertions.assertTrue(
                newPersons.stream().anyMatch(person -> person.getFirstname().startsWith("Firstname")),
                "Au moins une personne doit avoir un prénom commençant par 'Firstname'"
        );
        Assertions.assertTrue(
                newPersons.stream().anyMatch(person -> person.getLastname().startsWith("Lastname")),
                "Au moins une personne doit avoir un nom de famille commençant par 'Lastname'"
        );
    }
}