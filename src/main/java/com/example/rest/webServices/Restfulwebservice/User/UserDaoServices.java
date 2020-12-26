package com.example.rest.webServices.Restfulwebservice.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoServices {
   private static List<User> users=new ArrayList<>();
   private static int usercount=3;
    static {
    users.add(new User(1,"vishal Jha","Bihar"));
        users.add(new User(2,"Devansh Chaubey","Varanasi"));
        users.add(new User(3,"Shiva Dubey","Haryana"));
    };
    public List<User> findAll(){
        return users;
    }


    public User save(User user){
        if(user.getId() == null){
            user.setId(++usercount);}
        users.add(user);
        return user;
    }


    public User findById(Integer id){
        for(User user:users){
            if(user.getId()==id)return user;
        }
        return null;
    }
    public User deleteById(Integer id){
        Iterator<User> iterator=users.iterator();
        while(iterator.hasNext()){
//            if(user.getId()==id) users.remove(id);
           User user=iterator.next();
           if(user.getId()==id) iterator.remove();
           return user;
        }
        return null;
    }
}
