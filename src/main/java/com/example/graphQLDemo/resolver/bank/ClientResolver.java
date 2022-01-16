package com.example.graphQLDemo.resolver.bank;

import com.example.graphQLDemo.domain.bank.BankAccount;
import com.example.graphQLDemo.domain.bank.Client;
import com.example.graphQLDemo.util.CorrelationIdPropagationExecutor;
import graphql.GraphQLException;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jdk.jshell.spi.ExecutionControl.RunException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {

  private final Executor executorService = CorrelationIdPropagationExecutor.wrap(Executors.newFixedThreadPool(
      Runtime.getRuntime().availableProcessors()
  ));

  public CompletableFuture<Client> client(BankAccount bankAccount) {
    log.info("Requesting Client info for bank account Id: {}", bankAccount.getId());
    return CompletableFuture.supplyAsync(() ->
      Client.builder()
          .id(UUID.randomUUID())
          .firstName("Gitesh")
          .lastName("Jain")
          .build()
    , executorService);
  }

  /*
  public Client client(BankAccount bankAccount) {
    log.info("Requesting Client info for bank account Id: {}", bankAccount.getId());
    //throw  new GraphQLException("Client Unavailable");
    //throw  new RuntimeException("Client Unavailable due to SQL database query");

    return Client.builder()
        .id(UUID.randomUUID())
        .firstName("Gitesh")
        .lastName("Jain")
        .build();
  }
   */

  /*
  public DataFetcherResult<Client> client(BankAccount bankAccount) {
    log.info("Requesting Client info for bank account Id: {}", bankAccount.getId());
    return DataFetcherResult.<Client>newResult()
        .data(Client.builder()
            .id(UUID.randomUUID())
            .firstName("Gitesh")
            .lastName("Jain")
            .build())
        //.error(new GenericGraphQLError("Error form sub-clients"))
        .build();
  }
   */
}
