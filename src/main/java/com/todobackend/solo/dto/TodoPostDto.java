package com.todobackend.solo.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class TodoPostDto {
  
  @NotNull
  private String title;
  
  private int order;
  
  @NotNull
  private boolean completed;
}
