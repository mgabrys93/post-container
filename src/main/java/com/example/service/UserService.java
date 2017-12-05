package com.example.service;

import com.example.exception.UserNotExist;
import com.example.model.Comment;
import com.example.model.PasswordChange;
import com.example.model.Post;
import com.example.model.User;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService extends DefaultService<User> {

    User findByUsername(String username) throws UserNotExist;
    int getCommentPagesNumber(User user);
    int getPostPagesNumber(User user);
    List<Post> getNewestPostByPage(int page, User user);
    List<Comment> getNewestCommentByPage(int page, User user);
    void addUserRole(String username, String rolename) throws UserNotExist;
    void deleteUserRole(String username, String rolename) throws UserNotExist;
    void changePassword(User user, PasswordChange passwordChange);

    void checkOldPassword(BindingResult bindingResult, String oldPassword, User user);
}
