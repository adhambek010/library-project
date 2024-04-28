package com.example.library.weblibrary.user.database.repositories;

import com.example.library.weblibrary.user.database.entities.AuthorityEntity;
import com.example.library.weblibrary.user.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<AuthorityEntity, String> {

    Optional<AuthorityEntity> findByName(Role name);
}
