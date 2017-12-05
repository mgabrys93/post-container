package com.example.controller;

import com.example.config.Constants;
import com.example.exception.UserNotExist;
import com.example.model.Role;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PropertySource("classpath:/validation.properties")
public class AdminController {

    @Autowired
    private UserService userService;

    @Value("${User.Username.NotExist}")
    private String usernameNotExist;

    @RequestMapping(value = "/user/role/addAdmin", method = RequestMethod.GET)
    public String getNonAdminUserList(Model model){
        List<String> usernameList = userService.findAll().stream()
                .filter(x -> !x.getRoles().contains(new Role(Constants.ROLE_ADMIN)))
                .map(User::getUsername)
                .collect(Collectors.toList());

        model.addAttribute("usernameList", usernameList);
        return "user_admin_role_add";
    }

    @RequestMapping(value = "/user/role/addAdmin", method = RequestMethod.POST)
    public String addAdminRole(@RequestParam("username") String username, Model model){
        try {
            userService.addUserRole(username, Constants.ROLE_ADMIN);
        } catch (UserNotExist userNotExist) {
            model.addAttribute("usernameNotExist", usernameNotExist);
            return "user_admin_role_add";
        }
        model.addAttribute("username", username);
        return "user_admin_role_added";

    }

    @RequestMapping(value = "user/role/deleteAdmin", method = RequestMethod.GET)
    public String getAdminUserList(Model model){
        List<String> usernameList = userService.findAll().stream()
                .filter(x -> x.getRoles().contains(new Role(Constants.ROLE_ADMIN)))
                .map(User::getUsername)
                .collect(Collectors.toList());
        model.addAttribute("usernameList", usernameList);
        return "user_admin_role_delete";
    }

    @RequestMapping(value = "user/role/deleteAdmin", method = RequestMethod.POST)
    public String deleteAdminRole(@RequestParam("username") String username, Model model) {

        try {
            userService.deleteUserRole(username, Constants.ROLE_ADMIN);
        } catch (UserNotExist userNotExist) {
            model.addAttribute("usernameNotExist", usernameNotExist);
            return "user_admin_role_delete";
        }

        model.addAttribute("username", username);
        return "user_admin_role_deleted";
    }
}
