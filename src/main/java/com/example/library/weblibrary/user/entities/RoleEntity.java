package com.example.library.weblibrary.user.entities;

import com.example.library.weblibrary.user.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role name;

}
