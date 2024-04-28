package com.example.library.weblibrary.user.database.entities;

import com.example.library.weblibrary.user.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class AuthorityEntity extends BaseEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
