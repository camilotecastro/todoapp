package com.co.todoapp.persistence.repository;

import com.co.todoapp.enums.TaskStatus;
import com.co.todoapp.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByTaskStatus(TaskStatus status);

    @Modifying //Significa que es una query de actualización
    @Query(value = "UPDATE TASK SET FINISHED = TRUE WHERE ID = :id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id")Long id);
}
