package com.projects.banking.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TransactionTypeValidator.class)
public @interface TransactionTypeValidation  {

    String message() default "Invalid transation type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
