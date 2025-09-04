package com.syxbruno.auth.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

  INSTRUCTOR("instructor"),
  STUDENT("student");

  private final String role;
}
