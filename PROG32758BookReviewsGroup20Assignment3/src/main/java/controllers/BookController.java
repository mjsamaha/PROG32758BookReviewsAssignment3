package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    // map to index
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
