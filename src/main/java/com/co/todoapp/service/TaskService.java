package com.co.todoapp.service;

import com.co.todoapp.enums.TaskStatus;
import com.co.todoapp.exceptions.TodoExceptions;
import com.co.todoapp.mapper.TaskInDTOToTask;
import com.co.todoapp.persistence.entity.Task;
import com.co.todoapp.persistence.repository.TaskRepository;
import com.co.todoapp.service.dto.TaskInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDto taskInDto){
        Task task = mapper.map(taskInDto);
         return this.repository.save(task);
    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty())
            throw new TodoExceptions("Task not found", HttpStatus.NOT_FOUND);
        this.repository.markTaskAsFinished(id);
    }

    @Transactional
    public void deleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty())
            throw new TodoExceptions("Task not found", HttpStatus.NOT_FOUND);
        this.repository.deleteById(id);
    }

}
