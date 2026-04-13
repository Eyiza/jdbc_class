package app.controller;

import app.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController {
    // Constructor Injection
    private final DemoService demoService;
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    // Field Injection. The disadvantage of this is that the object that can then be overridden completely in a method there
//    @Autowired
//    private DemoService demoService;
}
