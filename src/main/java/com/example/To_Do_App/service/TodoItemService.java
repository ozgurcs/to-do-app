package com.example.To_Do_App.service;

import com.example.To_Do_App.entity.TodoItem;
import com.example.To_Do_App.repository.TodoItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Transactional
    public TodoItem save(TodoItem item){
        return todoItemRepository.save(item);
    }

    public Optional<TodoItem> findById(Long id){
        return todoItemRepository.findById(id);
    }
    public List<TodoItem> getAll(){
        return todoItemRepository.findAll();
    }

    @Transactional
    public void delete(TodoItem todoItem){
        todoItemRepository.delete(todoItem);
    }



}
