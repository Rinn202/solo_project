package com.todobackend.solo.controller;


import com.todobackend.solo.dto.TodoPatchDto;
import com.todobackend.solo.dto.TodoPostDto;
import com.todobackend.solo.dto.TodoResponseDto;
import com.todobackend.solo.entity.Todo;
import com.todobackend.solo.mapper.TodoMapper;
import com.todobackend.solo.service.TodoService;
import com.todobackend.solo.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TodoController {
  private final TodoService todoService;
  private final TodoMapper mapper;
  
  public TodoController(TodoService todoService, TodoMapper mapper) {
    this.todoService = todoService;
    this.mapper = mapper;
  }
  
  @PostMapping
  public ResponseEntity postTodo(@Valid @RequestBody TodoPostDto todoPostDto) {
    Todo todo = todoService.createTodo(mapper.todoPostDtoToTodo(todoPostDto));
    URI location = UriCreator.createUri("", todo.getId());
    return ResponseEntity.created(location).build();
  }
  
  @PatchMapping("/{todo-id}")
  public ResponseEntity patchTodo(
      @PathVariable("todo-id") @Positive long todoId,
      @Valid @RequestBody TodoPatchDto todoPatchDto){
    todoPatchDto.setId(todoId);
    
    Todo todo = todoService.updateTodo(mapper.todoPatchDtoToTodo(todoPatchDto));
    TodoResponseDto todoResponseDto = mapper.todoToTodoResponseDto(todo);
    return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
  }
  
  @GetMapping("/{todo-id}")
  public ResponseEntity getTodo(
      @PathVariable("todo-id") @Positive long todoId){
    Todo todo = todoService.findTodo(todoId);
    TodoResponseDto todoResponseDto = mapper.todoToTodoResponseDto(todo);
    return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
  }
  
//  @GetMapping
//  public ResponseEntity getTodos(){
//    List<Todo> todo = todoService.findTodos();
//    List<TodoResponseDto> todoResponseDto = mapper.todosToTodoResponseDto(todo);
//    return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
//  }
  @GetMapping
  public String getTodos(){
    return "To-do Application !";
  }
  
  @DeleteMapping("/{todo-id}")
  public ResponseEntity deleteTodo(
      @PathVariable("todo-id") @Positive long todoId) {
    todoService.deleteTodo(todoId);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
