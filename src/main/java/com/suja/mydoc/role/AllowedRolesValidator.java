package com.suja.mydoc.role;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AllowedRolesValidator implements ConstraintValidator<NotRoles, Role> {

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        for (Role role : Role.values()) {
            if (role.equals(value)) {
                return true;
            }
        }
     return false;
    }
}
