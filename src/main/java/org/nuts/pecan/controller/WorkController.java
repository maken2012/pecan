package org.nuts.pecan.controller;

import org.nuts.pecan.exception.notfoundexception.WorkNotFoundException;
import org.nuts.pecan.modelassembler.WorkModelAssembler;
import org.nuts.pecan.pojo.Status;
import org.nuts.pecan.pojo.Work;
import org.nuts.pecan.repository.WorkRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/7 01:36:09
 */
@RestController
public class WorkController {
    @Resource
    private WorkRepository repository;
    @Resource
    private WorkModelAssembler assembler;

    @GetMapping("/works")
    public CollectionModel<EntityModel<Work>> all(){
        List<EntityModel<Work>> works = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(works, linkTo(methodOn(WorkController.class).all()).withSelfRel());
    }

    @GetMapping("/works/{id}")
    public EntityModel<Work> one(@PathVariable Long id){
        Work work = repository.findById(id).orElseThrow(() -> new WorkNotFoundException(id));
        return assembler.toModel(work);
    }

    @PostMapping("/works")
    public ResponseEntity<EntityModel<Work>> newWork(@RequestBody Work work){
        work.setStatus(Status.IN_PROGRESS);
        Work newWork = repository.save(work);
        return ResponseEntity.created(linkTo(methodOn(WorkController.class).one(newWork.getId())).toUri()).body(assembler.toModel(newWork));
    }

    @GetMapping("/works/{id}/cancel")
    public ResponseEntity<RepresentationModel> cancel(@PathVariable Long id){
        Work work = repository.findById(id).orElseThrow(() -> new WorkNotFoundException(id));
        if(work.getStatus() == Status.IN_PROGRESS){
            work.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(repository.save(work)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed",
                        "You can't cancel a work that is in the " + work.getStatus() + " status"));
    }

    @GetMapping("/works/{id}/complete")
    public ResponseEntity<RepresentationModel> complete(@PathVariable Long id){
        Work work = repository.findById(id).orElseThrow(() -> new WorkNotFoundException(id));
        if(work.getStatus() == Status.IN_PROGRESS){
            work.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(repository.save(work)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed",
                        "You can't complete a work that is in the " + work.getStatus() + " status"));
    }
}
