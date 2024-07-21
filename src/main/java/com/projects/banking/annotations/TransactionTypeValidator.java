package com.projects.banking.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class TransactionTypeValidator implements ConstraintValidator<TransactionTypeValidation,String> {
    @Override
    public boolean isValid(String transactiontype, ConstraintValidatorContext constraintValidatorContext) {
        List<String> transactiontypes = List.of("deposit","withdraw");
         return transactiontypes.contains(transactiontype);


    }
}
