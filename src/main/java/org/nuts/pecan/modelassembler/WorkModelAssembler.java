package org.nuts.pecan.modelassembler;

import org.nuts.pecan.controller.WorkController;
import org.nuts.pecan.pojo.Status;
import org.nuts.pecan.pojo.Work;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/7 01:32:32
 */
@Component
public class WorkModelAssembler implements RepresentationModelAssembler<Work, EntityModel<Work>> {

    /**
     * Converts the given entity into a {@code D}, which extends {@link RepresentationModel}.
     *
     * @param work
     * @return
     */
    @Override
    public EntityModel<Work> toModel(Work work) {
        // Any way
        EntityModel<Work> model = new EntityModel<>(work,
            linkTo(methodOn(WorkController.class).one(work.getId())).withSelfRel(),
            linkTo(methodOn(WorkController.class).all()).withRel("works"));

        // if work status equals IN_PROGRESS , add link with cancel and complete
        if(work.getStatus() == Status.IN_PROGRESS){
            model.add(linkTo(methodOn(WorkController.class).cancel(work.getId())).withRel("cancel"));
            model.add(linkTo(methodOn(WorkController.class).complete(work.getId())).withRel("complete"));
        }
        return model;
    }
}
