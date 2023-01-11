package fr.limayrac.resourceserver.service;

import fr.limayrac.resourceserver.model.Eleve;
import fr.limayrac.resourceserver.repository.EleveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EleveService {

    @Autowired
    private EleveRepository eleveRepository;

    public Optional<Eleve> getEleve(final Integer id) {
        return eleveRepository.findById(id);
    }

    public List<Eleve> getEleves() {
        return eleveRepository.findAll();
    }
}
