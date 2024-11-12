package com.suja.mydoc.model;

import com.suja.mydoc.role.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String password;

    @Getter
    private String ip_address;

    @Getter
    private boolean locked;


   @Enumerated(EnumType.STRING)
   private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return role.getAuthorities();
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}

