package com.thoughtworks.springboottodolist.service;

import com.thoughtworks.springboottodolist.dao.TodoRepository;
import com.thoughtworks.springboottodolist.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoTest {
    @Test
    void should_return_todo_list_when_get_todo_list() {
        // given
        TodoRepository todoRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        given(todoRepository.findAll()).willReturn(Collections.emptyList());
        // when
        List<Todo> todoList = todoService.getAll();
        // then
        verify(todoRepository, times(1)).findAll();
        assertEquals(0, todoList.size());
    }
}
