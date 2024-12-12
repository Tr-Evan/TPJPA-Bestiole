package org.example.bestioles;

import org.example.bestioles.model.Animal;
import org.example.bestioles.model.Person;
import org.example.bestioles.model.Species;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.bestioles.repository.AnimalRepository;
import org.example.bestioles.repository.PersonRepository;
import org.example.bestioles.repository.SpeciesRepository;

import java.util.List;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void testQueries() {

            // TP4
        // Requête sur Species
        Species species = speciesRepository.findFirstByCommonName("Chien");
        System.out.println("First Species: " + species);

        List<Species> latinSpecies = speciesRepository.findByLatinNameContainingIgnoreCase("canis");
        System.out.println("Latin Species: " + latinSpecies);

        // Requête sur Person
        List<Person> personsByName = personRepository.findByLastNameOrFirstName("Dupont", "Jean");
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

            // TP5
        @Test
        void testFindAllOrderedByCommonName() {
            List<Species> species = speciesRepository.findAllOrderedByCommonName();
            Assertions.assertFalse(species.isEmpty());
        }

        @Test
        void testCountBySex() {
            long maleCount = animalRepository.countBySex("Male");
            Assertions.assertTrue(maleCount >= 0);
        }

}
