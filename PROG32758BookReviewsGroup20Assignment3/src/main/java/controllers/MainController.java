package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
// I was using @Controllers and not @RestControllers
@Controller
public class MainController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/denied")
    public String denied() {
        return "denied";
    }

    // implement route
    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("message", "Hello Admin User");
        return "admin"; // return admin.html view, only accessible for admin users -- not guest
    }


}