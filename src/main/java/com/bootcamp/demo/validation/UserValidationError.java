package com.bootcamp.demo.validation;

public class UserValidationError extends RuntimeException {
    public UserValidationError(String errors) {
        super(errors);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
