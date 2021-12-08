package com.bootcamp.demo.validation;

import com.bootcamp.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public final static String NAME_PATTERN = "[A-Z-/-]+[a-z/-]+[A-Z/-]*[a-z/-]";
    public final static String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public final static String CONTAINING_UPPERCASE_PATTERN = ".*[A-Z]+.*";
    public final static String CONTAINING_LOWERCASE_PATTERN = ".*[a-z]+.*";
    public final static String CONTAINING_DIGIT_PATTERN = ".*[0-9]+.*";
    public final static String CONTAINING_SPECIAL_CHARACTER_PATTERN = ".*[!#$%^&*~_=+./<>-]+.*";
    public final static String PHONE_NUMBER_PATTERN = "([+]4)*0[0-9]{9}";

    public String validateFirstName(String firstName) {
        String errors = "";
        if (firstName == null) {
            errors += "First name cannot be empty!\n";
        }
        else if (!firstName.matches(NAME_PATTERN)) {
            errors += "First name must start with an uppercase letter and have at least another letter!\n";
        }
        return errors;
    }

    public String validateLastName(String lastName) {
        String errors = "";
        if (lastName == null) {
            errors += "Last name cannot be empty!\n";
        }
        else if (lastName.matches(NAME_PATTERN)) {
            errors += "Last name must start with an uppercase letter and have at least another letter!\n";
        }
        return errors;
    }

    public String validateEmail(String email) {
        String errors = "";
        if (email == null) {
            errors += "Email address cannot be empty!\n";
        }
        else if (email.matches(EMAIL_PATTERN)) {
            errors += "The given email address doesn't seem right\n";
        }
        return errors;
    }

    public String validatePassword(String password) {
        String errors = "";
        if (password == null) {
            errors += "Password cannot be empty!\n";
        }
        else if (password.length() < 8) {
            errors += "Password isn't long enough!\n";
        }
        else
        {
            if (!password.matches(CONTAINING_UPPERCASE_PATTERN)) {
                errors += "Password must contain an uppercase letter!\n";
            }
            if (!password.matches(CONTAINING_LOWERCASE_PATTERN)) {
                errors += "Password must contain a lowercase letter!\n";
            }
            if (!password.matches(CONTAINING_DIGIT_PATTERN)) {
                errors += "Password must contain a digit!\n";
            }
            if (!password.matches(CONTAINING_SPECIAL_CHARACTER_PATTERN)) {
                errors += "Password must contain a special character(!#$%^&*~_=+./<>-)\n";
            }
        }
        return errors;
    }

    public String validatePhoneNumber(String phoneNumber) {
        String errors = "";
        if (phoneNumber == null) {
            errors += "Phone number cannot be empty!\n";
        }
        else if (phoneNumber.length() != 12) {
            errors += "Phone number doesn't have the right length!\n";
        }
        else if (!phoneNumber.matches(PHONE_NUMBER_PATTERN)) {
            errors += "Phone number doesn't have the right format (+40...)\n";
        }
        return errors;
    }

    public void validateUserAtRegistration(User user) throws UserValidationError {
        String errors = "";
        errors += validateFirstName(user.getFirstName());
        errors += validateLastName(user.getLastName());
        errors += validateEmail(user.getEmail());
        errors += validatePassword(user.getPassword());
        errors += validatePhoneNumber(user.getPhoneNumber());
        if (!errors.equals("")) {
            throw new UserValidationError(errors);
        }
    }

    public void validateUserAtLogin(User user) throws UserValidationError
    {
        String errors = "";
        errors += validateEmail(user.getEmail());
        errors += validatePassword(user.getPassword());
        if (!errors.equals("")) {
            throw new UserValidationError(errors);
        }
    }
}
