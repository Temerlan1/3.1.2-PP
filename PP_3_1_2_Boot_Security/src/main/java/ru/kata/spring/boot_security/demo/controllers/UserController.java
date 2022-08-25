package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping()
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logout1")
    public String getlogoutForm(Model model,Principal principal){
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "logout";
    }

    @GetMapping("/user")//МЕТОД ЮЗЕРА, А ОСТАЛЬНЫЕ ПЕРЕНЕСЕМ В AdminController
    public String getUser(Model model,Principal principal){//@PathVariable("id") long id){
        model.addAttribute("user", userService.findByUsername(principal.getName()));
       // model.addAttribute("user",userService.findById(id));
        return "show";
    }

}
