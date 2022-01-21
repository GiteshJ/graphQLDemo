package com.example.graphQLDemo.datafetcher;

import com.example.graphQLDemo.domain.Director;
import com.example.graphQLDemo.domain.Movie;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import graphql.schema.DataFetchingEnvironment;
import java.util.concurrent.CompletableFuture;
import org.dataloader.DataLoader;

@DgsComponent
public class DirectorDataFetcher {

  @DgsData(parentType = "Movie", field = "director")
  public CompletableFuture<Director> director(DataFetchingEnvironment dfe) {

    DataLoader<String, Director> dataLoader = dfe.getDataLoader("directors");
    Movie movie = dfe.getSource();

    return dataLoader.load(movie.getDirectorIdString());
  }
}