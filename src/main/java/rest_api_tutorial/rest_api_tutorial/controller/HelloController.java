package rest_api_tutorial.rest_api_tutorial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rest_api_tutorial.rest_api_tutorial.service.HelloService;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @Autowired
    private final HelloService helloService;
    /*
    //Construction, it can be uses where remove the @RequiredArgsConstructor - annotation lombok
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Autowired
    //setter
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
    Dependency injection is recommended by Construction Alt + Insert

    //@Autowired //it doesn't require since 4.3+
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }
    */

    @GetMapping("/")
    public String hello() {
        return helloService.hello();
    }
}