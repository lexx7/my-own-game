package com.example.myowngame;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class MainController {

    private UserService userService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @PostMapping("login")
    public String login(@RequestParam String username) {
        return userService.add(username);
    }

    @PostMapping("logout")
    public String logout(@RequestParam String username) {
        return userService.remove(username);
    }

    @RequestMapping("button")
    public String button() {
        return "button";
    }

    @RequestMapping("admin")
    public String list(Model model) {
        model.addAttribute("persons", userService.list());
        return "admin";
    }
}
