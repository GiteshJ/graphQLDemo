package com.example.graphQLDemo.resolver.bank;

import com.example.graphQLDemo.context.dataLoader.DataLoaderRegistryFactory;
import com.example.graphQLDemo.domain.bank.BankAccount;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BalanceResolver implements GraphQLResolver<BankAccount> {

  public CompletableFuture<BigDecimal> balance(BankAccount bankAccount,
      DataFetchingEnvironment environment) {
    DataLoader<UUID, BigDecimal> dataLoader = environment
        .getDataLoader(DataLoaderRegistryFactory.BALANCE_DATA_LOADER);
    return dataLoader.load(bankAccount.getId(), bankAccount);
  }

}
