package com.thoughtworks.springboottodolist.controller;

import com.thoughtworks.springboottodolist.dto.TodoDto;
import com.thoughtworks.springboottodolist.exception.BusinessException;
import com.thoughtworks.springboottodolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping
    public List<TodoDto> getAllTodoList() {
        return todoService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDto addTodo(@RequestBody TodoDto todoDto) {
        return todoService.addTodo(todoDto);
    }

    @DeleteMapping("/{id}")
    public TodoDto deleteTodo(@PathVariable int id) throws BusinessException {
        return todoService.deleteById(id);
    }

    @PutMapping("/{id}")
    public TodoDto updateTodo(@RequestBody TodoDto todoDto,@PathVariable int id) throws BusinessException {
        todoDto.setId(id);
        return todoService.updateTodo(todoDto);
    }
}
