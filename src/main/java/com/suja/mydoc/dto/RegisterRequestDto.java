package com.suja.mydoc.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.suja.mydoc.role.NotRoles;
import com.suja.mydoc.role.Role;
import com.suja.mydoc.role.RoleDeserializer;
import com.suja.mydoc.validators.ValidIpAddress;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Password must contain at least one digit, one uppercase and one lowercase letter, and at least 8 characters")
    private String password;


    @NotRoles()
    @JsonDeserialize(using = RoleDeserializer.class)
    private Role role;


    @NotBlank(message = "IP address is required")
    @ValidIpAddress
    private String ip_address;
}
