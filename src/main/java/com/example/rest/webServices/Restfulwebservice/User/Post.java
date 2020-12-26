package com.example.rest.webServices.Restfulwebservice.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {
 @Id
 @GeneratedValue
 private Integer id;
 private String post;

    public Post() {
    }

    public Post(Integer id, String post) {
        this.id = id;
        this.post = post;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    public Integer getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
