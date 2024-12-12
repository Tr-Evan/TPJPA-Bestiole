package org.example.bestioles.repository;

import org.example.bestioles.model.Person;
import org.example.bestioles.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

        // TP4
    Species findFirstByCommonName(String commonName);

    List<Species> findByLatinNameContainingIgnoreCase(String latinName);

    // TP5
    @Query("SELECT s FROM Species s ORDER BY s.commonName ASC")
    List<Species> findAllOrderedByCommonName();

    @Query("SELECT s FROM Species s WHERE LOWER(s.commonName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Species> findByCommonNameLike(@Param("name") String name);
}