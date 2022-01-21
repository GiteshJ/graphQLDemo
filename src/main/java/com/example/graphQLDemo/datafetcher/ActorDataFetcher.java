package com.example.graphQLDemo.datafetcher;

import com.example.graphQLDemo.domain.Actor;
import com.example.graphQLDemo.domain.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ActorDataFetcher {

  private final List<Actor> actors = List.of(
      new Actor("RDJ"),
      new Actor("Chris Evans"),
      new Actor("Chris Hemsworth"),
      new Actor("Chris Prat"),
      new Actor("Scarlett Johansson")
  );

  @DgsData(parentType = "Query", field = "actors")
  public List<Actor> actors(@InputArgument String titleFilter) {
    if(titleFilter == null) {
      return actors;
    }

    return actors.stream().filter(s -> s.getName().contains(titleFilter)).collect(Collectors.toList());
  }


}
