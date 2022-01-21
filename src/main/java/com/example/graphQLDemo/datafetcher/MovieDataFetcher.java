package com.example.graphQLDemo.datafetcher;

import com.example.graphQLDemo.domain.Actor;
import com.example.graphQLDemo.domain.Director;
import com.example.graphQLDemo.domain.Movie;
import com.example.graphQLDemo.filters.MovieFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.dataloader.DataLoader;

@DgsComponent
public class MovieDataFetcher {

  List<Movie> movies = List.of(
      Movie.builder().name("Avengers").actors(List.of(
          new Actor("RDJ")
      )).directorId(1).build(),
      Movie.builder().name("Avengers: Age Of Ultron").actors(List.of(
          new Actor("Chris Evans")
      )).directorId(2).build(),
      Movie.builder().name("Avengers: Infinity War").actors(List.of(
          new Actor("Chris Pratt")
      )).directorId(3).build(),
      Movie.builder().name("Avengers: Endgame").actors(List.of(
          new Actor("Chris Hemsworth")
      )).directorId(4).build(),
      Movie.builder().name("Avengers: Secret Wars").actors(List.of(
          new Actor("Scarlett Johansson")
      )).directorId(5).build()
  );

  @DgsData(parentType = "Movie", field = "actors")
  public List<Actor> actors(DataFetchingEnvironment dfg) {
    Movie movie = dfg.getSource();
    return movies.stream()
        .filter(m -> m.getName().equalsIgnoreCase(movie.getName()))
        .flatMap(movie1 -> movie1.getActors().stream())
        .collect(Collectors.toList());
  }

  @DgsQuery
  public List<Movie> movies(@InputArgument MovieFilter movieFilter) {
    if(movieFilter == null) return movies;
    return movies.stream()
        .filter(m -> m.getName().equalsIgnoreCase(movieFilter.getMovie()))
        .filter(movie -> movie.getActors().stream().anyMatch( actor -> actor.getName().equalsIgnoreCase(movieFilter.getActor())))
        .collect(Collectors.toList());
  }

}
