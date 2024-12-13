package org.example.bestioles.controleur;

import org.example.bestioles.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public void runTest() {
        testService.testQueries();
    }
}
