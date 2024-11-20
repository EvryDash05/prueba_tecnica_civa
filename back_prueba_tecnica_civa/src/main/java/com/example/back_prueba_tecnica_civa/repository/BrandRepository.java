package com.example.back_prueba_tecnica_civa.repository;

import com.example.back_prueba_tecnica_civa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
    Optional<BrandEntity> findByBrandName(String name);
}
