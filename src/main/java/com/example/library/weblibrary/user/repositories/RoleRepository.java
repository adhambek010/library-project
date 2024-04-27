package com.example.library.weblibrary.user.repositories;

import com.example.library.weblibrary.user.entities.RoleEntity;
import com.example.library.weblibrary.user.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    Optional<RoleEntity> findByName(Role name);
}
