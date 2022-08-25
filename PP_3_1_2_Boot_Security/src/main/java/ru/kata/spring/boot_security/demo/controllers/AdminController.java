package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    UserService userService;

    @Autowired
    AdminController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")//МЕТОД ЮЗЕРА, А ОСТАЛЬНЫЕ ПЕРЕНЕСЕМ В AdminController
    public String getUser(Model model,@PathVariable("id") long id){ //Principal principal){//@PathVariable("id") long id){
        model.addAttribute("user", userService.findById(id));
        // model.addAttribute("user",userService.findById(id));
        return "show";
    }

    @GetMapping()
    public String getUsers(Model model){
        model.addAttribute("users",userService.findAll());
        return "all";
    }

    @GetMapping("/new")
    public String createUsersForm(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String userDelete(@PathVariable("id") long id){
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") long id,Model model){
        model.addAttribute("user",userService.findById(id));
        return "update";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/admin";
    }
}
