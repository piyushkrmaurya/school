package com.herokuapp.schoolmvc.validator;

import com.herokuapp.schoolmvc.dao.UserDAO;
import com.herokuapp.schoolmvc.form.UserForm;
import com.herokuapp.schoolmvc.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {
 
    @Autowired
    private UserDAO userDAO;
 
    @Override
    public boolean supports(Class<?> c) {
        return c == UserForm.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        UserForm user = (UserForm) target;
 
        // Check the fields of UserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.user.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.user.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.user.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.user.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "NotEmpty.user.type");
 
        if (!errors.hasFieldErrors("userName")) {
            User dbUser = userDAO.findUserAccount(user.getUserName());
            if (dbUser != null) {
                errors.rejectValue("userName", "Duplicate.user.userName");
            }
        }
    }
 
}