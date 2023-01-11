package fr.limayrac.resourceserver.repository;

import fr.limayrac.resourceserver.model.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Integer> {

}
