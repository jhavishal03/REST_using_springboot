package com.example.rest.webServices.Restfulwebservice.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDaoServices services;
    @GetMapping("/users")
    public List<User> getAllUser(){
        return services.findAll();
    }
    @GetMapping("/users/{id}")
    public  User getById(@PathVariable Integer id){
        User user=services.findById(id);
        if(user==null)
            throw new userNotFoundException("id-> "+id);
        //hateoas implementation from here
//        EntityModel<User> resource=EntityModel.of(user);
//        WebMvcLinkBuilder linkTo=
//                            linkTo(methodOn(this.getClass(),getAllUser()));
//        resource.add(linkTo.withRel("all-users"));

        return user;
    }
    @PostMapping("/users")
    public ResponseEntity<Object> insertUser(@Valid @RequestBody  User user){
        User saveduser=services.save(user);
       URI uriLocation= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(saveduser.getId())
                .toUri();
       return ResponseEntity.created(uriLocation).build();

    }
    @DeleteMapping("/users/{id}")
    public void deleteByid(@PathVariable Integer id){
        User user=services.deleteById(id);
        if(user.getId()==null)
            throw new userNotFoundException(" id->"+id);
//    return user;
    }
}
