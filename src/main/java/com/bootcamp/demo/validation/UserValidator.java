package com.bootcamp.demo.validation;

import com.bootcamp.demo.model.User;

public class UserValidator {

    public void validateUserAtRegistration (User user){
        String errors = "";

        if (user.getFirstName() == null)
            errors += "First name cannot be empty!\n";
        else if (!user.getFirstName().matches("[A-Z]+[a-z]+[A-Z]*[a-z]*"))
            errors += "First name must start with an uppercase letter and have at least another letter!\n";

        if (user.getLastName() == null)
            errors += "Last name cannot be empty!\n";
        else if (!user.getLastName().matches("[A-Z]+[a-z]+[A-Z]*[a-z]*"))
            errors += "Last name must start with an uppercase letter and have at least another letter!\n";

        if (user.getEmail() == null)
            errors += "Email address cannot be empty!\n";
        else if (!user.getEmail().matches("[a-zA-Z0-9._-]+@[a-zA-Z]+[.][a-zA-Z]+"))
            errors += "The given email address doesn't seem right\n";

        if (user.getPassword() == null)
            errors += "Password cannot be empty!\n";
        else if (user.getPassword().length() < 8)
            errors += "Password isn't long enough!\n";
        else
        {
            if (!user.getPassword().matches(".*[A-Z]+.*"))
                errors += "Password must contain an uppercase letter!\n";
            if (!user.getPassword().matches(".*[a-z]+.*"))
                errors += "Password must contain a lowercase letter!\n";
            if (!user.getPassword().matches(".*[0-9]+.*"))
                errors += "Password must contain a digit!\n";
            if (!user.getPassword().matches(".*[!#$%^&*~_=+./<>-]+.*"))
                errors += "Password must contain a special character(!#$%^&*~_=+./<>-)\n";
        }

        if (user.getPhoneNumber() == null)
            errors += "Phone number cannot be empty!\n";
        else if (user.getPhoneNumber().length() != 12)
            errors += "Phone number doesn't have the right length!\n";
        else if (!user.getPhoneNumber().matches("[+]40[0-9]{9}"))
            errors += "Phone number doesn't have the right format (+40...)\n";

        if (!errors.equals(""))
            throw new UserValidationError(errors);
    }


    public void validateUserAtLogin(User user)
    {
        String errors = "";

        if (user.getEmail() == null)
            errors += "Email address cannot be empty!\n";
        if (user.getPassword() == null)
            errors += "Password cannot be empty!\n";

        if (!errors.equals(""))
            throw new UserValidationError(errors);
    }

}
