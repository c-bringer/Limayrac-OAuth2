package fr.limayrac.resourceserver.controller;

import fr.limayrac.resourceserver.exception.RessourceNotFoundException;
import fr.limayrac.resourceserver.model.Eleve;
import fr.limayrac.resourceserver.model.EleveAssembler;
import fr.limayrac.resourceserver.service.EleveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "Eleve", description = "Liste des méthodes liées aux eleves")
public class EleveController {

    @Autowired
    private EleveService eleveService;

    private final EleveAssembler eleveAssembler;

    EleveController(EleveAssembler eleveAssembler) {
        this.eleveAssembler = eleveAssembler;
    }

    @GetMapping(value = "/eleves")
    @Operation(summary = "Ne nécessite pas d'options.",
            description = "Cette méthode permet de récuperer la liste des eleves.",
            tags = { "Eleve" })
    public CollectionModel<EntityModel<Eleve>> getEleves() {
        List<EntityModel<Eleve>> eleves = eleveService.getEleves().stream()
                .map(eleveAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(eleves, linkTo(methodOn(EleveController.class).getEleves()).withSelfRel());
    }

    @GetMapping(value = "/eleves/{idEleve}")
    @Operation(summary = "Ne nécessite pas d'options.",
            description = "Cette méthode permet de récuperer les détails d'un eleve.",
            tags = { "Eleve" })
    public ResponseEntity<?> getEleve(@PathVariable("idEleve") final Integer id) {
        Eleve eleve = eleveService.getEleve(id)
                .orElseThrow(() -> new RessourceNotFoundException("Impossible de trouver l'eleve " + id + "."));

        if (eleve != null) {
            return ResponseEntity.ok(eleveAssembler.toModel(eleve));
        }

        return ResponseEntity.badRequest().body(
                "Une erreur est survenue lors de la requête, veuillez vérifier les informations envoyées."
        );
    }
}
