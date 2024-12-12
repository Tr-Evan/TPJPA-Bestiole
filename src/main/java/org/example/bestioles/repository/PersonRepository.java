package org.example.bestioles.repository;

import org.example.bestioles.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByLastNameOrFirstName(String lastName, String firstName);

    List<Person> findByAgeGreaterThanEqual(int age);
}