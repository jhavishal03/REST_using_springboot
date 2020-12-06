package com.example.rest.webServices.Restfulwebservice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Configuration //tell spring that this is bean
@EnableSwagger2
public class swaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact(
            "Vishal Jha", "https://www.linkedin.com/jhavishal03", "vishaljha.12rpvv@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "REST API Title", "REST API Description", "1.0",
            "urn:tos",DEFAULT_CONTACT ,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());
    public static final Set<String> defaultpandc=new HashSet<String>((Arrays.asList("application/json",
            "application/xml")));
    @Bean
   public  Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                   .apiInfo(DEFAULT_API_INFO)
                    .produces(defaultpandc)
                     .consumes(defaultpandc);
    }
}
