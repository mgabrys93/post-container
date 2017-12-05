package com.example.validator;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Element.Empty");
        if(userRepository.findByUsername(user.getUsername()) != null){
            errors.rejectValue("username", "User.Username.Duplicate");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Element.Empty");
        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("password", "User.PasswordConfirm.Diff");
        }
    }

}
