package com.example.myowngame;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ApiController {
    private UserService userService;

    @PostMapping("push")
    public void push(@RequestParam String username) {
        userService.push(username);
    }

    @PostMapping("clear-all-users")
    public void clearAllUsers() {
        userService.clearAll();
    }

    @PostMapping("clear-push")
    public void clearPush() {
        userService.clearPush();
    }
}
