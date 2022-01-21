package com.example.graphQLDemo.domain;

import lombok.Data;

@Data
public class Actor {

  String name;

  public Actor(String name){
    this.name = name;
  }

}
