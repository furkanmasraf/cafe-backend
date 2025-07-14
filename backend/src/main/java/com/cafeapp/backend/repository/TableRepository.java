package com.cafeapp.backend.repository;

import com.cafeapp.backend.model.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
