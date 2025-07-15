package com.cafeapp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository<ErrorLog> extends JpaRepository<ErrorLog, Long> {
}
