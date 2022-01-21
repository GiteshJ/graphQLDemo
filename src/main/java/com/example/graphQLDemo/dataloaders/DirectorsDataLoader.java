package com.example.graphQLDemo.dataloaders;

import com.example.graphQLDemo.domain.Director;
import com.example.graphQLDemo.services.DirectorService;
import com.netflix.graphql.dgs.DgsDataLoader;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import org.dataloader.BatchLoader;
import org.dataloader.Try;
import org.springframework.beans.factory.annotation.Autowired;

@DgsDataLoader(name = "directors")
public class DirectorsDataLoader implements BatchLoader<String, Director> {

  @Autowired
  DirectorService directorService;

  @Override
  public CompletionStage<List<Director>> load(List<String> keys) {
    return CompletableFuture.supplyAsync(() -> directorService.loadDirectors(keys));
  }
}
