package com.example.quotesapi.Validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target( { TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SearchTagValidation.class)
public @interface SearchTagValidator {
    String message() default "Either Tag Id or Tag value must be present";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
