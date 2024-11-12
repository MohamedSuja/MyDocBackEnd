package com.suja.mydoc.role;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AllowedRolesValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotRoles {


    String message() default "Invalid role; allowed values are USER, ADMIN or MANAGER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
