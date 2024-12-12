package org.example.bestioles.repository;


public interface CustomPersonRepository {

    void deleteAllWithoutAnimals();

    void generateRandomPersons(int count);
}
