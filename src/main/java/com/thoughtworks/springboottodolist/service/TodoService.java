package com.thoughtworks.springboottodolist.service;

import com.thoughtworks.springboottodolist.dao.TodoRepository;
import com.thoughtworks.springboottodolist.dto.TodoDto;
import com.thoughtworks.springboottodolist.exception.BusinessException;
import com.thoughtworks.springboottodolist.mapper.TodoMapper;
import com.thoughtworks.springboottodolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TodoService {
    @Autowired
    private final TodoRepository todoRepository;
    @Autowired
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Todo addTodo(TodoDto todoDto) {
        Todo todo = todoMapper.todoDto2Todo(todoDto);
        return todoRepository.save(todo);
    }

    public Todo deleteById(int id) throws BusinessException {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new BusinessException("Not Found This Todo"));
        todoRepository.deleteById(id);
        return todo;
    }

    public Todo updateTodo(TodoDto todoDto) throws BusinessException {
        Todo todo = todoMapper.todoDto2Todo(todoDto);
        Todo todoWaitUpdate = todoRepository.findById(todo.getId()).orElseThrow(() -> new BusinessException("Not Found This Todo"));
        if (Objects.nonNull(todo.getType())) {
            todoWaitUpdate.setType(todo.getType());
        }
        if (Objects.nonNull(todo.getStatus())) {
            todoWaitUpdate.setStatus(todo.getStatus());
        }
        if (Objects.nonNull(todo.getText())) {
            todoWaitUpdate.setText(todo.getText());
        }
        if (Objects.nonNull(todo.getUpdated())) {
            todoWaitUpdate.setUpdated(todo.getUpdated());
        }
        if (Objects.nonNull(todo.getTitle())) {
            todoWaitUpdate.setTitle(todo.getTitle());
        }
        return todoRepository.save(todoWaitUpdate);
    }
}
