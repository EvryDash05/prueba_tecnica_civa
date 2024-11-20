package com.example.back_prueba_tecnica_civa.repository;

import com.example.back_prueba_tecnica_civa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findByRoleName(String name);
}
