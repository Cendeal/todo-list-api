package com.thoughtworks.springboottodolist.service;

import com.thoughtworks.springboottodolist.dao.TodoRepository;
import com.thoughtworks.springboottodolist.dto.TodoDto;
import com.thoughtworks.springboottodolist.exception.BusinessException;
import com.thoughtworks.springboottodolist.mapper.TodoMapper;
import com.thoughtworks.springboottodolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<TodoDto> getAll() {
        return todoRepository.findAll().stream().map(todoMapper::todo2TodoDto).collect(Collectors.toList());
    }

    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = todoMapper.todoDto2Todo(todoDto);
        todo.setCreated(new Date());
        return todoMapper.todo2TodoDto(todoRepository.save(todo));
    }

    public TodoDto deleteById(int id) throws BusinessException {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new BusinessException("Not Found This Todo"));
        todoRepository.deleteById(id);
        return todoMapper.todo2TodoDto(todo);
    }

    public TodoDto updateTodo(TodoDto todoDto) throws BusinessException {
        Todo todo = todoMapper.todoDto2Todo(todoDto);
        Todo todoWaitUpdate = todoRepository.findById(todo.getId()).orElseThrow(() -> new BusinessException("Not Found This Todo"));
        todoWaitUpdate.setUpdated(new Date());
        if (Objects.nonNull(todo.getType())) {
            todoWaitUpdate.setType(todo.getType());
        }
        if (Objects.nonNull(todo.getStatus())) {
            todoWaitUpdate.setStatus(todo.getStatus());
        }
        if (Objects.nonNull(todo.getText())) {
            todoWaitUpdate.setText(todo.getText());
        }
        if (Objects.nonNull(todo.getTitle())) {
            todoWaitUpdate.setTitle(todo.getTitle());
        }
        return todoMapper.todo2TodoDto(todoRepository.save(todoWaitUpdate));
    }
}
