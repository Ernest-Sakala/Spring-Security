package com.daoauthenticate.dao.controller;

import com.daoauthenticate.dao.model.UserModel;
import com.daoauthenticate.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/register")
    public String registerUserPage(Model model){
        UserModel userModel = new UserModel();
        model.addAttribute("user", userModel);
        return "register";
    }


    @PostMapping("user/register")
    public String registerUser(@ModelAttribute("user") UserModel userModel){
        userService.registerUser(userModel);
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "sign_in";
    }

}
