package com.example.graphQLDemo.datafetcher;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {DgsAutoConfiguration.class, ShowsDataFetcher.class})
class ShowsDataFetcherTest {

  @Autowired
  DgsQueryExecutor dgsQueryExecutor;

  @Test
  void shows() {
    List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(
        " { shows { title releaseYear }}",
        "data.shows[*].title");

    assertThat(titles).contains("Ozark");
  }
}