package com.todobackend.solo.service;

import com.todobackend.solo.entity.Todo;
import com.todobackend.solo.exception.BusinessLogicException;
import com.todobackend.solo.exception.ExceptionCode;
import com.todobackend.solo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
  private final TodoRepository repository;
  
  public TodoService(TodoRepository repository) {
    this.repository = repository;
  }
    
    public Todo createTodo(Todo todo) {
    Integer maxTodoOrder = repository.findMaxTodoOrder();
    int lastTodoOrder = maxTodoOrder != null ? maxTodoOrder + 1 : 1;
    todo.setOrder(lastTodoOrder);
    return repository.save(todo);
  }
  
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
  public Todo updateTodo(Todo todo){
    Todo findTodo = findTodo(todo.getId());
    Optional.of(todo.getOrder()).ifPresent(findTodo::setOrder);
    Optional.of(todo.getTitle()).ifPresent(findTodo::setTitle);
    Optional.of(todo.isCompleted()).ifPresent(findTodo::setCompleted);
    
    return repository.save(findTodo);
  }
  
  @Transactional(readOnly = true)
  public Todo findTodo(long todoId) {
    Optional<Todo> optionalTodo = repository.findById(todoId);
    Todo findTodo = optionalTodo.orElseThrow(() ->
            new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
    return findTodo;
  }
  
  @Transactional(readOnly = true)
  public List<Todo> findTodos() {
    return repository.findAll();
  }
  
  public void deleteTodo(long todoId) {
    Todo findTodo = findTodo(todoId);
    repository.delete(findTodo);
    }
    
    
//  삭제시 todoOrder 자동정렬 기능을 추가하였으나 제공된 viwe에서 기능하지 않습니다
//  public void deleteTodo(long todoId) {
//    Todo findTodo = findTodo(todoId);
//    repository.delete(findTodo);
//
//    List<Todo> todos = repository.findAll();
//
//    int newOrder = 1;
//    for (Todo todo : todos) {
//      todo.setTodoOrder(newOrder);
//      newOrder++;
//    }
//    repository.saveAll(todos);
//  }
}
