package com.example.graphQLDemo.authorsandposts;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  @Autowired
  AuthorRepository authorRepository;
  @GetMapping(value = "/getAuthor")
  public List<Author> get(){
    return authorRepository.findAll();
  }
}
