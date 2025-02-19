package com.example.To_Do_App.controller;

import com.example.To_Do_App.entity.TodoItem;
import com.example.To_Do_App.service.TodoItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class HomeController {
    private TodoItemService todoItemService;

    public HomeController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("tasks",todoItemService.getAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam(name = "title") String title,@RequestParam(name = "description") String description){
        TodoItem item = new TodoItem(title,description,false, Instant.now(),Instant.now());
        todoItemService.save(item);
        return "redirect:/";
    }

    @GetMapping("/complete/{id}")
    public String completeItem(@PathVariable Long id){
        Optional<TodoItem> optionalItem = todoItemService.findById(id);
        if(optionalItem.isPresent()){
            TodoItem item = optionalItem.get();
            item.setIsCompleted(true);
            todoItemService.save(item);
        }
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id){
        Optional<TodoItem> optional = todoItemService.findById(id);
        if(optional.isPresent()){
            TodoItem item = optional.get();
            todoItemService.delete(item);
        }
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable Long id,Model model){
        Optional<TodoItem> item = todoItemService.findById(id);
        if(item.isPresent()){
            TodoItem item1 = item.get();
            model.addAttribute("task",item1);
        }
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable Long id,@RequestParam(name = "title") String title,@RequestParam(name = "description")String description,Model model){
        Optional<TodoItem> optional = todoItemService.findById(id);
        if(optional.isPresent()){
            TodoItem item = optional.get();
            item.setTitle(title);
            item.setDescription(description);
            item.setUpdatedAt(Instant.now());
            todoItemService.save(item);
        }
        return "redirect:/";
    }
}
