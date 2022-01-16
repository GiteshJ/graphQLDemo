package com.example.graphQLDemo.resolver.bank;

import com.example.graphQLDemo.domain.bank.Asset;
import com.example.graphQLDemo.domain.bank.BankAccount;
import com.example.graphQLDemo.util.CorrelationIdPropagationExecutor;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AssetResolver implements GraphQLResolver<BankAccount> {

  private final Executor executorService = CorrelationIdPropagationExecutor.wrap(Executors.newFixedThreadPool(
      Runtime.getRuntime().availableProcessors())
  );

  public CompletableFuture<List<Asset>> asset(BankAccount bankAccount) {
    return CompletableFuture.supplyAsync(
        () -> {
          log.info("Requesting Assets information for bank account Id: {}", bankAccount.getId());
          return List.of(new Asset(UUID.randomUUID()));
        },
        executorService);
  }
}
