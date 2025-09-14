package com.task.management.task.management.Repository;

import com.task.management.task.management.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface taskRepository extends JpaRepository<TaskEntity,Long> {
    List<TaskEntity> findByTaskName(String taskname);
}
