package com.example.rest.webServices.Restfulwebservice.HelloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(path = "/ok")
   public HelloWorld helloworld(){
       return new HelloWorld("bhen ki chut");
   }
}
