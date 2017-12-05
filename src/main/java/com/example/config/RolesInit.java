package com.example.config;

import com.example.exception.UserNotExist;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.example.config.Constants.*;

@Component
public class RolesInit implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createRoleIfNotFound(ROLE_USER);
        createRoleIfNotFound(ROLE_ADMIN);
        createAdminUserIfNotFound();
    }

    private void createRoleIfNotFound(String name){
        if(roleRepository.findByName(name) == null){
            roleRepository.save(new Role(name));
        }
    }

    private void createAdminUserIfNotFound(){
        try {
            userService.findByUsername(DEFAULT_ADMIN_USERNAME);
        } catch (UserNotExist userNotExist) {
            User user = new User();
            user.setUsername(DEFAULT_ADMIN_USERNAME);
            user.setPassword(DEFAULT_ADMIN_PASSWORD);
            user.setRoles(new HashSet<>(roleRepository.findAll()));
            userService.save(user);
        }
    }
}
