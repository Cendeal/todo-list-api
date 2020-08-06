package com.thoughtworks.springboottodolist.mapper;

import com.thoughtworks.springboottodolist.dto.TodoDto;
import com.thoughtworks.springboottodolist.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TodoMapper{
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoDto todo2TodoDto(Todo todo);
    Todo todoDto2Todo(TodoDto todoDto);
}
