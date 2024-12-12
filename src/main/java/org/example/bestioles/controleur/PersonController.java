package org.example.bestioles.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.bestioles.service.PersonService;
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/test/person")
    public String testPersonRepository() {
        personService.testCustomMethods();
        return "Custom repository methods executed!";
    }
}
