package com.albin.alcommit.repository;

import com.albin.alcommit.model.Technologies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologiesRepository extends JpaRepository<Technologies, Long> {
}
