package com.todobackend.solo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Todo {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @Column(nullable = false)
  private String title;
  
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDERS")
  private int order;
  
  @Column(name = "COMPLETED")
  private boolean completed;
}
