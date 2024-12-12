package org.example.bestioles.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.bestioles.model.Person;
import org.springframework.stereotype.Repository;

import java.util.Random;
import java.util.stream.IntStream;

@Repository
public class CustomPersonRepositoryImpl implements CustomPersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteAllWithoutAnimals() {
        String jpql = "DELETE FROM Person p WHERE SIZE(p.animals) = 0";
        
        entityManager.createQuery(jpql).executeUpdate();
    }

    @Override
    @Transactional
    public void generateRandomPersons(int count) {
        Random random = new Random();
        IntStream.range(0, count).forEach(i -> {
            Person person = new Person();
            person.setFirstname("Firstname" + random.nextInt(1000));
            person.setLastname("Lastname" + random.nextInt(1000));
            person.setAge(random.nextInt(100)); // Age aléatoire entre 0 et 99
            entityManager.persist(person);
        });
    }
}
