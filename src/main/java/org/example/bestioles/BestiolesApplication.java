package org.example.bestioles;

import org.example.bestioles.model.Animal;
import org.example.bestioles.model.Person;
import org.example.bestioles.model.Species;
import org.example.bestioles.repository.AnimalRepository;
import org.example.bestioles.repository.PersonRepository;
import org.example.bestioles.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BestiolesApplication implements CommandLineRunner {

	// Injection des repositories
	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private SpeciesRepository speciesRepository;

	public static void main(String[] args) {
		SpringApplication.run(BestiolesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Liste des animaux : ");
		animalRepository.findAll().forEach(animal -> System.out.println(animal.getName()));

		System.out.println("Liste des personnes : ");
		personRepository.findAll().forEach(person -> System.out.println(person.getFirstname() + " " + person.getLastname()));

		System.out.println("Liste des espèces : ");
		speciesRepository.findAll().forEach(species -> System.out.println(species.getCommonName()));


		Animal animal = new Animal();
		Species species = speciesRepository.findById(1).orElseThrow();
		animal.setSpecies(species);
		animal.setColor("Blanc");
		animal.setName("Charly");
		animal.setSex("M");



		animalRepository.save(animal);

		Person person = new Person();
		person.setAge(30);
		person.setFirstname("Luc");
		person.setLastname("Durand");

		personRepository.save(person);

		species.setCommonName("Perroquet");
		species.setLatinName("Amazona amazonica");
		speciesRepository.save(species);

		animalRepository.findById(1).ifPresent(animalFound -> System.out.println("Animal trouvé: " + animalFound.getName()));
		personRepository.findById(1).ifPresent(personFound -> System.out.println("Personne trouvée: " + personFound.getFirstname()));
		speciesRepository.findById(1).ifPresent(speciesFound -> System.out.println("Espèce trouvée: " + speciesFound.getCommonName()));

		animalRepository.delete(animal);
		System.out.println("Liste des animaux après suppression : ");
		animalRepository.findAll().forEach(animal1 -> System.out.println(animal1.getName()));

		long countAfterDelete = animalRepository.count();
		System.out.println("Nombre d'animaux après suppression : " + countAfterDelete);
	}
}