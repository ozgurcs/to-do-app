package com.example.To_Do_App.repository;

import com.example.To_Do_App.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem,Long> {
    Optional<TodoItem> findByTitle(String title );
}



