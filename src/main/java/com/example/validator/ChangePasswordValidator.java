package com.example.validator;

import com.example.model.PasswordChange;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ChangePasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordChange.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PasswordChange passwordChange = (PasswordChange) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "Element.Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "Element.Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatNewPassword", "Element.Empty");

        if (!(passwordChange.getOldPassword().isEmpty() || passwordChange.getNewPassword().isEmpty())){
            if(passwordChange.getOldPassword().equals(passwordChange.getNewPassword())){
                errors.rejectValue("oldPassword", "Password.Change.Old.Same");
            }
        }
        if (!passwordChange.getNewPassword().equals(passwordChange.getRepeatNewPassword())){
            errors.rejectValue("repeatNewPassword", "User.PasswordConfirm.Diff");
        }

    }
}
