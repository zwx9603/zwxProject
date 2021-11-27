package com.soft.zwx.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
//@RequestMapping("/controller")
public class UserController {

    @RequestMapping("/login1")
    public String toLogin(){
        return "login";
    }
}
