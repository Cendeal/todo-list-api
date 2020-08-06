package com.thoughtworks.springboottodolist.service;

import com.sun.xml.bind.v2.TODO;
import com.thoughtworks.springboottodolist.dao.TodoRepository;
import com.thoughtworks.springboottodolist.dto.TodoDto;
import com.thoughtworks.springboottodolist.exception.BusinessException;
import com.thoughtworks.springboottodolist.mapper.TodoMapper;
import com.thoughtworks.springboottodolist.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoTest {
    @Mock
    TodoRepository todoRepository;
    @Mock
    TodoMapper todoMapper;
    @InjectMocks
    TodoService todoService;

    @Test
    void should_return_todo_list_when_get_todo_list() {
        // given
        given(todoRepository.findAll()).willReturn(Collections.emptyList());
        // when
        List<Todo> todoList = todoService.getAll();
        // then
        verify(todoRepository, times(1)).findAll();
        assertEquals(0, todoList.size());
    }

    @Test
    void should_return_todo_when_add_todo_given_todo_request_dto() {
        // given
        TodoDto todoDto = mock(TodoDto.class);
        Todo todo = new Todo();
        given(todoMapper.todoDto2Todo(todoDto)).willReturn(todo);
        given(todoRepository.save(todo)).willReturn(todo);
        // when
        Todo todoSaved = todoService.addTodo(todoDto);
        // then
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    void should_return_todo_when_delete_todo_given_todo_id() throws BusinessException {
        // given
        int id = 1;
        Todo todo = new Todo();
        given(todoRepository.findById(id)).willReturn(Optional.of(todo));
        // when
        Todo todoDeleted = todoService.deleteById(id);
        // then
        verify(todoRepository,times(1)).deleteById(id);
        assertEquals(todo,todoDeleted);

    }
}
