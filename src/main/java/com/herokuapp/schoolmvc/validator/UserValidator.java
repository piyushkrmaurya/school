package com.herokuapp.schoolmvc.validator;

import com.herokuapp.schoolmvc.dao.UserDAO;
import com.herokuapp.schoolmvc.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
 
    @Autowired
    private UserDAO userDAO;
 
    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> c) {
        return c == User.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
 
        // Check the fields of User.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.user.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.user.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.user.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.user.gender");
 
        if (!errors.hasFieldErrors("userName")) {
            User dbUser = userDAO.findUserAccount(user.getUserName());
            if (dbUser != null) {
                // Username is not available.
                errors.rejectValue("userName", "Duplicate.user.userName");
            }
        }
    }
 
}