package com.example.graphQLDemo.domain;


import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {

  String name;
  List<Actor> actors;
  Director director;
  Integer directorId;

  public String getDirectorIdString() {
    return directorId.toString();
  }
}
