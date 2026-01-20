package com.emirhan.todo.todo_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.emirhan.todo.todo_api.entity.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByUserId(Long userId);
}
