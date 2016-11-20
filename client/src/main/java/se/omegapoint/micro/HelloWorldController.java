package se.omegapoint.micro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello() {

        LOGGER.error("BEFORE FETCHING!");
        restTemplate.getForEntity("http://localhost:8081/conferences", String.class);
        LOGGER.error("AFTER FETCHING!");
        return "Hello World!";
    }
}
