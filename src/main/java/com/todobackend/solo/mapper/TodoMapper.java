package com.todobackend.solo.mapper;

import com.todobackend.solo.dto.TodoPatchDto;
import com.todobackend.solo.dto.TodoPostDto;
import com.todobackend.solo.dto.TodoResponseDto;
import com.todobackend.solo.entity.Todo;
import com.todobackend.solo.utils.UriCreator;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    default Todo todoPostDtoToTodo(TodoPostDto todoPostDto){
        Todo todo = new Todo();
        todo.setOrder(todoPostDto.getOrder());
        todo.setTitle(todoPostDto.getTitle());
        todo.setCompleted(todoPostDto.isCompleted());
        return todo;
    };
    
    default Todo todoPatchDtoToTodo(TodoPatchDto todoPatchDto){
        Todo todo = new Todo();
        todo.setId(todoPatchDto.getId());
        todo.setOrder(todoPatchDto.getOrder());
        todo.setTitle(todoPatchDto.getTitle());
        todo.setCompleted(todoPatchDto.isCompleted());
        return todo;
    };
    
    default TodoResponseDto todoToTodoResponseDto(Todo todo){
        TodoResponseDto responseDto = new TodoResponseDto(
            todo.getId(),todo.getTitle(), todo.getOrder(), todo.isCompleted(), UriCreator.createUri("http://localhost:8080", todo.getId())
        );
        return responseDto;
    };
    List<TodoResponseDto> todosToTodoResponseDto(List<Todo> todo);
    
}
