package com.emirhan.todo.todo_api.service;
import java.util.List;

import com.emirhan.todo.todo_api.entity.ToDo;

public interface ToDoService {
    
    ToDo create(ToDo todo);

    List<ToDo> getUserTodos(Long userId);

}
