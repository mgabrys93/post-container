package com.example.controller;

import com.example.config.Constants;
import com.example.config.SecurityContextAccessor;
import com.example.exception.UserNotExist;
import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;
import com.example.service.CommentService;
import com.example.service.PostService;
import com.example.service.UserService;
import com.example.util.PaginationModel;
import com.example.validator.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PaginationModel paginationModel;

    @Autowired
    private MessageValidator messageValidator;

    @Autowired
    private SecurityContextAccessor securityContextAccessor;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String postList(@RequestParam(value = "page", required = false) Integer page, Model model){
        int pagesNumber = postService.getPagesNumber();
        if(page == null){
            page = 1;
        }
        List<Post> posts = postService.getNewestPostsByPage(page);

        model.addAttribute("pagesInterval", paginationModel.generatePagesInterval(page, Constants.PAGE_INTERVAL, pagesNumber));
        model.addAttribute("postList", posts);
        model.addAttribute("pagesNumber", pagesNumber);
        return "post_list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("postForm", new Post());
        return "post_create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("postForm") Post postForm, BindingResult bindingResult,
                         Model model, Principal principal){
        messageValidator.validate(postForm.getMessage(), bindingResult);
        if(bindingResult.hasErrors()){
            return "post_create";
        }
        User user;
        try {
            user = userService.findByUsername(principal.getName());
        } catch (UserNotExist userNotExist) {
            model.addAttribute("username", principal.getName());
            return "user_not_exist";
        }
        postForm.setPostAuthor(user);
        postForm.getMessage().setLocalDateTime(LocalDateTime.now());
        postService.save(postForm);
        return "redirect:/post/" + postForm.getId();
    }

    @RequestMapping(value = "/{post_id}", method = RequestMethod.GET)
    public String postView(@PathVariable("post_id") Long postId, Model model){
        Post post = postService.findOne(postId);
        if(post == null){
            return "post_not_found";
        }
        model.addAttribute("post", post);
        model.addAttribute("commentForm", new Comment());
        return "post_view";
    }

    @RequestMapping(value = "/{post_id}", method = RequestMethod.POST)
    public String addComment(@PathVariable("post_id") Long postId,
                             @ModelAttribute("commentForm") Comment commentForm,
                             BindingResult bindingResult, Principal principal, Model model){

        messageValidator.validate(commentForm.getMessage(), bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute(postService.findOne(postId));
            return "post_view";
        }
        User user;
        try {
            user = userService.findByUsername(principal.getName());
        } catch (UserNotExist userNotExist) {
            model.addAttribute("username", principal.getName());
            return "user_not_exist";
        }
        commentForm.setCommentAuthor(user);
        commentForm.getMessage().setLocalDateTime(LocalDateTime.now());

        Post post = postService.findOne(postId);
        commentForm.setPost(post);

        commentService.save(commentForm);
        return "redirect:/post/" + postId;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(@RequestParam("postId") Long postId, Principal principal){
        User user;
        try {
            user = userService.findByUsername(principal.getName());
        } catch (UserNotExist userNotExist) {
            return "user_not_exist";
        }
        Post post = postService.findOne(postId);

        if(securityContextAccessor.isCurrentAuthenticationAdmin() || post.getPostAuthor().equals(user)){
            postService.delete(postId);
            return "redirect:/post/list";
        }
        return "post_cant_delete";
    }

    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    public String deleteComment(@RequestParam("commentId") Long commentId, Principal principal){
        User user;
        try {
            user = userService.findByUsername(principal.getName());
        } catch (UserNotExist userNotExist) {
            return "user_not_exist";
        }
        Comment comment = commentService.findOne(commentId);
        if (securityContextAccessor.isCurrentAuthenticationAdmin() || comment.getCommentAuthor().equals(user)){
            commentService.delete(commentId);
            return "redirect:/post/" + comment.getPost().getId();
        }
        return "comment_cant_delete";
    }
}
