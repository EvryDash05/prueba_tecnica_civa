package com.example.back_prueba_tecnica_civa.repository;

import com.example.back_prueba_tecnica_civa.entity.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<BusEntity, Integer> {
}
