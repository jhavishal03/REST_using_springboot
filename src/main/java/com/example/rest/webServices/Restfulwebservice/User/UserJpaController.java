package com.example.rest.webServices.Restfulwebservice.User;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {
    @Autowired
    private  UserRepository repository;
    @Autowired
    private PostRepository postRepository;
    @GetMapping(value = "/jpa/users")
    public List<User> getAllUser(){
        return  repository.findAll();
    }
    @GetMapping("/jpa/users/{id}")
    public User getById(@PathVariable Integer id){
        Optional<User> user=repository.findById(id);
       if(!user.isPresent())
           throw new userNotFoundException("excepption id->"+id);

//        EntityModel<User> resource=EntityModel.of(user.get());
//        WebMvcLinkBuilder linkTo=
//                linkTo(methodOn(this.getClass(),getAllUser()));
//        resource.add(linkTo.withRel("all-Users"));
        return  user.get();
    }
  @PostMapping(value = "/jpa/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user){
      User savedUser=repository.save(user);
        URI uriLocation= ServletUriComponentsBuilder
                       .fromCurrentRequest()
                        .path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(uriLocation).build();

    }
    @DeleteMapping(value = "/jpa/users/{id}")
    public void deletebyId(@PathVariable Integer id){
        repository.deleteById(id);
    }
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPostsofUser(@PathVariable Integer id){
      Optional<User> userOptional=repository.findById(id);
      if(!userOptional.isPresent())
          throw new userNotFoundException("No such user found with id-> "+id);
      return userOptional.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable  Integer id, @RequestBody Post post){
        Optional<User> userOptional=repository.findById(id);
        User user=userOptional.get();
        post.setUser(user);
        postRepository.save(post);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest()
                     .path("/{id}").buildAndExpand(post.getId())
                     .toUri();
        return ResponseEntity.created(location).build();
    }
}
