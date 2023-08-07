package com.todobackend.solo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URI;

@AllArgsConstructor
@Getter
public class TodoResponseDto {
  private long id;
  private String title;
  private int order;
  private boolean completed;
  private URI url;
}


