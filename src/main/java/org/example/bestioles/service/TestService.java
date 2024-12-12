package org.example.bestioles.service;

import org.example.bestioles.model.Animal;
import org.example.bestioles.model.Person;
import org.example.bestioles.model.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.bestioles.repository.AnimalRepository;
import org.example.bestioles.repository.PersonRepository;
import org.example.bestioles.repository.SpeciesRepository;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public void testQueries() {
        // Requête sur Species
        Species species = speciesRepository.findFirstByCommonName("Chien");
        System.out.println("First Species: " + species);

        List<Species> latinSpecies = speciesRepository.findByLatinNameContainingIgnoreCase("canis");
        System.out.println("Latin Species: " + latinSpecies);

        // Requête sur Person
        List<Person> personsByName = personRepository.findByLastnameOrFirstname("Dupont", "Jean");
        System.out.println("Persons by Name: " + personsByName);

        List<Person> personsByAge = personRepository.findByAgeGreaterThanEqual(30);
        System.out.println("Persons by Age: " + personsByAge);

        // Requête sur Animal
        Species dogSpecies = speciesRepository.findFirstByCommonName("Chien");
        List<Animal> animalsBySpecies = animalRepository.findBySpecies(dogSpecies);
        System.out.println("Animals by Species: " + animalsBySpecies);

        List<Animal> animalsByColor = animalRepository.findByColorIn(List.of("Marron", "Blanc"));
        System.out.println("Animals by Color: " + animalsByColor);
    }
}
