package com.task.management.task.management.Repository;

import com.task.management.task.management.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    void deleteByUsername(String username);
}
