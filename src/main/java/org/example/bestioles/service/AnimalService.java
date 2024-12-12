package org.example.bestioles.service;

import org.example.bestioles.model.Animal;
import org.example.bestioles.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    // Create
    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    // Update
    public Animal updateAnimal(int id, Animal updatedAnimal) {
        Optional<Animal> existingAnimal = animalRepository.findById(id);
        if (existingAnimal.isPresent()) {
            Animal animal = existingAnimal.get();
            animal.setName(updatedAnimal.getName());
            animal.setColor(updatedAnimal.getColor());
            animal.setSex(updatedAnimal.getSex());
            animal.setSpecies(updatedAnimal.getSpecies());
            return animalRepository.save(animal);
        } else {
            throw new IllegalArgumentException("Animal not found with id: " + id);
        }
    }

    // Passe-plats
    public List<Animal> findAllAnimals() {
        return animalRepository.findAll();
    }

    public Optional<Animal> findAnimalById(int id) {
        return animalRepository.findById(id);
    }

    public void deleteAnimal(int id) {
        animalRepository.deleteById(id);
    }
}