package org.example.bestioles.service;

import org.example.bestioles.model.Species;
import org.example.bestioles.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    // Create
    public Species createSpecies(Species species) {
        return speciesRepository.save(species);
    }

    // Update
    public Species updateSpecies(int id, Species updatedSpecies) {
        Optional<Species> existingSpecies = speciesRepository.findById(id);
        if (existingSpecies.isPresent()) {
            Species species = existingSpecies.get();
            species.setCommonName(updatedSpecies.getCommonName());
            species.setLatinName(updatedSpecies.getLatinName());
            return speciesRepository.save(species);
        } else {
            throw new IllegalArgumentException("Species not found with id: " + id);
        }
    }

    // Passe-plats
    public List<Species> findAllSpecies() {
        return speciesRepository.findAll();
    }

    public Optional<Species> findSpeciesById(int id) {
        return speciesRepository.findById(id);
    }

    public void deleteSpecies(int id) {
        speciesRepository.deleteById(id);
    }
}