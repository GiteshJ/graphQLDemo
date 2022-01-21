package com.example.graphQLDemo.datamutation;

import com.example.graphQLDemo.domain.Rating;
import com.example.graphQLDemo.inputs.RatingInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;

@DgsComponent
public class RatingMutation {

  @DgsData(parentType = "Mutation", field = "addRating")
  public Rating addRating(@InputArgument("input") RatingInput ratingInput) {
    //No need for custom parsing anymore!
    int stars = ratingInput.getStars();

    if (stars < 1) {
      throw new IllegalArgumentException("Stars must be 1-5");
    }

    String title = ratingInput.getTitle();
    System.out.println("Rated " + title + " with " + stars + " stars");
    System.out.println("Rated " + ratingInput.getTitle() + " with " + ratingInput.getStars() + " stars");

    return new Rating(ratingInput.getStars());
  }
}