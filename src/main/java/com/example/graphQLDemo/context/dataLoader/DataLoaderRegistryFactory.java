package com.example.graphQLDemo.context.dataLoader;

import com.example.graphQLDemo.service.BalanceService;
import com.example.graphQLDemo.util.CorrelationIdPropagationExecutor;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoaderRegistryFactory {

  public static final String BALANCE_DATA_LOADER = "BALANCE_DATA_LOADER";

  private final BalanceService balanceService;
  private final Executor balanceExecutor = CorrelationIdPropagationExecutor.wrap(Executors.newFixedThreadPool(
      Runtime.getRuntime().availableProcessors()
  ));
  public DataLoaderRegistry create(String userId) {
    var registry = new DataLoaderRegistry();
    registry.register(BALANCE_DATA_LOADER, createBalanceDataLoader(userId));
    return registry;
  }

  private DataLoader<UUID, BigDecimal> createBalanceDataLoader(String userId) {
    return DataLoader
        .newMappedDataLoader((Set<UUID> bankAccountIds, BatchLoaderEnvironment environment) ->
            CompletableFuture.supplyAsync(() ->
                    balanceService.getBalanceFor((Map) environment.getKeyContexts(), userId),
                balanceExecutor));
  }

}
