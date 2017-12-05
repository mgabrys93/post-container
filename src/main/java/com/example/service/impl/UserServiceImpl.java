package com.example.service.impl;

import com.example.config.Constants;
import com.example.exception.UserNotExist;
import com.example.model.*;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.util.DateStringFormatter;
import com.example.util.PaginationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PaginationModel paginationModel;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DateStringFormatter dateStringFormatter;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUsername(String username) throws UserNotExist {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserNotExist();
        }else {
            return user;
        }
    }

    @Override
    public int getCommentPagesNumber(User user) {
        return paginationModel.getPageNumberRoundedUp(user.getComments().size(), Constants.PAGE_ELEMENTS);
    }

    @Override
    public int getPostPagesNumber(User user) {
        return paginationModel.getPageNumberRoundedUp(user.getPosts().size(), Constants.PAGE_ELEMENTS);
    }

    @Override
    public List<Post> getNewestPostByPage(int page, User user) {
        return dateStringFormatter.updateDisplayPostsDateFormat(postRepository.findByPostAuthor(user, new PageRequest(page - 1, Constants.PAGE_ELEMENTS, Sort.Direction.DESC, "id")));
    }

    @Override
    public List<Comment> getNewestCommentByPage(int page, User user) {
        return dateStringFormatter.updateDisplayCommentsDateFormat(commentRepository.findByCommentAuthor(user, new PageRequest(page - 1, Constants.PAGE_ELEMENTS, Sort.Direction.DESC, "id")));
    }

    @Override
    public void addUserRole(String username, String rolename) throws UserNotExist {
        User user = findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void deleteUserRole(String username, String rolename) throws UserNotExist {
        User user = findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        Set<Role> roles = user.getRoles();
        roles.remove(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void changePassword(User user, PasswordChange passwordChange) {
        user.setPassword(passwordChange.getNewPassword());
        save(user);
    }

    @Override
    public void checkOldPassword(BindingResult bindingResult, String oldPassword, User user) {
        if(!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())){
            bindingResult.rejectValue("oldPassword", "Password.Change.Old.Diff");
        }
    }
}
