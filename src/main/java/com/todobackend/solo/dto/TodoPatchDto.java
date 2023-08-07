package com.todobackend.solo.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class TodoPatchDto {
  private long id;
  
  @NotNull
  private String title;
  
  @NotNull
  private int order;
  
  @NotNull
  private boolean completed;
  
  public void setId(long id) {
    this.id = id;
  }
}

