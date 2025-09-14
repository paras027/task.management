package com.task.management.task.management.Repository;

import com.task.management.task.management.Entity.projectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface projectRepository extends JpaRepository<projectEntity, Long> {
}
