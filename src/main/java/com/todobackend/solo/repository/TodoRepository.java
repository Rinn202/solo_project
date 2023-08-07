package com.todobackend.solo.repository;

import com.todobackend.solo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
  @Query("SELECT MAX(t.order) FROM Todo t")
  Integer findMaxTodoOrder();
}
