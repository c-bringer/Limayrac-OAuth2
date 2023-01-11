package fr.limayrac.resourceserver.model;

import fr.limayrac.resourceserver.controller.EleveController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EleveAssembler implements RepresentationModelAssembler<Eleve, EntityModel<Eleve>> {

    @Override
    public EntityModel<Eleve> toModel(Eleve eleve) {
        return EntityModel.of(eleve,
                linkTo(methodOn(EleveController.class).getEleve(eleve.getId())).withSelfRel());
    }
}
