package org.example.bestioles.repository;

import org.example.bestioles.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer>, CustomPersonRepository {

    List<Person> findByLastnameOrFirstname(String lastname, String firstname);

    List<Person> findByAgeGreaterThanEqual(int age);


}