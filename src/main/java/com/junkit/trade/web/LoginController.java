package com.junkit.trade.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showlogin")
    public String login() {
        return "login";
    }
}
