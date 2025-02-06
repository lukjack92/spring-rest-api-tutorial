package rest_api_tutorial.rest_api_tutorial.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope // default is singleton
public class HelloService {
    public String hello() {
        return "Hello World";
    }
}