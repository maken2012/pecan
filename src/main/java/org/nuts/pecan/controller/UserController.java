package org.nuts.pecan.controller;

import org.nuts.pecan.exception.notfoundexception.UserNotFoundException;
import org.nuts.pecan.modelassembler.UserModelAssembler;
import org.nuts.pecan.pojo.User;
import org.nuts.pecan.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/6 21:45:17
 */
@RestController
public class UserController {

    @Resource
    private UserRepository repository;
    @Resource
    private UserModelAssembler assembler;

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all(){
        List<EntityModel<User>> users = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(users, linkTo(methodOn(UserController.class)).withSelfRel());

    }

    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id){
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        User updateUser = repository.findById(id)
            .map(user -> {
                user.setName(newUser.getName());
                return repository.save(user);
            }).orElseGet( () -> {
                newUser.setId(id);
                return repository.save(newUser);
            });
        EntityModel<User> entityModel = assembler.toModel(updateUser);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
