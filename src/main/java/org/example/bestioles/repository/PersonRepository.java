package org.example.bestioles.repository;

import org.example.bestioles.model.Animal;
import org.example.bestioles.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

        //TP4
    List<Person> findByLastnameOrFirstname(String lastname, String firstname);

    List<Person> findByAgeGreaterThanEqual(int age);

        // TP5
    @Query("SELECT p FROM Person p WHERE p.age BETWEEN :ageMin AND :ageMax")
    List<Person> findByAgeBetween(@Param("ageMin") int ageMin, @Param("ageMax") int ageMax);

    @Query("SELECT p FROM Person p JOIN p.animals a WHERE a = :animal")
    List<Person> findByAnimal(@Param("animal") Animal animal);
}