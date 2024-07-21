package com.projects.banking.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Null;

public class AccountValidator implements ConstraintValidator<AccountValidation, String> {


    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {

        return name.length()>2 && !name.equals(" ");
    }
}
