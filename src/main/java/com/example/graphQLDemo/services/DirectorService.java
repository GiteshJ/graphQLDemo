package com.example.graphQLDemo.services;

import com.example.graphQLDemo.domain.Director;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {

  public List<Director> loadDirectors(List<String> directors){
    return directors.stream().map(director -> new Director("Kevin Feige" + director)).collect(Collectors.toList());
  }
}
