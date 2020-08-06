package com.thoughtworks.springboottodolist.service;

import com.thoughtworks.springboottodolist.dao.TodoRepository;
import com.thoughtworks.springboottodolist.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    public TodoService(TodoRepository todoRepository) {
    }

    public List<Todo> getAll() {
        return null;
    }
}
