package com.example.library.weblibrary.user.entities;

import com.example.library.weblibrary.user.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Table(name = "all_user")
public class User extends BaseEntity implements UserDetails {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany
    @JoinColumn(name = "identifier")
    private List<UserBooks> books;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.STUDENT.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
