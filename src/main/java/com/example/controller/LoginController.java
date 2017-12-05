package com.example.controller;

import com.example.config.SecurityContextAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private SecurityContextAccessor securityContextAccessor;

    @RequestMapping({"/", "hello"})
    public String hello(){
        return "redirect:/post/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout){

        if(!securityContextAccessor.isCurrentAuthenticationAnonymous()) return "redirect:/post/list";
        if(error != null){
            model.addAttribute("error", "Invalid username and password!");
        }
        if(logout != null){
            model.addAttribute("logout", "You've been logged out successfully.");
        }
        return "login";
    }
}
