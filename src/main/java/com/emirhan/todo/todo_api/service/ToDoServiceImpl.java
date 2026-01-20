package com.emirhan.todo.todo_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emirhan.todo.todo_api.entity.ToDo;
import com.emirhan.todo.todo_api.repository.ToDoRepository;

@Service
public class ToDoServiceImpl  implements ToDoService{

    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository)
    {
        this.toDoRepository=toDoRepository;
    }

    @Override
    public ToDo create(ToDo toDo)
    {
        return toDoRepository.save(toDo);
    }     

    @Override 
    public List<ToDo> getUserTodos(Long userId)
    {
        return toDoRepository.findByUserId(userId);
    }
}
