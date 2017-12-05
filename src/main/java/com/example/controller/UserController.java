package com.example.controller;

import com.example.config.Constants;
import com.example.exception.UserNotExist;
import com.example.model.Comment;
import com.example.model.PasswordChange;
import com.example.model.Post;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.PaginationModel;
import com.example.validator.ChangePasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaginationModel paginationModel;

    @Autowired
    private ChangePasswordValidator changePasswordValidator;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String defaultUserProfile(@PathVariable("username") String username, Model model){
        User user;
        try {
            user = userService.findByUsername(username);
        } catch (UserNotExist userNotExist) {
            model.addAttribute("username", username);
            return "user_not_exist";
        }

        model.addAttribute("user", user);
        return "default_user";
    }


    @RequestMapping(value = "/{username}/posts", method = RequestMethod.GET)
    public String userPosts(@PathVariable("username") String username,
                            @RequestParam(value = "page", required = false) Integer page, Model model){

        User user;
        try {
            user = userService.findByUsername(username);
        } catch (UserNotExist userNotExist) {
            model.addAttribute("username", username);
            return "user_not_exist";
        }
        page = paginationModel.updatePageIfNull(page);

        int pagesNumber = userService.getPostPagesNumber(user);
        List<Post> posts = userService.getNewestPostByPage(page, user);

        model.addAttribute("posts", posts);
        model.addAttribute("pagesNumber", pagesNumber);
        model.addAttribute("pagesInterval", paginationModel.generatePagesInterval(page,
                Constants.PAGE_INTERVAL, pagesNumber));

        return "user_posts";
    }

    @RequestMapping(value = "/{username}/comments", method = RequestMethod.GET)
    public String userComments(@PathVariable("username") String username,
                               @RequestParam(value = "page", required = false) Integer page, Model model){
        User user;
        try {
            user = userService.findByUsername(username);
        } catch (UserNotExist userNotExist) {
            model.addAttribute("username", username);
            return "user_not_exist";
        }

        page = paginationModel.updatePageIfNull(page);

        int pagesNumber = userService.getCommentPagesNumber(user);
        List<Comment> comments = userService.getNewestCommentByPage(page, user);

        model.addAttribute("pagesNumber", pagesNumber);
        model.addAttribute("comments", comments);
        model.addAttribute("pagesInterval", paginationModel.generatePagesInterval(page,
                Constants.PAGE_INTERVAL, pagesNumber));

        return "user_comments";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findUser(@RequestParam("username") String username, Model model){
        User user;
        try {
            user = userService.findByUsername(username);
        } catch (UserNotExist userNotExist) {
            model.addAttribute("username", username);
            return "user_not_exist";
        }
        return "redirect:/user/" + user.getUsername();

    }

    @RequestMapping(value = "/password/change", method = RequestMethod.GET)
    public String changePassword(Model model){
        model.addAttribute("passwordChange", new PasswordChange());
        return "password_change";
    }

    @RequestMapping(value = "/password/change", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("passwordChange") PasswordChange passwordChange, Principal principal, BindingResult bindingResult, Model model){

        changePasswordValidator.validate(passwordChange, bindingResult);
        User user;
        try {
            user = userService.findByUsername(principal.getName());
        } catch (UserNotExist userNotExist) {
            model.addAttribute("username", principal.getName());
            return "user_not_exist";
        }

        userService.checkOldPassword(bindingResult, passwordChange.getOldPassword(), user);
        if(bindingResult.hasErrors()){
            return "password_change";
        }
        userService.changePassword(user, passwordChange);

        return "password_changed";
    }

    @RequestMapping(value = "/getUsernames", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getUsernames(@RequestParam(value = "username") String username){
        return userService.findAll().stream()
                .filter(x -> x.getUsername().startsWith(username))
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

}
